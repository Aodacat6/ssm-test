package com.mycom.ssmdemo.entity.shiro;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Roleforshiro)实体类
 *
 * @author makejava
 * @since 2020-02-27 17:54:19
 */
@Data
public class Roleforshiro implements Serializable {

    private static final long serialVersionUID = -32682357986688972L;
    
    private Integer roleID;
    
    private String roleName;

    private List<Permissionsforshiro> permissionsList;

}