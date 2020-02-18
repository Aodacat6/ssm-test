package com.mycom.ssmdemo.utiltest.mqtest;

//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.mycom.ssmdemo.util.RabbitMqUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 03:04
 * @description： mq简单队列模式生产者
 * @modified By：
 * @version: $
 */
@Component
public class SimpleSender {

    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(){
        String sendMsg = "send:" + new Date();

        System.out.println(sendMsg);

        rabbitTemplate1.convertAndSend(RabbitMqUtils.queueName, sendMsg);
    }
}
