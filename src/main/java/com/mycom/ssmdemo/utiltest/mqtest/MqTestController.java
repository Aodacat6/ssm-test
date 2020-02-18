package com.mycom.ssmdemo.utiltest.mqtest;

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

}
