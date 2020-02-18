package com.mycom.ssmdemo.utiltest.mqtest;

import com.mycom.ssmdemo.util.RabbitMqUtils;
import com.mycom.ssmdemo.utiltest.mqtest.entity.User;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 06:42
 * @description：
 * @modified By：
 * @version: $
 */
@Component
//@RabbitListener(queues = RabbitMqUtils.queueName)
public class EntityReceice {

    //@RabbitHandler
    public void process(User user){
        System.out.println("Receive:" + user.getName() + ";" + user.getAge());
    }
}
