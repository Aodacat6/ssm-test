package com.mycom.ssmdemo.entity.vip;

import lombok.Data;

@Data
public class VipInfo {
    private int id;
    private String name;
    private String sex;
    private String orgCode;
    private String orgName;
    private String userCode;
    private String userName;
    private String vipBirthday;
    private String province;
    private String city;
    private String district;
    private String mobile;
    /*
         transient关键字修饰属性值，序列化时会自动忽略
     */
    private transient String password;
    private String vipSource;
    private String vipCode;
}
