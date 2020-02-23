package com.mycom.ssmdemo.mapper.vip;

import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.vip.VipPicture;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-23 下午 12:33
 * @description：
 * @modified By：
 * @version: $
 */
public interface VipPictureMapper {

    int addPicture(Map<String, Object> params);

    int deletePic(Map<String, Object> params);

    int editPic(Map<String, Object> params);

    List<VipPicture> viewPic(Map<String, Object> params);
}
