package com.mycom.ssmdemo.service.user.impl;

import com.mycom.ssmdemo.entity.user.User;
import com.mycom.ssmdemo.mapper.user.UserInfoMapper;
import com.mycom.ssmdemo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 下午 02:14
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public User getUserByUserCode(String userCode) {
        return userInfoMapper.getUserByUserCode(userCode);
    }
}
