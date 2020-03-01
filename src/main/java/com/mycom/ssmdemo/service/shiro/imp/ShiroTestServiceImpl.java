package com.mycom.ssmdemo.service.shiro.imp;

import com.mycom.ssmdemo.entity.shiro.Userforshiro;
import com.mycom.ssmdemo.mapper.shiro.ShiroMapper;
import com.mycom.ssmdemo.service.shiro.ShiroTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-28 下午 07:46
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class ShiroTestServiceImpl implements ShiroTestService {

    @Autowired
    private ShiroMapper shiroMapper;
    @Override
    public Userforshiro getRolePermissionByUserName(String username) {
        return shiroMapper.getRolePermissionByUserName(username);
    }
}
