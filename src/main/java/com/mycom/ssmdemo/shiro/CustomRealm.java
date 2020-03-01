package com.mycom.ssmdemo.shiro;

import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.entity.shiro.Permissionsforshiro;
import com.mycom.ssmdemo.entity.shiro.Roleforshiro;
import com.mycom.ssmdemo.entity.shiro.Userforshiro;
import com.mycom.ssmdemo.service.shiro.ShiroTestService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-03-01 上午 10:16
 * @description： realm:查询用户角色的权限信息，并将权限保存到权限管理器
 * ******************************************************************
 * AuthorizationInfo属于角色权限管理
 * AuthenticationInfo属于账号密码认证（登录认证）
 * *****************************************************************
 * @modified By：
 * @version: $
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ShiroTestService shiroTestService;

    /**
     * AuthorizationInfo 身份授权，即将权限信息保存到权限管理器
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取登录ID
        String username = principalCollection.getPrimaryPrincipal().toString();
        //查询出该用户 的所有权限信息
        Userforshiro user = shiroTestService.getRolePermissionByUserName(username);
        if (user == null){
            throw new BizException("用户信息不存在！");
        }
        //SimpleAuthorizationInfo 角色权限管理器
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将角色信息和权限信息依次添加到 管理器中
        for (Roleforshiro role : user.getRoleList()){
            //每次循环，将角色信息添加到管理器
            simpleAuthorizationInfo.addRole(role.getRoleID().toString());
            //每次循环，将权限信息添加到管理器
            for (Permissionsforshiro permissions : role.getPermissionsList()){
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionID().toString());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * AuthenticationInfo 身份认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //这一步的目的是在post请求时会先进行认证，然后再到请求
        if (authenticationToken.getPrincipal() == null){
            return null;
        }
        //获取传入的用户信息
        String username = authenticationToken.getPrincipal().toString();
        //获取用户所有信息
        Userforshiro user = shiroTestService.getRolePermissionByUserName(username);
        if (user == null){
            throw new BizException("用户信息不存在！");
        }
        //验证传入用户信息
        //SimpleAuthenticationInfo 身份认证器
        //用户名密码
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                username, user.getPassword(), "CustomRealm"
        );
        return simpleAuthenticationInfo;
    }
}
