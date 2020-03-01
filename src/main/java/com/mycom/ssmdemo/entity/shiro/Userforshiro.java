package com.mycom.ssmdemo.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Userforshiro)实体类
 *
 * @author makejava
 * @since 2020-02-27 17:52:53
 */
@Data
public class Userforshiro implements Serializable {

    private static final long serialVersionUID = -58084054706847982L;
    
    private Integer userID;
    
    private String username;
    
    private String password;

    private List<Roleforshiro> roleList;


}