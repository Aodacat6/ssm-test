package com.mycom.ssmdemo.service.certi;

import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.certi.CertiInfo;

import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-03-02 下午 06:01
 * @description：
 * @modified By：
 * @version: $
 */
public interface CertiInfoService {

    ResponseData applyCerti(Map<String, Object> params);

    ResponseData qryCertiByUser(Map<String, Object> params);

    CertiInfo getMaxCertiNo();
}
