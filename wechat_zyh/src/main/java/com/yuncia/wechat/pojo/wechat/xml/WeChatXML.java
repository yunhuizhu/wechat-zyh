package com.yuncia.wechat.pojo.wechat.xml;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.yuncia.wechat.pojo.wechat.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信接口的规律：
 * 微信的事件请求和服务器的被动响应都是XML格式。
 * 服务端主动调用微信接口和微信响应都是JSON格式。
 *
 * @author MonKong
 * @Description 微信接口XMLPOJO的基类。
 * @date 2014年12月9日
 */
@JacksonXmlRootElement(localName = "xml")
@JsonNaming(PropertyNamingStrategy.PascalCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class WeChatXML {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private Long createTime;
    @XStreamAlias("MsgType")
    private MessageType msgType;

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
