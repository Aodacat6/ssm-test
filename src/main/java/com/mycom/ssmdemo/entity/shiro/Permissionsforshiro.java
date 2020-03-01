package com.mycom.ssmdemo.entity.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * (Permissionsforshiro)实体类
 *
 * @author makejava
 * @since 2020-02-27 17:54:36
 */
@Data
public class Permissionsforshiro implements Serializable {

    private static final long serialVersionUID = 684514837671403171L;
    
    private Integer permissionID;
    
    private String permissionsName;


}