package com.mycom.ssmdemo.utiltest.mqtest;

import com.mycom.ssmdemo.util.RabbitMqUtils;
import com.mycom.ssmdemo.utiltest.mqtest.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 06:41
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class EnetiySend {

    @Autowired
    private RabbitTemplate rabbitTemplate1;

    public void send(User user){
        String sendMsg = "send:" + user.getName() + ";" + user.getAge();

        System.out.println(sendMsg);

        rabbitTemplate1.convertAndSend(RabbitMqUtils.queueName, user);
    }
}
