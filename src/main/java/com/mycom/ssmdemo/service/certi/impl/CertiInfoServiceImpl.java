package com.mycom.ssmdemo.service.certi.impl;

import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.certi.CertiInfo;
import com.mycom.ssmdemo.mapper.certi.CertiInfoMapper;
import com.mycom.ssmdemo.service.certi.CertiInfoService;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-03-02 下午 06:04
 * @description：
 * @modified By：
 * @version: $
 */
@Service
public class CertiInfoServiceImpl implements CertiInfoService {

    @Autowired
    private CertiInfoMapper certiInfoMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseData applyCerti(Map<String, Object> params) {

        String applyUser = params.getOrDefault("username", "").toString();

        if (StringUtils.isNullOrEmpty(applyUser)){
            throw new BizException("申请人不能为空！");
        }

        return null;
    }

    @Override
    public ResponseData qryCertiByUser(Map<String, Object> params) {
        return null;
    }

    @Override
    public CertiInfo getMaxCertiNo() {
        return certiInfoMapper.getMaxCertiNo();
    }
}
