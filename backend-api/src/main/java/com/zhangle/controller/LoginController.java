package com.zhangle.controller;

import com.zhangle.common.bo.UserBo;
import com.zhangle.common.entity.User;
import com.zhangle.service.LoginService;
import com.zhangle.util.TokenUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("")
@CrossOrigin
@Api(value = "登录相关接口", tags = { "登录" })
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    CacheManager cacheManager;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserBo login(@ApiParam("用户信息") @RequestBody User user) {
        UserBo userBo = loginService.login(user);
        if (userBo.getStatus() == 200) {
            String token = TokenUtils.generateToken();
            cacheManager.getCache("TOKEN").put(token, 1);
            userBo.setToken(token);
        }
        return userBo;
    }

}
