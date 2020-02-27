package com.mycom.ssmdemo.service.vip;

import com.mycom.ssmdemo.common.message.CommResult;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.vip.VipInfo;
import com.mycom.ssmdemo.entity.vip.VipPicture;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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

    ResponseData addPicture(String vipCode, MultipartFile multipartFile);

    CommResult deletePic(Map<String, Object> params);

    ResponseData editPic(String vipCode, MultipartFile multipartFile);

    void viewPic(String vipCode, HttpServletResponse response) throws IOException;

    ResponseData verifyVip(Map<String, Object> params);

}
