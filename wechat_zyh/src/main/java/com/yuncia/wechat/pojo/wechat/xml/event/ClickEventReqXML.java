/**
 *
 */
package com.yuncia.wechat.pojo.wechat.xml.event;

import com.yuncia.wechat.pojo.wechat.xml.WeChatXML;

/**
 * @author MonKong
 * @Description 微信接口事件请求PO
 * @date 2014年12月9日
 */
public class ClickEventReqXML extends WeChatXML {
    private EventType event;
    private String eventKey;

    public EventType getEvent() {
        return event;
    }

    public void setEvent(EventType event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
