package com.mycom.ssmdemo.service.common;

import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-13 下午 12:33
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class TaskService {

    public void doTestTask(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println("--------------begin--------------");
        for (int i = 0; i < 10; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程:" + Thread.currentThread().getName());
                }
            });
        }
        System.out.println("--------------end--------------");
        executorService.shutdown();
    }
}
