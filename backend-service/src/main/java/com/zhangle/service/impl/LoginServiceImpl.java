package com.zhangle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangle.common.bo.UserBo;
import com.zhangle.common.constant.ResponseConstant;
import com.zhangle.common.entity.User;
import com.zhangle.dao.mapper.UserMapper;
import com.zhangle.service.LoginService;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    public UserBo login(User user) {
        UserBo bo = new UserBo();

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("name", user.getName());
        User userInDB = userMapper.selectOne(queryWrapper);
        if (userInDB != null ) {
            if (userInDB.getPassword().equals(DigestUtils.md5Hex(user.getPassword()))) {
                bo.setMessage(ResponseConstant.MESSAGE_OK);
                bo.setStatus(ResponseConstant.STATUS_OK);
                bo.setName(userInDB.getName());
            } else {
                bo.setMessage(ResponseConstant.MESSAGE_INVALID_PASSWORD);
                bo.setStatus(ResponseConstant.STATUS_INVALID_PASSWORD);
            }
        } else {
            bo.setMessage(ResponseConstant.MESSAGE_INVALID_NAME);
            bo.setStatus(ResponseConstant.STATUS_INVALID_NAME);
        }
        return bo;
    }
}
