package com.mycom.ssmdemo.service.vip;

import com.mycom.ssmdemo.entity.vip.VipInfo;

import java.util.Map;

public interface VipService {
    VipInfo queryById(Map<String,Object> params);
    int insertVip(Map<String,Object> paras);
}
