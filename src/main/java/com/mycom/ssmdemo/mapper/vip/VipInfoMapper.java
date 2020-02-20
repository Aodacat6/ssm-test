package com.mycom.ssmdemo.mapper.vip;

import com.mycom.ssmdemo.entity.vip.VipInfo;

import java.util.List;
import java.util.Map;

public interface VipInfoMapper {

    int insertVip(Map<String, Object> vipInfo);

    VipInfo queryById(String id);

    int updateById(int id);

    List<VipInfo> getAllVip();

    VipInfo queryVipByMobile(Map<String, Object> params);

    Map<String, Object> getMaxVipCode();
}
