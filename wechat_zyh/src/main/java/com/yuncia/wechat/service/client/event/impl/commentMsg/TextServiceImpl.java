package com.yuncia.wechat.service.client.event.impl.commentMsg;

import com.yuncia.wechat.service.client.event.WeChatService;
import com.yuncia.wechat.util.DataConverUtils;
import org.springframework.stereotype.Service;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;
import com.yuncia.wechat.pojo.wechat.xml.msg.TextMsgReqXML;
import com.yuncia.wechat.pojo.wechat.xml.msg.TextMsgRespXML;

/**
 * @author MonKong
 * @Description 响应文字消息请求
 * @date 2014年12月10日
 */
@Service
public class TextServiceImpl extends
        WeChatService<TextMsgReqXML, TextMsgRespXML> {

    @Override
    public String service(TextMsgReqXML req, TextMsgRespXML resp)
            throws Exception {
        resp.setContent("呵呵~！B<a href='http://www.baidu.com'>点我</a>");
        return DataConverUtils.ObjectToXML(resp);
    }

}
