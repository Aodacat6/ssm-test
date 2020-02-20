package com.mycom.ssmdemo.mapper.user;

import com.mycom.ssmdemo.entity.user.User;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 上午 10:07
 * @description：
 * @modified By：
 * @version: $
 */
public interface UserInfoMapper {
    User getUserByUserCode(String userCode);
}
