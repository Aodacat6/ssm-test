package com.mycom.ssmdemo.mapper.shiro;

import com.mycom.ssmdemo.entity.shiro.Userforshiro;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-28 下午 05:50
 * @description：shiro框架测试
 * @modified By：
 * @version: $
 */
public interface ShiroMapper {

    Userforshiro getRolePermissionByUserName(String username);
}
