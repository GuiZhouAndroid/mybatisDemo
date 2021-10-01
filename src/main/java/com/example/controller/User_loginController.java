package com.example.controller;


import com.example.entity.User_loginBean;
import com.example.service.User_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.example.common.entity.BaseController;

import java.util.List;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-01
 */
@RestController
@RequestMapping("/user-login-bean")
public class User_loginController extends BaseController {
    @Autowired
    User_loginService userLoginService;
    @PostMapping("/findAllUser")
    public List<User_loginBean> findAllUser(){
        return userLoginService.findAllUser();
    }
}
