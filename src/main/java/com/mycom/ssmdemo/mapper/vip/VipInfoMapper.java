package com.mycom.ssmdemo.mapper.vip;

import com.mycom.ssmdemo.entity.vip.VipInfo;

import java.util.List;
import java.util.Map;

public interface VipInfoMapper {
    int insertVip(VipInfo vipInfo);
    VipInfo queryById(String id);
    int updateById(int id);
    List<VipInfo> getAllVip();
}
