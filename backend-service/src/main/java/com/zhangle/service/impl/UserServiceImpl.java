package com.zhangle.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangle.common.entity.User;
import com.zhangle.dao.mapper.UserMapper;
import com.zhangle.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public void newUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> listUser() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.orderByDesc("id");
        Page<User> page = new Page<User>(1,1, false);
        page = userMapper.selectPage(page, query);
        return page.getRecords();
    }
}
