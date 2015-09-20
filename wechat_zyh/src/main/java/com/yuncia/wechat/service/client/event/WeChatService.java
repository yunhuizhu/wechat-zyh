package com.yuncia.wechat.service.client.event;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;

/**
 * @author MonKong
 * @Description 处理来自微信事件请求的基类接口，处理微信请求的业务都应该继承本类。泛型信息为业务需要的具体bean
 * @date 2014年12月10日
 */
public abstract class WeChatService<reqT extends WeChatXML, respT extends WeChatXML> {
    //	private Class<reqT> reqClazz;
    private Class<respT> respClazz;
    protected final Logger logger = LogManager.getLogger();

    public WeChatService() {// 获取泛型信息
        super();
        ParameterizedType type = (ParameterizedType) (this.getClass()
                .getGenericSuperclass());
//		reqClazz = (Class) type.getActualTypeArguments()[0];
        respClazz = (Class) type.getActualTypeArguments()[1];
        // logger = LogManager.getLogger(clazz);
    }

    /**
     * 供Control层调用，抽取通用业务，一般情况不要重写此方法.
     *
     * @param requestBody
     * @return 响应的内容，将会被Control层转为String后直接响应
     * @throws Exception
     * @date 2014年12月10日
     */
    public String process(reqT reqBean) throws Exception {
        respT respBean = respClazz.newInstance();
        // 设置通用信息
        respBean.setFromUserName(reqBean.getToUserName());
        respBean.setToUserName(reqBean.getFromUserName());
        respBean.setCreateTime(new Date().getTime());
        return service(reqBean, respBean);
    }

    /**
     * 业务处理方法
     *
     * @param req
     * @return
     * @throws Exception
     * @date 2014年12月10日
     */
    public abstract String service(reqT req, respT resq) throws Exception;

}
