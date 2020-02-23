package com.mycom.ssmdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.common.configuration.AliSmsConfiguration;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.thridPlugin.AliSms;
import com.mycom.ssmdemo.util.FileUpandDown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    @Autowired
    private FileUpandDown fileUpandDown;

    //private

    @GetMapping("/msg")
    public int sendMsg(){

        //AliSms aliSms = new AliSms();
        String phone = "17660635488";
        String params = "{\"CheckCode\":\"666666\"}";
        JSONObject jsonObject = JSONObject.parseObject(params);
        int response ;
        try {
            String sendType = "0";
            response = aliSms.sendMsg(phone, sendType, jsonObject);
        } catch (ClientException e) {
            e.printStackTrace();
        }
       // System.out.println(response);
        return 0;
    }

    @PostMapping("/fileup")
    public ResponseData fileUp(@RequestParam("vipCode") String vipCode, @RequestParam("file") MultipartFile multipartFile){

        String vipCode1 = vipCode;
        return fileUpandDown.fileUpload(multipartFile);
    }
    @PostMapping("/filedown")
    public void fileDown(@RequestParam("vipCode") String vipCode, HttpServletResponse response) throws IOException {

        fileUpandDown.fileDown("我爱你.txt", response);
    }

}
