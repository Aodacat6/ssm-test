package com.mycom.ssmdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.mycom.ssmdemo.thridPlugin.AliSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-20 下午 10:59
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AliSms aliSms;

    @GetMapping("/msg")
    public int sendMsg(){
        AliSms aliSms = new AliSms();
        String phone = "17660635488";
        String params = "{\"CheckCode\":\"666666\"}";
        JSONObject jsonObject = JSONObject.parseObject(params);
        SendSmsResponse response = null;
        try {
            response = aliSms.sendMsg(phone, jsonObject);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        System.out.println(response.toString());
        return 0;
    }
}
