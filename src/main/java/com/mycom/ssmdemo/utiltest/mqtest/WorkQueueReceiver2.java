package com.mycom.ssmdemo.utiltest.mqtest;

import com.mycom.ssmdemo.util.RabbitMqUtils;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 06:14
 * @description：
 * @modified By：
 * @version: $
 */
@Component
@RabbitListener(queues = RabbitMqUtils.queueName)
public class WorkQueueReceiver2 {

    @RabbitHandler
    public void process(String hello){
        System.out.println("Receive2:" + hello);
    }
}
