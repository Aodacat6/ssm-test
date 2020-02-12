package com.mycom.ssmdemo.service.vip.impl;

import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.entity.vip.VipInfo;
import com.mycom.ssmdemo.mapper.vip.VipInfoMapper;
import com.mycom.ssmdemo.service.vip.VipService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-11 下午 04:28
 * @description：ceshilei
 * @modified By：
 * @version: 1.0$
 */
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private VipInfoMapper vipInfoMapper;
    @Override
    public VipInfo queryById(Map<String,Object> params) {
        String id = params.getOrDefault("id","").toString();
        return vipInfoMapper.queryById(id);
    }

    @Transactional
    @Override
    public int insertVip(Map<String,Object> params) {
        String name = params.getOrDefault("name","").toString();
        String sex = params.getOrDefault("sex","").toString();
        if (StringUtils.isNullOrEmpty(name)){
            throw new BizException("姓名不能为空！");
        }
        if (StringUtils.isNullOrEmpty(sex)){
            throw new BizException("性别不能为空！");
        }
        VipInfo vipInfo = new VipInfo();
        vipInfo.setName(name);
        vipInfo.setSex(sex);
        return vipInfoMapper.insertVip(vipInfo);
    }
}
