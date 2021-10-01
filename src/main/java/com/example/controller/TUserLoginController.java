package com.example.controller;


import com.example.model.TUserLogin;
import com.example.service.ITUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-30
 */
@RestController
@RequestMapping("/t-user-login")
public class TUserLoginController {
    @Autowired
    private ITUserLoginService userService;
    @PostMapping("/getUser")
    public TUserLogin getUser(){
        return userService.getById(2);
    }

    @PostMapping("/findAllUser")
    public List<TUserLogin> findAllUser(){
        return userService.findAllUser();
    }
}
