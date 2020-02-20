package com.mycom.ssmdemo.service.org.impl;

import com.mycom.ssmdemo.entity.org.OrgInfo;
import com.mycom.ssmdemo.entity.org.OrgVipInfo;
import com.mycom.ssmdemo.mapper.org.OrgVipInfoMapper;
import com.mycom.ssmdemo.service.org.OrgVipInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 上午 09:59
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class OrgVipInfoImpl implements OrgVipInfoService {

    protected final static Logger logger =
            LoggerFactory.getLogger(OrgVipInfoImpl.class);
    @Autowired
    private OrgVipInfoMapper orgVipInfoMapper;
    @Override
    public OrgVipInfo getOrgVipInfoByOrgCode(String orgCode) {
        logger.info(orgCode);
        OrgVipInfo orgVipInfo = new OrgVipInfo();
        orgVipInfo = orgVipInfoMapper.getOrgVipInfoByOrgCode(orgCode);
        return orgVipInfo;
    }

    @Override
    public OrgInfo getOrgInfoByOrgCode(String orgCode) {
        return orgVipInfoMapper.getOrgInfoByOrgCode(orgCode);
    }
}
