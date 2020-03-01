package com.mycom.ssmdemo.controller.shiro;

import com.mycom.ssmdemo.common.commexception.BizException;
import com.mycom.ssmdemo.common.message.ResponseData;
import com.mycom.ssmdemo.entity.shiro.Userforshiro;
import com.mycom.ssmdemo.service.shiro.ShiroTestService;
import com.mysql.cj.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.index.GeoIndexed;
import org.springframework.web.bind.annotation.*;

//import javax.security.auth.Subject;
import java.util.Map;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-28 下午 07:48
 * @description：
 * @modified By：
 * @version: $
 */
@RestController
//@RequestMapping("/shiro")
public class ShiroController {

    @Autowired
    private ShiroTestService shiroTestService;

    @GetMapping("/shiro/test/{username}")
    public ResponseData testUser(@PathVariable String username){
        Userforshiro userforshiro = shiroTestService.getRolePermissionByUserName(username);
        return ResponseData.okData("data:", userforshiro);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseData login(@RequestParam Map<String, Object> params){

        String username = params.getOrDefault("username", "").toString();
        String password = params.getOrDefault("password", "").toString();
        if ((StringUtils.isNullOrEmpty(username)) || (StringUtils.isNullOrEmpty(password))){
            throw new BizException("用户名或者密码不能为空！");
        }
        //组织输入的用户信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        //添加用户认证信息
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            throw new BizException("账号或密码错误！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
            throw new BizException("没有权限");
        }

        return ResponseData.ok();
    }

    @RequiresRoles("admin") //admin
    @RequiresPermissions("add")//"add"
    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String index(){
        return "SUCCESS! this is index！";
    }
}
