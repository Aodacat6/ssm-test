package com.mycom.ssmdemo.service.vip;

import com.mycom.ssmdemo.common.message.CommResult;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.vip.VipInfo;

import java.util.Map;

public interface VipService {

    VipInfo queryById(Map<String,Object> params);

    CommResult insertVip(Map<String,Object> paras);

    VipInfo queryVipByMobile(Map<String, Object> params);

    CommResult insertVipRegInfo(Map<String, Object> vipRegInfo);

    ResponseData vipRegister(Map<String, Object> params);

    Map<String, Object> getMaxVipCode();

    String getMaxVipCodeBiz();

    ResponseData getCheckCode(Map<String, Object> params);

}
