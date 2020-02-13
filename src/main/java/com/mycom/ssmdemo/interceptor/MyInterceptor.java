package com.mycom.ssmdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.util.LoggerUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-13 上午 10:01
 * @description：拦截器实例,yw操作
 * @modified By：
 * @version: $
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUri = request.getRequestURI();
        Map<String, String[]> requestParams = request.getParameterMap();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Api", requestUri);
        jsonObject.put("ReqParams", requestParams);

        LoggerUtils.getLogger().info("ReceiveData: " + jsonObject.toString());
        //进行业务校验
        String token = requestParams.getOrDefault("token", null)[0];
        if ("6666".equals(token)) {
            return true;
        }
        throw new BizException("token验证失败！");
    }

}
