package com.mycom.ssmdemo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-13 上午 09:57
 * @description：拦截器实例，业务处理之前
 * @modified By：
 * @version: $
 */
@Configuration
public class MyWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor myInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //拦截路径
        String[] includePathPattern = {
                "/**"
        };
        //放行路径
        String[] excludePathPattern = {

        };
        //注册自定义的拦截器
        registry.addInterceptor(myInterceptor)
                .addPathPatterns(includePathPattern)
                .excludePathPatterns(excludePathPattern);
    }
}
