package com.mycom.ssmdemo.mapper.org;

import com.mycom.ssmdemo.entity.org.OrgInfo;
import com.mycom.ssmdemo.entity.org.OrgVipInfo;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 上午 09:39
 * @description：
 * @modified By：
 * @version: $
 */
public interface OrgVipInfoMapper {
    OrgVipInfo getOrgVipInfoByOrgCode(String orgCode);
    OrgInfo getOrgInfoByOrgCode(String orgCode);
}
