package com.mycom.ssmdemo.utiltest.mqtest;

import com.mycom.ssmdemo.utiltest.mqtest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 03:43
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/mqtest")
public class MqTestController {

    @Autowired
    private SimpleSender simpleSender;
    @Autowired
    private WorkQueueSender workQueueSender;
    @Autowired
    private EnetiySend enetiySend;
    @Autowired
    private DirectExchangeSender directExchangeSender;

    @GetMapping("/direct")
    public int directExchange(){
        directExchangeSender.send();
        return 1;
    }

    @GetMapping("/simple")
    public int simpleModel(){
        simpleSender.send();
        return 1;
    }
    @GetMapping("/workqueue")
    public int workQueueSender(){
        for (int i=0; i<31; i++ ) {
            workQueueSender.send();
        }
        return 1;
    }
    @GetMapping("/entity")
    public int entityQueueSender(){
        User user = new User();
        user.setAge(18);
        user.setName("万里扬");
        enetiySend.send(user);

        return 1;
    }

}
