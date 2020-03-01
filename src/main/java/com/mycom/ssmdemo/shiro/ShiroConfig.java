package com.mycom.ssmdemo.shiro;

//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.mgt.SecurityManager;

import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-03-01 上午 11:34
 * @description：
 * @modified By：
 * @version: $
 */
@Configuration
public class ShiroConfig {

    //据说这样不行，看看
    @Autowired
    private CustomRealm myCustomRealm;

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAAPC = new DefaultAdvisorAutoProxyCreator();
        defaultAAPC.setProxyTargetClass(true);
        return defaultAAPC;
    }

    //权限管理,配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myCustomRealm);
        //手工导入这个apache下的securitymanager包，否则返回值会报不兼容的错误
        return securityManager;
    }

    /**
     * Filter工厂，设置过滤条件和跳转条件
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        //设置登录动作相关的地址
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        //登出
        map.put("/logout", "logout");
        //对所有用户认证
        map.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
