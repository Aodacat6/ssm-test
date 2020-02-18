package com.mycom.ssmdemo.entity.org;

import com.mycom.ssmdemo.entity.vip.VipInfo;
import lombok.Data;

import java.util.List;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 上午 09:36
 * @description：
 * @modified By：
 * @version: $
 */
@Data
public class OrgVipInfo {

    private String orgCode;
    private String orgName;
    private List<VipInfo> vipInfoList;

}
