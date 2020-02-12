package com.mycom.ssmdemo.aop;

import com.mycom.ssmdemo.util.LoggerUtils;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-12 下午 07:48
 * @description：aop测试
 * @modified By：
 * @version: $
 */
@Component
@Aspect
public class SingAOP {

    @Before("execution( * com.mycom.ssmdemo.cotroller.vip.VipInfoController.*(..))")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("-----------------in----------------------");
        Object[] obj = joinPoint.getArgs();
        String key = obj[0].toString();
        LoggerUtils.getLogger().info("输入：key:" + key);
        System.out.println("-----------------out----------------------");
    }
}
