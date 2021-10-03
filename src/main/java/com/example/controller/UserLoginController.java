package com.example.controller;


import com.example.entity.UserLoginBean;
import com.example.service.UserLoginService;
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
 * @since 2021-10-03
 */
@RestController
@RequestMapping("/user-login-bean")
public class UserLoginController {
    /** 常规写法：Spring容器中查询对应类型的bean，然后根据名称来查找资源 */
    @Autowired
    UserLoginService userLoginService;

    //浏览器访问 http://localhost:8085/user-login-bean/getUserById
    @RequestMapping("/getUserById")
    public UserLoginBean getUserById(){
        //getById是mybatisplus封装好的，通过ID值，查找映射表中的信息
        return userLoginService.getById(1);
    }
    //浏览器访问 http://localhost:8085/user-login-bean/findAllUser
    @RequestMapping("/findAllUser")
    public List<UserLoginBean> findAllUser(){
        //findAllUser() 是自定义的SQL查询映射方法——查询t_user_login表中全部用户信息
        return userLoginService.findAllUser();
    }
}
