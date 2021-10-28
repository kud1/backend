package com.zhangle.service;

import com.zhangle.common.bo.UserBo;
import com.zhangle.common.entity.User;

public interface LoginService {

    UserBo login(User user);
}
