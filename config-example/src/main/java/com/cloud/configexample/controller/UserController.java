package com.cloud.configexample.controller;

import com.cloud.configexample.config.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RefreshScope
public class UserController {
    @Autowired
    private User user;

    @RequestMapping("/get")
    public String get() {
        return "user name :" + user.getName() + "; age: " + user.getAge();
    }

}
