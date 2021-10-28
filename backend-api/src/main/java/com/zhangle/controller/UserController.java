package com.zhangle.controller;

import com.zhangle.common.entity.User;
import com.zhangle.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST)
    public User getUserById(@RequestBody User user) {
        User userInDB = userService.getById(user.getId());
        if(userInDB == null) {
            return new User();
        }
        return userInDB;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Long addUser(@RequestBody  User user) {
        userService.newUser(user);
        return user.getId();
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public List<User> listUser() {
        return userService.listUser();
    }
}
