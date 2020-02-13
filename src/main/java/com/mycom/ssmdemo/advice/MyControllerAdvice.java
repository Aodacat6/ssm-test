package com.mycom.ssmdemo.advice;

import com.alibaba.fastjson.JSONObject;
import com.mycom.ssmdemo.common.message.ResponseData;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-13 上午 11:05
 * @description：返回数据测试
 * @modified By：
 * @version: $
 */
@ControllerAdvice("com.mycom.ssmdemo.controller")
public class MyControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (o instanceof ResponseData){
            ResponseData responseData = (ResponseData) o;
            String json = JSONObject.toJSONString(responseData);
            JSONObject jsonObject = JSONObject.parseObject(json);

            jsonObject.put("sign", "6666");
            return jsonObject;

        }

        return o;
    }
}
