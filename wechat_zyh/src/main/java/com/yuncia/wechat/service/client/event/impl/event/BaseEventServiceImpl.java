package com.yuncia.wechat.service.client.event.impl.event;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;
import com.yuncia.wechat.pojo.wechat.xml.msg.TextMsgRespXML;
import com.yuncia.wechat.service.client.event.WeChatService;
import com.yuncia.wechat.util.DataConverUtils;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-4-28
 * Time: 下午4:26
 * To change this template use File | Settings | File Templates.
 */

//无作用，只是做中转用，具体的是该类的子类进行处理
@Service
public class BaseEventServiceImpl extends
        WeChatService<WeChatXML, TextMsgRespXML> {


    @Override
    public String service(WeChatXML req, TextMsgRespXML resp)
            throws Exception {
        return DataConverUtils.ObjectToXML(resp);
    }
}
