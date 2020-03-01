package com.mycom.ssmdemo.service.shiro;

import com.mycom.ssmdemo.entity.shiro.Userforshiro;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-28 下午 07:44
 * @description：
 * @modified By：
 * @version: $
 */
public interface ShiroTestService {

    Userforshiro getRolePermissionByUserName (String username);
}
