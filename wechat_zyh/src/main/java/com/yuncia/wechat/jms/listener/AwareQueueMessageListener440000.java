package com.yuncia.wechat.jms.listener;

import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-15
 * Time: 上午6:58
 * To change this template use File | Settings | File Templates.
 */
public class AwareQueueMessageListener440000 implements
        SessionAwareMessageListener<TextMessage> {

    private Destination destination;

    public void onMessage(TextMessage message, Session session) throws JMSException {
        System.out.println("收到一条消息");
        System.out.println("消息内容是：" + message.getText());
//        MessageProducer producer = session.createProducer(destination);
////        consumer.rec
//        Message textMessage = session.createTextMessage("ConsumerSessionAwareMessageListener。。。");
//        producer.send(textMessage);
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

}
