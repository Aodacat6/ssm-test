package com.mycom.ssmdemo.service.vip.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.common.commonutil.CommonUtils;
import com.mycom.ssmdemo.common.commonutil.DateUtils;
import com.mycom.ssmdemo.common.commonutil.EntryUtils;
import com.mycom.ssmdemo.common.message.CommResult;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.org.OrgInfo;
import com.mycom.ssmdemo.entity.user.User;
import com.mycom.ssmdemo.entity.vip.VipInfo;
import com.mycom.ssmdemo.entity.vip.VipPicture;
import com.mycom.ssmdemo.mapper.vip.VipInfoMapper;
import com.mycom.ssmdemo.mapper.vip.VipPictureMapper;
import com.mycom.ssmdemo.mapper.vip.VipRegInfoMapper;
import com.mycom.ssmdemo.service.org.OrgVipInfoService;
import com.mycom.ssmdemo.service.user.UserService;
import com.mycom.ssmdemo.service.vip.VipService;
import com.mycom.ssmdemo.thridPlugin.AliSms;
import com.mycom.ssmdemo.util.FileUpandDown;
import com.mycom.ssmdemo.util.LoggerUtils;
import com.mycom.ssmdemo.util.RedisUtils;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-11 下午 04:28
 * @description：ceshilei
 * @modified By：
 * @version: 1.0$
 */
