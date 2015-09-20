package com.yuncia.wechat.pojo.wechat.xml;

import com.yuncia.wechat.pojo.wechat.MessageType;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-12
 * Time: 下午4:31
 * To change this template use File | Settings | File Templates.
 */
public class BaseResp {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private Long createTime;
    @XStreamAlias("MsgType")
    private MessageType msgType;

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

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }
}
