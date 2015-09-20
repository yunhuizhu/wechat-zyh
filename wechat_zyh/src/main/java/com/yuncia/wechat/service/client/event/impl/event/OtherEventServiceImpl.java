package com.yuncia.wechat.service.client.event.impl.event;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;
import com.yuncia.wechat.pojo.wechat.xml.event.ClickEventReqXML;
import com.yuncia.wechat.pojo.wechat.xml.msg.TextMsgRespXML;
import com.yuncia.wechat.service.client.event.WeChatService;
import com.yuncia.wechat.service.wap.CustomerService;
import com.yuncia.wechat.util.DataConverUtils;
import com.yuncia.wechat.util.OtherSystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-8
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OtherEventServiceImpl extends
        WeChatService<ClickEventReqXML, TextMsgRespXML> {

    @Autowired
    private CustomerService customerService;

    @Override
    public String service(ClickEventReqXML req, TextMsgRespXML resp)
            throws Exception {
        System.out.println("req.getEvent():" + req.getEvent());
        if ("subscribe".equals(req.getEvent().toString())) { //关注
            System.out.println("OtherSystemUtil.wechat_subscribe_msg:" + OtherSystemUtil.wechat_subscribe_msg);
            resp.setContent(OtherSystemUtil.wechat_subscribe_msg);
        }
        return DataConverUtils.ObjectToXML(resp);
    }
}
