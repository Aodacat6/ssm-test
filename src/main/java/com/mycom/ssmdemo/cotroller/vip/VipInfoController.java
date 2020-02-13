package com.mycom.ssmdemo.cotroller.vip;

import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.vip.VipInfo;
import com.mycom.ssmdemo.service.vip.VipService;
import com.mycom.ssmdemo.util.LoggerUtils;
import com.mycom.ssmdemo.util.RedisUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/vip")
public class VipInfoController {

    @Autowired
    VipService vipService;
    @Autowired
    RedisUtils redisUtils;
    @GetMapping("/test/{msg}")
    public String test(@PathVariable String msg){
        return msg;
    }

    @PostMapping("/posttest")
    public Map<String,String> postTest(@RequestParam Map<String,String> params){
        return params;
    }

    @PostMapping("/querybyid")
    public ResponseData queryById(@RequestParam Map<String,Object> params){
        VipInfo vipInfo = vipService.queryById(params);
        Map<String,Object> map = new HashMap<>();
        //List<Map<String,Object>> list = new ArrayList<>();
        map.put("name", vipInfo.getName());
        map.put("sex", vipInfo.getSex());
        //list.add(map);
        //return ResponseData.okData("data",list);
        return ResponseData.okData("data",map);
    }

    @PostMapping("/insertvip")
    public ResponseData insertVip(@RequestParam Map<String,Object> params ){
        LoggerUtils.getLogger().info(params.toString());
        vipService.insertVip(params);
        return ResponseData.ok();
    }

    @GetMapping("/redisinserttest")
    public Boolean redisInsertTest(@RequestParam String key, String value){
        return redisUtils.set(key,value);
    }

    @GetMapping("/redisquerytest")
    public String redisQueryTest(@RequestParam String key){
        return redisUtils.get(key).toString();
    }
}
