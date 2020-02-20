package com.mycom.ssmdemo.entity.vip;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 上午 09:47
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class VipRegInfo {
    private String vipCode;
    private Date regDate;
    private Time regTime;
}
