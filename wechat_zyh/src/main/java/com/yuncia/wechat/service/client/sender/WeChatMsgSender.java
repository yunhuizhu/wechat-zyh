package com.yuncia.wechat.service.client.sender;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.yuncia.wechat.pojo.wechat.json.WeChatJSON;
import com.yuncia.wechat.pojo.wechat.json.msg.ReturnJSON;
import com.yuncia.wechat.sys.SysConstant;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.HttpsUtils;
import com.yuncia.wechat.util.UrlGenerator;

/**
 * @author MonKong
 * @Description 用于提交微信POST请求
 * @date 2014年12月12日
 */
@Component
public class WeChatMsgSender {
    private static final Logger logger = LogManager.getLogger();

    public ReturnJSON sendGet(String url, Map<String, String> params)
            throws Exception {
        String respJson = HttpsUtils.doGet(url, params,true);
        return DataConverUtils.JsonToObject(respJson, ReturnJSON.class);
    }

    public ReturnJSON sendPost(WeChatJSON postData) throws Exception {
        if (postData == null) {
            throw logger.throwing(new Exception("调用微信接口的post内容为空！"));
        }
        // 转换为JSON数据
        String requestBody = DataConverUtils.ObjectToJson(postData);
        // 发送请求
        String respJson = HttpsUtils.doPost(UrlGenerator.createSendMessageUrl(SysConstant.globalAccessToken), requestBody,true);
        // 返回响应数据
        return DataConverUtils.JsonToObject(respJson, ReturnJSON.class);
    }

}
