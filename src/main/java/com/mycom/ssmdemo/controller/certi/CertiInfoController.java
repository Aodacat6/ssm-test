package com.mycom.ssmdemo.controller.certi;

import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.service.certi.CertiInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-03-02 下午 06:07
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/certi")
public class CertiInfoController {

    @Autowired
    private CertiInfoService certiInfoService;

    @PostMapping("/apply")
    public ResponseData appleCerti(Map<String, Object> params){
        return certiInfoService.applyCerti(params);
    }
}
