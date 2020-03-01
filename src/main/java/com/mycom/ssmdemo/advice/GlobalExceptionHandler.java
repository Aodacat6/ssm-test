package com.mycom.ssmdemo.advice;

import com.alibaba.fastjson.JSONObject;
import com.mycom.ssmdemo.common.message.ResponseData;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-13 上午 11:24
 * @description：
 * @modified By：
 * @version: $
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler(HttpServletRequest request, Exception e) {

        String errMsg = e.getMessage();
        if ((errMsg != null) && (errMsg.toUpperCase().contains("EXCEPTION"))){
            errMsg = "抱歉，程序出错了";
        }
        String jsonStr = JSONObject.toJSONString(ResponseData.error(errMsg));
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        return jsonObject;
    }
}
