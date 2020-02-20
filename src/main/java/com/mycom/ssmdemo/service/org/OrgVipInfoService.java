package com.mycom.ssmdemo.service.org;

import com.mycom.ssmdemo.entity.org.OrgInfo;
import com.mycom.ssmdemo.entity.org.OrgVipInfo;
import com.mycom.ssmdemo.mapper.org.OrgVipInfoMapper;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 上午 09:59
 * @description：
 * @modified By：
 * @version: $
 */
public interface OrgVipInfoService {

    OrgVipInfo getOrgVipInfoByOrgCode(String orgCode);
    OrgInfo getOrgInfoByOrgCode(String orgCode);
}
