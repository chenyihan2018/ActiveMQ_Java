package com.activeMq;/**
 * Created by chenjia on 2018/5/23.
 */

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.apache.activemq.command.ActiveMQTempQueue;

import javax.jms.*;

/**
 * @author chenjia
 * @create 2018-05-23 14:23
 * @desc 消息生产者
 **/
public class JMSProducer {

    public static final String USERNAME= ActiveMQConnectionFactory.DEFAULT_USER;
    public static final String PASSWORD= ActiveMQConnectionFactory.DEFAULT_PASSWORD;
    public static final String BASEURL= ActiveMQConnectionFactory.DEFAULT_BROKER_URL;

    public static final int MESSAGENUMBER= 10;

    public static void main(String[] args){
        ConnectionFactory connectionFactory; //创建连接工厂
        Connection connection = null; //创建连接
        Session session; //创建连接会话
        Destination destination; //创建连接目的地
        MessageProducer messageProducer; //创建消息生产者

        connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME,JMSProducer.PASSWORD,JMSProducer.BASEURL); //实例化连接工厂
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("fisrtQueue 1 ");//创建消息队列
            messageProducer = session.createProducer(destination); //创建消息生产者
            sendMessage(session,messageProducer);
            session.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                if(connection!=null){
                    connection.close();
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * 发送消息
     * @param session
     * @param messageProducer
     * @throws Exception
     */
    public static void sendMessage(Session session , MessageProducer messageProducer)throws Exception{
        for (int i=0; i<JMSProducer.MESSAGENUMBER;i++){
            TextMessage textMessage = session.createTextMessage("发送消息队列" + i);
            System.out.println("发送消息队列1" + i);
            messageProducer.send(textMessage);
        }
    }

}