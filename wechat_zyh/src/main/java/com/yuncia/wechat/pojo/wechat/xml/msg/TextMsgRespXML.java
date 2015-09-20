/**
 *
 */
package com.yuncia.wechat.pojo.wechat.xml.msg;

import com.yuncia.wechat.pojo.wechat.MessageType;
import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author MonKong
 * @Description 用一句话描述该类的作用
 * @date 2014年12月9日
 */
public class TextMsgRespXML extends WeChatXML {
    public TextMsgRespXML(){
        setMsgType(MessageType.text);
    }
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMsgRespXML [msgType=" + getMsgType() + ", content=" + content
                + "]";
    }



}
