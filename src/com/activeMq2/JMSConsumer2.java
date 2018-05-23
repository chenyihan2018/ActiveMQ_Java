package com.activeMq2;/**
 * Created by chenjia on 2018/5/23.
 */

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author chenjia
 * @create 2018-05-23 15:06
 * @desc
 **/
public class JMSConsumer2 {

    public static final String USERNAME= ActiveMQConnectionFactory.DEFAULT_USER;
    public static final String PASSWORD= ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    public static final String BASEURL= ActiveMQConnectionFactory.DEFAULT_BROKER_URL;

    public static final int MESSAGENUMBER= 10;

    public static void main(String[] args){
        ConnectionFactory connectionFactory; //创建连接工厂
        Connection connection = null; //创建连接
        Session session; //创建连接会话
        Destination destination; //创建连接目的地
        MessageConsumer messageConsumer; //创建消息生产者

        connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, JMSProducer.BASEURL); //实例化连接工厂

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
//            destination = session.createQueue("fisrtQueue 1 ");//创建消息队列
            destination = session.createTopic("firset Top"); //创建消息队列
            messageConsumer = session.createConsumer(destination);
            messageConsumer.setMessageListener(new JMSLister2());
        }catch (Exception e){
            e.getMessage();
        }finally {
        }
    }

}