@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private VipInfoMapper vipInfoMapper;
    @Autowired
    private VipRegInfoMapper vipRegInfoMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private OrgVipInfoService orgVipInfoService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private AliSms aliSms;
    @Autowired
    private VipPictureMapper vipPictureMapper;
    @Autowired
    private FileUpandDown fileUpandDown;

    /**
     * 会员注册入口
     * @param params
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseData vipRegister(Map<String, Object> params) {

        String name = params.getOrDefault("name","").toString();
        String sex = params.getOrDefault("sex","").toString();
        String orgCode = params.getOrDefault("orgCode", "").toString();
        //String orgName = params.getOrDefault("orgName", "").toString();
        String userCode = params.getOrDefault("userCode", "").toString();
        //String userName = params.getOrDefault("userName", "").toString();
        String vipBirthday = params.getOrDefault("vipBirthday", "").toString();
        String province = params.getOrDefault("province", "").toString();
        String city = params.getOrDefault("city", "").toString();
        String district = params.getOrDefault("district", "").toString();
        String mobile = params.getOrDefault("mobile", "").toString();
        String password = params.getOrDefault("password", "").toString();
        String vipSource = params.getOrDefault("vipSource", "").toString();
        //String vipCode = params.getOrDefault("vipCode", "").toString();
        if (StringUtils.isNullOrEmpty(name)){
            throw new BizException("姓名不能为空！");
        }
        if (StringUtils.isNullOrEmpty(sex)){
            throw new BizException("性别不能为空！");
        }
        if ((!Objects.equals(sex, "0")) && (!Objects.equals(sex, "1")) && (!Objects.equals(sex, "2"))){
            throw new BizException("性别值不合法，只能是0、1、2");
        }
        if(StringUtils.isNullOrEmpty(orgCode)){
            throw new BizException("组织编码不能为空！");
        }
        if(StringUtils.isNullOrEmpty(userCode)){
            throw new BizException("用户编码不能为空！");
        }
        if (StringUtils.isNullOrEmpty(mobile)){
            throw new BizException("手机号不能为空！");
        }
        if (!CommonUtils.isMobile(mobile)) {
            throw new BizException("手机号不合法，请输入正确的手机号！");
        }
        if (StringUtils.isNullOrEmpty(password)){
            throw new BizException("密码不能为空！");
        }
        if (!CommonUtils.isNumberic(password)){
            throw new BizException("密码必须为数字！");
        }
        if (password.length() != 6){
            throw new BizException("密码必须为六位数字！");
        }
        //密码加密处理
        try {
            password = EntryUtils.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询用户是否存在，并返回用户信息
        User user = userService.getUserByUserCode(userCode);
        if (user == null){
            throw new BizException("用户编码不存在！");
        }
        String userName = user.getUserName();

        //查询组织是否存在，并返回组织信息
        OrgInfo orgInfo = orgVipInfoService.getOrgInfoByOrgCode(orgCode);
        if (orgInfo == null){
            throw new BizException("组织编码不存在！");
        }
        String orgName = orgInfo.getOrgName();
        //查询手机号是否存在

        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);

        VipInfo vipInfo = queryVipByMobile(map);
        if (vipInfo != null){
            throw new BizException("手机号已注册会员，不能再次注册！");
        }
        /*
        VipInfo vipInfo = new VipInfo();
        vipInfo.setName(name);
        vipInfo.setSex(sex);
         */
        Map<String, Object> vipMap = new HashMap<>();
        vipMap.put("name", name);
        vipMap.put("sex", sex);
        vipMap.put("orgCode", orgCode);
        vipMap.put("orgName", orgName);
        vipMap.put("userCode", userCode);
        vipMap.put("userName", userName);
        vipMap.put("vipBirthday", vipBirthday);
        vipMap.put("province", province);
        vipMap.put("city", city);
        vipMap.put("district", district);
        vipMap.put("mobile", mobile);
        vipMap.put("password", password);
        vipMap.put("vipSource", vipSource);
        //vipMap.put("vipCode", vipCode);
        CommResult commResult = insertVip(vipMap);
        if (commResult.getCode() != 0){
            throw new BizException("会员信息插入失败：" + commResult.getMsg());
        }
        Map<String, Object> map1 = (Map<String, Object>)commResult.getData();
        //VipInfo vipInfo1 = (VipInfo)commResult.getData();
        String vipCode = map1.get("vipCode").toString();
        Map<String, Object> regMap = new HashMap<>();
        regMap.put("vipCode", vipCode);
        commResult = insertVipRegInfo(regMap);
        if (commResult.getCode() != 0){
            throw new BizException("会员注册信息插入失败：" + commResult.getMsg());
        }

        return ResponseData.okData("vipCode", vipCode);
    }


    @Override
    public VipInfo queryById(Map<String,Object> params) {
        String id = params.getOrDefault("id","").toString();
        return vipInfoMapper.queryById(id);
    }


    @Override
    public CommResult insertVip(Map<String,Object> params) {
        try {
            String newVipCode = getMaxVipCodeBiz();
            params.put("vipCode", newVipCode);
            vipInfoMapper.insertVip(params);
            return CommResult.ok(params);
        }catch (Exception e){
            return CommResult.error(e.getMessage());
        }

    }

    @Override
    public VipInfo queryVipByMobile(Map<String, Object> params) {
        return vipInfoMapper.queryVipByMobile(params);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommResult insertVipRegInfo(Map<String, Object> vipRegInfo) {

        try {
            vipRegInfo.put("regDate", DateUtils.getDate());
            vipRegInfo.put("regTime", DateUtils.getTime());
            vipRegInfoMapper.insertVipRegInfo(vipRegInfo);
            return CommResult.ok(vipRegInfo);
        }catch (Exception e){
            return CommResult.error(e.getMessage());
        }

    }

    @Override
    public Map<String, Object> getMaxVipCode() {

        return vipInfoMapper.getMaxVipCode();
    }

    /**
     * 获取会员码，8位递增顺序号 + 4位随机码
     * @return
     */
    @Override
    public synchronized String getMaxVipCodeBiz() {
        long vipCode = 0L;
        if (redisUtils.hasKey("vipCode")){
            vipCode = Long.valueOf(redisUtils.get("vipCode").toString());
        }else {
            Map<String, Object> map = getMaxVipCode();
            if (null == map){
                vipCode = 88880000L;
            }
            String getCode = map.getOrDefault("vipCode", "0").toString();
            if ("0".equals(getCode)){
                vipCode = 88880000L;
            }else {
                vipCode = Long.valueOf(getCode.substring(0,8));
            }
        }
        vipCode = vipCode + 1;
        if (!redisUtils.set("vipCode", new Long(vipCode).toString())){
            throw new BizException("设置vipcode异常！");
        }
        //随机生成一个4位数
        String random = new Random().nextInt(8999) + 1000 + "";
        return Long.valueOf(vipCode).toString() + random;
    }

    @Override
    public ResponseData getCheckCode(Map<String, Object> params) {

        String mobile = params.getOrDefault("mobile", "").toString();
        if (StringUtils.isNullOrEmpty(mobile)){
            throw new BizException("传入的手机号不能为空！");
        }
        if (!CommonUtils.isMobile(mobile)){
            throw new BizException("传入的手机号不符合规则！");
        }
        String key = "mo" + mobile;
        if (redisUtils.hasKey(key)){
            throw new BizException("短信发送太频繁，请稍后再试！");
        }
        String checkCode = CommonUtils.randomNum();
        long expireTime = 300L;  //300秒
        if (!redisUtils.setandExpire(key, checkCode, expireTime)){
            throw new BizException("验证码设置失败！");
        }
        String sendType = "0";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("CheckCode", checkCode);
        int result = 1;
        try {
            result = aliSms.sendMsg(mobile, sendType, jsonObject);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (result != 0){
            throw new BizException("短信发送异常！");
        }

        return ResponseData.okData("checkCode", checkCode);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseData addPicture(String vipCode, MultipartFile multipartFile) {

        if(StringUtils.isNullOrEmpty(vipCode)){
            throw new BizException("传入的vipCode不能为空！");
        }
        CommResult commResult = fileUpandDown.fileUpload(multipartFile);
        if (commResult.getCode() != 0){
            throw new BizException("文件保存失败！");
        }
        Map<String, Object> map = (Map<String, Object>) commResult.getData();
        String fileName = map.get("fileName").toString();
        String fileDir = map.get("fileDir").toString();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("vipCode", vipCode);
        map1.put("fileName", fileName);
        map1.put("fileDir", fileDir);

        int result = vipPictureMapper.addPicture(map1);
        if (result != 1){
            throw new BizException("文件信息保存到数据库失败！");
        }
        return ResponseData.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommResult deletePic(Map<String, Object> params) {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseData editPic(String vipCode, MultipartFile multipartFile) {

        if(StringUtils.isNullOrEmpty(vipCode)){
            throw new BizException("传入的vipCode不能为空！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("vipCode", vipCode);
        List<VipPicture> list = vipPictureMapper.viewPic(map);
        if (list == null){
            throw new BizException("查询图片出错，原文件可能不存在！");
        }

        CommResult commResult = fileUpandDown.fileUpload(multipartFile);
        if (commResult.getCode() != 0){
            throw new BizException("文件保存失败！");
        }

        String fileDir =  list.get(0).getFileDir();
        String fileName = list.get(0).getFileName();
        //更新先删除原文件
        String filePath = fileDir;
        File file = new File(fileDir);
        if (file.exists()){
            file.delete();
        }
        Map<String, Object> map1 = (Map<String, Object>) commResult.getData();
        fileName = map1.get("fileName").toString();
        fileDir = map1.get("fileDir").toString();

        Map<String, Object> map2 = new HashMap<>();
        map2.put("vipCode", vipCode);
        map2.put("fileName", fileName);
        map2.put("fileDir", fileDir);

        int result = vipPictureMapper.editPic(map2);
        if (result != 1){
            throw new BizException("文件信息保存到数据库失败！");
        }
        return ResponseData.ok();
    }

    @Override
    public void viewPic(String vipCode, HttpServletResponse response) throws IOException {
        //String vipCode = params.getOrDefault("vipCode", "").toString();
        if (StringUtils.isNullOrEmpty(vipCode)){
            throw new BizException("输入的会员号不能为空！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("vipCode", vipCode);
        List<VipPicture> list = vipPictureMapper.viewPic(map);
        if (list == null){
            throw new BizException("查询图片出错！");
        }
        String fileDir = "";
        String fileName = "";
        //原来考虑的比较多，比如一个会员可能有多个文件
        //回来懒得做了，只要一个文件吧，所以才有如下写法
        for(VipPicture vipPicture: list){
            fileDir = vipPicture.getFileDir();
            fileName = vipPicture.getFileName();

        }
        fileUpandDown.fileDown(fileName, response);
        //return ResponseData.ok();
    }

    @Override
    public ResponseData verifyVip(Map<String, Object> params) {

        String cardNo = params.getOrDefault("cardNo", "").toString();
        String password = params.getOrDefault("password", "").toString();
        String checkCode = params.getOrDefault("checkCode", "").toString();
        if (StringUtils.isNullOrEmpty(cardNo)){
            throw new BizException("传入的会员号不能为空！");
        }
        boolean isMobileNo = CommonUtils.isMobile(cardNo);

        if (StringUtils.isNullOrEmpty(password) && StringUtils.isNullOrEmpty(checkCode)){
            throw new BizException("密码和校验码不能同时为空！");
        }
        if (!isMobileNo && (StringUtils.isNullOrEmpty(password))){
            throw new BizException("会员号验证时，必须输密码！");
        }
        //业务查询，手机号可以用密码和校验码，会员码只能用密码
        VipInfo vipInfo = null;
        Map<String, Object> map = new HashMap<>();
        if (isMobileNo){
            map.put("mobile", cardNo);
            //map.put("password", password);
            if (!StringUtils.isNullOrEmpty(password)){
                //vipInfo = vipInfoMapper.queryVipByMobile(map);
            }else {
                //reids中查询验证码
                String key = "mo" + cardNo;
                String curCheckCode = redisUtils.get(key).toString();
                if (!checkCode.equals(curCheckCode)){
                    throw new BizException("验证码不正确！");
                }
            }
            vipInfo = vipInfoMapper.queryVipByMobile(map);
        }else {
            map.put("vipCode", cardNo);
            //map.put("password", password);
            vipInfo = vipInfoMapper.queryVipByVipCode(map);
        }

        if (vipInfo == null){
            throw new BizException("没有找到会员信息！");
        }
        String curPassword = null;
        try {
            curPassword = EntryUtils.decrypt(vipInfo.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("解密失败！");
        }

        if (!StringUtils.isNullOrEmpty(password) && !(Objects.equals(password, curPassword))){
            throw new BizException("密码输入错误！");
        }
        return ResponseData.okData("vipInfo", vipInfo);
    }

    /**
     * pageHelper是mybaits的分页插件，通过设置pageHelper.start()，就可以实现mybatis查询结果分页
     * pageHelper是物理分页，每次执行会查询两个，第一次查询所有所有数据数量，
     *                                          第二次用mysql的limit satrtno, size进行分页查询
     * @param params
     * @return
     */
    @Override
    public ResponseData getAllVip(Map<String, Object> params) {

        String page = params.getOrDefault("pageNo", "").toString();
        String size = params.getOrDefault("pageSize", "").toString();

        List<VipInfo> list = null;
        if (StringUtils.isNullOrEmpty(page) || StringUtils.isNullOrEmpty(page)){
            list = vipInfoMapper.getAllVip();
        }else {  //不为空时，按传入的参数查询
            int pageNo = Integer.parseInt(page);
            int pageSize = Integer.parseInt(size);
            PageHelper.startPage(pageNo, pageSize);
            list = vipInfoMapper.getAllVip();
        }

        PageInfo<VipInfo> pageInfo = new PageInfo<>(list);
        //test
        LoggerUtils.getLogger().info(pageInfo.toString());
        //根据传入的page信息查询返回对应的会员信息，并返回所有会员总数，和本次页数无关
        return ResponseData.okData(list, pageInfo.getTotal());
    }

    @Override
    public ResponseData addVipBatch(List<VipInfo> vipInfos) {

        int result = vipInfoMapper.insertBatchVip(vipInfos);
        if (result<1){
            return ResponseData.error("插入数据出错！");
        }
        return ResponseData.okData("count", result);
    }

}
