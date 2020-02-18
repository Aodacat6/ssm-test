package com.mycom.ssmdemo.utiltest.mqtest.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 下午 06:38
 * @description： MQ可以传输实体类，但是必须要序列化
 * @modified By：
 * @version: $
 */
@Data
public class User implements Serializable {

    private String name;
    private int age;
    private String sex;
}
