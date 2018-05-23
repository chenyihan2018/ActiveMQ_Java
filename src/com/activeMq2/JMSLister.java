package com.activeMq2;/**
 * Created by chenjia on 2018/5/23.
 */

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author chenjia
 * @create 2018-05-23 15:03
 * @desc
 **/
public class JMSLister implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("监听收到的消息1"+((TextMessage)message).getText());
        }catch (Exception e){

        }
    }

}
