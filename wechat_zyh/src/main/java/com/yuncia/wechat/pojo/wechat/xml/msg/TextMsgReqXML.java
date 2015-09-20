package com.yuncia.wechat.pojo.wechat.xml.msg;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;

/**
 * @author MonKong
 * @Description 微信接口消息请求PO
 * @date 2014年12月9日
 */
public class TextMsgReqXML extends WeChatXML {
    private String content;
    private Long msgId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }


    @Override
    public String toString() {
        return "TextMsgReqXML [content=" + content + ", msgId=" + msgId + "]";
    }
}
