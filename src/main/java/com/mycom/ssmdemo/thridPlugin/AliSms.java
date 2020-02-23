package com.mycom.ssmdemo.thridPlugin;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.mycom.ssmdemo.common.configuration.AliSmsConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-20 下午 08:58
 * @description：阿里短信平台
 * @modified By：
 * @version: $
 */

@Component
public class AliSms {

    protected static Logger logger = LoggerFactory.getLogger(AliSms.class);

    @Autowired
    private AliSmsConfiguration aliSmsConfiguration;

    /**
     * 发送短信
     * @return
     */
    public int sendMsg(String phone, String smsType, JSONObject params)
            throws ClientException {

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsConfiguration.getAccessKeyId(), aliSmsConfiguration.getAccessSecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(aliSmsConfiguration.getSignName());
        //必填:短信模板-可在短信控制台中找到
        //0-验证码  1-发卡通知
        if ("0".equals(smsType)){
            request.setTemplateCode(aliSmsConfiguration.getTemplateCode_CheckCode());
        }else if ("1".equals(smsType)){
            request.setTemplateCode(aliSmsConfiguration.getTemplateCode_FK());
        }
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(params.toJSONString());
        request.setOutId(UUID.randomUUID().toString());
        //请求失败这里会抛ClientException异常
        SendSmsResponse response = acsClient.getAcsResponse(request);
        logger.info("AliSmsResponse:" + response.toString());
        if (!"OK".equals(response.getCode())){
            return 1;
        }
        return 0;
    }


}
