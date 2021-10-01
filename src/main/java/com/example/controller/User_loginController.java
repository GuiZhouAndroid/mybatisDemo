package com.example.controller;


import com.example.JsonResult;
import com.example.service.User_loginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class User_loginController {
    @Autowired
    User_loginService userLoginService;
    @RequestMapping("/findAllUser")
    public JsonResult<Object> findAllUser(){
        return new JsonResult<>(userLoginService.getById(1),"查询成功");
    }
}
