package com.mycom.ssmdemo.mapper.certi;

import com.mycom.ssmdemo.entity.certi.CertiInfo;

import java.util.List;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-03-02 下午 05:32
 * @description：
 * @modified By：
 * @version: $
 */
public interface CertiInfoMapper {

    int addCertiInfo(Map<String, String> map);

    List<CertiInfo> qryCertiInfo(Map<String, String> map);

    CertiInfo getMaxCertiNo();
}
