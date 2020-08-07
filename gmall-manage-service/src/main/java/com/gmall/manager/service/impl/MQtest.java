package com.gmall.manager.service.impl;

import com.gmall.mq.ActiveMQUtil;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;

import javax.jms.*;

/**
 * ClassName MQtest
 * PackageName com.gmall.manager.service.impl
 *
 * @Description
 * @auther wang
 * @create 2020-07-28
 */
public class MQtest {


    public static void main(String[] args) {

        ActiveMQUtil activeMQUtil = new ActiveMQUtil();

        Connection connection = null;
        Session session = null;
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(true, Session.SESSION_TRANSACTED);

//            Queue payment = session.createQueue("PAYMENT");

            Topic drink = session.createTopic("DRINK");

            TextMessage test = new ActiveMQTextMessage();
            test.setText("吃饭了");

            MessageProducer producer = session.createProducer(drink);
            producer.send(test);

            session.commit();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }


    }

}
