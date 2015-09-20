package com.yuncia.wechat.controller.wechat;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuncia.wechat.pojo.wechat.xml.event.EventType;
import com.yuncia.wechat.service.client.event.WeChatService;
import com.yuncia.wechat.service.client.event.impl.event.BaseEventServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuncia.wechat.pojo.wechat.MessageType;
import com.yuncia.wechat.pojo.wechat.UrlParam;
import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.WeChatUtils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * 接受微信发送请求的Controller
 *
 * @author MonKong
 * @date 2014年12月13日
 */
@Controller
@SuppressWarnings({"rawtypes", "unchecked"})
public class WeChatController {
    private static final Logger log = LogManager.getLogger();
    @Resource(name = "serviceMap")
    private Map<MessageType, WeChatService> serviceMap;// 不同类型信息对应的业务逻辑类
    @Resource(name = "eventServiceMap")
    private Map<EventType, WeChatService> eventServiceMap;// 事件类型不同
    private String token;
    private WXBizMsgCrypt wxc;// 微信的加解密类

    @Autowired
    public WeChatController(@Value("${wechat.token}") String token,
                            @Value("${wechat.appId}") String appId,
                            @Value("${wechat.encodingAesKey}") String encodingAesKey)
            throws AesException {
        super();
        this.token = token;
        wxc = new WXBizMsgCrypt(token, encodingAesKey, appId);
        log.debug("token:{}|appid:{}|aeskey:{}", token, appId, encodingAesKey);
    }

    /**
     * 用于处理来自微信的请求，可以自适应加密|明文模式
     *
     * @param body
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    @ResponseBody
    public String wechat(@RequestBody String body, UrlParam params,
                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.err.println(params);
        log.debug("---weixin|body:\n" + body);
        // 检验消息签名
        if (!WeChatUtils.verifyMsg(token, params.getSignature(),
                params.getTimestamp(), params.getNonce())) {
            throw new Exception("检验消息签名失败！");
        }
        // 检查是否加密模式
        String mingwen = null;
        boolean isEncrtpt = false;
        if (isEncrypt(params.getEncrypt_type())) {// 表示内容为密文|加密模式
            isEncrtpt = true;
            mingwen = wxc.decryptMsg(params.getMsg_signature(),
                    params.getTimestamp(), params.getNonce(), body);
        } else {// 表示内容为明文|明文模式
            mingwen = body;
        }
        log.debug("---weixin|mingwen:" + mingwen);
        // 获取对应Service
        WeChatService service = matchService(mingwen);
        if(service==null){  //不需要返回值
            return null;
        }
        // 获取service泛型信息,封装数据
        WeChatXML bean = (WeChatXML) DataConverUtils.XMLToObject(mingwen,
                getGeneric(service.getClass()));
        // 调用对应的微信业务, 返回xml
//        WeChatXML xml = service.process(bean);
        // 解析xml为字符串
        String responseXml =service.process(bean);
//        responseXml=responseXml.replaceAll(">","&gt;");
        System.err.println(responseXml);
        // 对返回内容加密后返回
        String responseStr = null;
        if (isEncrtpt) {// 加密模式
            responseStr = wxc.encryptMsg(responseXml, params.getTimestamp(),
                    params.getNonce());
        } else {// 明文模式
            responseStr = responseXml;
        }
        log.debug("---weixin|responseStr:" + responseStr);
        return responseStr;
    }

    /**
     * 用于接入微信接口的验证步骤
     *
     * @param echostr
     * @param params
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/wechat", method = RequestMethod.GET, params = "echostr")
    @ResponseBody
    public String wechatAccess(String echostr, UrlParam params)
            throws Exception {
        System.out.println(echostr + "|" + params);
        if (WeChatUtils.verifyMsg(token, params.getSignature(),
                params.getTimestamp(), params.getNonce())) {
            return echostr;
        } else {
            throw new Exception("检验消息签名失败！");
        }
    }

    /**
     * 统一处理异常,可根据不同异常作出不同的响应
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler
    private String handleException(Exception e) {
        log.error(e.getMessage(), e);
        // 异常情况返回错误,微信端会显示"...暂时无法服务"
        return "error";
    }

    /**
     * 判断是否加密模式
     *
     * @param encryptType
     * @return
     */
    public boolean isEncrypt(String encryptType) {
        return encryptType != null && "aes".equals(encryptType);
    }

    /**
     * 获取泛型信息
     *
     * @param clazz
     * @return
     * @date 2014年12月13日
     */
    private Class getGeneric(Class clazz) {
        //点击事件
        if (clazz.getClass().equals(BaseEventServiceImpl.class)) {

        }
        ParameterizedType type = (ParameterizedType) (clazz
                .getGenericSuperclass());
        return (Class) type.getActualTypeArguments()[0];
    }

    /**
     * 根据"MsgType"返回适当的WeChatEventService
     *
     * @param requestBody
     * @return
     * @throws Exception
     */
    private WeChatService matchService(String requestBody)
            throws Exception {
        ObjectReader xmlReader = DataConverUtils.getXMLReader();
        // 读取信息类型
        String msgType = xmlReader.readTree(requestBody).get("MsgType")
                .asText();
        MessageType key = DataConverUtils.convertValue(msgType,
                MessageType.class);
        // 获取对应业务
        WeChatService service = serviceMap.get(key);
        if(service!=null){
        //处理事件推送
        if (service.getClass().equals(BaseEventServiceImpl.class)) {
            service = getEventService(requestBody);
        }
        }
       return service;
    }

    /**
     * 根据"eventType"返回适当的WeChatEventService
     *
     * @param requestBody
     * @return
     * @throws Exception
     */
    private WeChatService getEventService(String requestBody)
            throws Exception {
        ObjectReader xmlReader = DataConverUtils.getXMLReader();
        // 读取信息类型类型
        String eventType = xmlReader.readTree(requestBody).get("Event")
                .asText();
        EventType key = DataConverUtils.convertValue(eventType,
                EventType.class);
        // 获取对应业务
        WeChatService service = eventServiceMap.get(key);
        return service;
    }

    private void testSession(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Integer sessionUserName = (Integer) httpSession.getAttribute("userNameId");
        System.out.println("sessionId;" + request.getRequestedSessionId());
        if (sessionUserName != null) {
            System.out.println("sessionUserName;" + sessionUserName);
            sessionUserName++;
            httpSession.setAttribute("userNameId", sessionUserName);
        } else {
            System.out.println("sessionUserName为空");
            httpSession.setAttribute("userNameId", 0);
        }
    }
}
