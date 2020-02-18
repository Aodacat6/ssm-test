package com.mycom.ssmdemo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 02:53
 * @description：rabbitmq测试，该类的作用是
 *              创建队列、创建路由器、队列绑定路由器
 * @modified By：
 * @version: $
 */
@Configuration
//消息处理方法参数是由 MessageConverter 转化，若使用自定义 MessageConverter
// 则需要在 RabbitListenerContainerFactory 实例中去设置
// （默认 Spring 使用的实现是 SimpleRabbitListenerContainerFactory）
public class RabbitMqUtils {

    private static Logger logger = LoggerFactory.getLogger(RabbitMqUtils.class);
    @Autowired
    private CachingConnectionFactory connectionFactory;
    public static final String queueName = "helloQueue";

    /**
     * 返回值为Queue的方法作用是在项目启动时创建queue
     * @return
     */
    @Bean
    public Queue helloQueue(){
        return new Queue(queueName);
    }
    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }
    /*
           以下是direct类型exchange用到的东西
           一个queue
           一个exchange
           一个绑定关系
     */
    @Bean
    public Queue dirQueue(){
        return new Queue("direct");
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }
    /*
        将队列dirQueue与directExchange交换机绑定，routing_key为direct
     */
    @Bean
    public Binding bindingExchangeDirect(@Qualifier("dirQueue") Queue dirQueue, DirectExchange directExchange){
        return BindingBuilder.bind(dirQueue).to(directExchange).with("direct");
    }
    /*
            以上是direct类型的exchange配置
     */
    @Bean
    public RabbitTemplate rabbitTemplate(){
        //若使用confirm-callback或return-callback，必须要配置publisherConfirms或publisherReturns为true
        //每个rabbitTemplate只能有一个confirm-callback和return-callback，如果这里配置了，那么写生产者的时候不能再写confirm-callback和return-callback
        //使用return-callback时必须设置mandatory为true，或者在配置中设置mandatory-expression的值为true
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);

//        /**
//         * 如果消息没有到exchange,则confirm回调,ack=false
//         * 如果消息到达exchange,则confirm回调,ack=true
//         * exchange到queue成功,则不回调return
//         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
//         */
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                //b: ack
                if (b){
                    logger.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,b,s);
                }else{
                    logger.info("消息发送失败:correlationData({}),ack({}),cause({})",correlationData,b,s);
                }
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                logger.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",s1,s2,i,s,message);
            }
        });

        return rabbitTemplate;
    }
}
