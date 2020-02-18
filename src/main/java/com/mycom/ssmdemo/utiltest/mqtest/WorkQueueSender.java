package com.mycom.ssmdemo.utiltest.mqtest;

import com.mycom.ssmdemo.util.RabbitMqUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 06:13
 * @description： 工作队列模式。一个生产者对应多个消费者
 *                  多个消费者平均消费消息
 * @modified By：
 * @version: $
 */
@Component
public class WorkQueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(){
        String sendMsg = "send:" + new Date();

        System.out.println(sendMsg);

        rabbitTemplate1.convertAndSend(RabbitMqUtils.queueName, sendMsg);
    }
}
