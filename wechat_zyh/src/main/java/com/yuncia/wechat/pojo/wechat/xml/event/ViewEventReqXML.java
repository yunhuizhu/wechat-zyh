package com.yuncia.wechat.pojo.wechat.xml.event;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;

/**
 * @author MonKong
 * @Description 微信接口事件请求PO
 * @date 2014年12月9日
 */
public class ViewEventReqXML extends WeChatXML {
    private static final EventType event = EventType.VIEW;
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public EventType getEvent() {
        return event;
    }
}
