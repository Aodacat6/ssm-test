package com.mycom.ssmdemo.service.user;

import com.mycom.ssmdemo.entity.user.User;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 下午 02:14
 * @description：
 * @modified By：
 * @version: $
 */
public interface UserService {

    User getUserByUserCode(String userCode);
}
