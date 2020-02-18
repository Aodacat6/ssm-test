package com.mycom.ssmdemo.controller.org;

import com.mycom.ssmdemo.entity.org.OrgVipInfo;
import com.mycom.ssmdemo.service.org.OrgVipInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-18 上午 10:03
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
@RequestMapping("/org")
public class OrgInfoController {

    @Autowired
    private OrgVipInfoService orgVipInfoService;
    @GetMapping("/getorgvipinfo/{orgCode}")
    public OrgVipInfo getOrgVipInfoByOrgCode(@PathVariable String orgCode){

        return orgVipInfoService.getOrgVipInfoByOrgCode(orgCode);
    }
}
