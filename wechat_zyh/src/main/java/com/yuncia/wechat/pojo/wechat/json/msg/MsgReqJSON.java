package com.yuncia.wechat.pojo.wechat.json.msg;

import com.yuncia.wechat.pojo.wechat.MessageType;
import com.yuncia.wechat.pojo.wechat.json.WeChatJSON;

/**
 * 子类必须设置msgType属性
 *
 * @author MonKong
 * @Description 调用微信接口的基类，拥有公共的数据
 * @date 2014年12月11日
 */
public class MsgReqJSON extends WeChatJSON {
    private String touser;//目标用户
    private MessageType msgType;

    public MsgReqJSON(MessageType msgType, String touser) {
        super();
        setMsgType(msgType);
        setTouser(touser);
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public MessageType getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageType msgType) {
        this.msgType = msgType;
    }

    @Override
    public String toString() {
        return "MsgReqJSON [touser=" + touser + ", msgType=" + msgType + "]";
    }
}
