package com.mycom.ssmdemo.utiltest.mqtest;

//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.mycom.ssmdemo.util.RabbitMqUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 03:04
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class DirectExchangeSender {

    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(){
        String sendMsg = "send:" + new Date();

        System.out.println(sendMsg);

        rabbitTemplate1.convertAndSend("direct", sendMsg);
    }
}
