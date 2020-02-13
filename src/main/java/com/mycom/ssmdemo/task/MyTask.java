package com.mycom.ssmdemo.task;

import com.mycom.ssmdemo.service.common.TaskService;
import com.mycom.ssmdemo.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-13 下午 12:36
 * @description：
 * @modified By：
 * @version: $
 */
@Component
public class MyTask {

    @Autowired
    TaskService taskService;

    @Scheduled(fixedDelay = 10000)
    public void doSomething(){
        LoggerUtils.getLogger().info("----------定时任务开始----------");
        taskService.doTestTask();
        LoggerUtils.getLogger().info("----------定时任务结束----------");
    }
}
