package com.yuncia.wechat.jms.produser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created with IntelliJ IDEA.
 * User: zhu
 * Date: 15-5-15
 * Time: 上午7:11
 * To change this template use File | Settings | File Templates.
 */
@Service
public class Produser440000 {
    @Autowired
    private JmsTemplate jmsTemplate;
//    org.springframework.jms.listener.DefaultMessageListenerCsontainer
//    org.springframework.jms.listener.DefaultMessageListenerContainer
    public void sendMessage(Destination destination, final String message){
        System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }

}
