package com.zhangle.service;

import com.zhangle.common.entity.User;

import java.util.List;

public interface UserService {

    User getById(Long id);

    void newUser(User user);

    List<User> listUser();
}
