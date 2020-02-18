package com.mycom.ssmdemo.utiltest.mqtest;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 03:04
 * @description： mq简单队列模式消费者
 * @modified By：
 * @version: $
 */
@Component
//@rabbitlistener配合@rabbithandler注解用于监听队列消息
//@RabbitListener监听队列，当队列有消息时会自动获取
//@RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法
// 处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
//@RabbitListener(queues = "direct")
public class DirectExchangeReceiver {

    //@RabbitHandler
    public void process(String hello){
        System.out.println("Receive:" + hello);
    }
}
