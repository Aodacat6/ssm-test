package com.mycom.ssmdemo.aop;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.mycom.ssmdemo.thridPlugin.AliSms;
import com.mycom.ssmdemo.util.LoggerUtils;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private AliSms aliSms;

    @Pointcut("execution( * com.mycom.ssmdemo.controller.vip.VipInfoController.*(..))")
    public void preparePointcut(){

    }

    @Pointcut("execution( * com.mycom.ssmdemo.controller.vip.VipInfoController.registerVip(..))")
    public void afterRegPointcut(){

    }

    @Before("preparePointcut()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("-----------------in----------------------");
        Object[] obj = joinPoint.getArgs();
        String key = obj[0].toString();
        LoggerUtils.getLogger().info("receive:" + key);
        System.out.println("-----------------out----------------------");
    }

    /**
     * 获取输入参数和返回参数
     * @param joinPoint
     * @param rvt
     * @throws ClientException
     */
    @AfterReturning(pointcut = "afterRegPointcut()", returning = "rvt")
    public void doAfterReg(JoinPoint joinPoint, Object rvt) throws ClientException {
        Object[] args = joinPoint.getArgs();
        Map<String, Object> map = (Map<String, Object>)args[0];
        String mobile = map.get("mobile").toString();
        String password = map.get("password").toString();
        String rvtJson = JSONObject.toJSON(rvt).toString();
        JSONObject rvtJsonObj = (JSONObject) JSONObject.parse(rvtJson);
        JSONObject metaJsonObj = (JSONObject) rvtJsonObj.get("meta");
        String vipCode = metaJsonObj.get("vipCode").toString();

        /**
         * 下面这一块发送通知短信的业务注释了
         * 因为我的账号是个人账号，只能发验证码，发不了通知短信
         * 如果有通知业务的话可以放开
        /*
        String sendType = "1";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("PassWord", password);
        jsonObject.put("CardFaceNo", vipCode);

        int result = aliSms.sendMsg(mobile, sendType, jsonObject);
        if (result == 1){
            //短信发送失败，这里可以加一下业务
            //比如存到数据库或者rids啥的
        }

         */
    }
}
