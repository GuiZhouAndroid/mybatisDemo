package com.example.controller;


import com.example.entity.UserLoginBean;
import com.example.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        //getById是mybatis-plus封装好的，通过ID值，查找映射表中的信息
        return userLoginService.getById(1);
    }
    //浏览器访问 http://localhost:8085/user-login-bean/findAllUser
    @RequestMapping("/findAllUser")
    public List<UserLoginBean> findAllUser(){
        //findAllUser() 是自定义的SQL查询映射方法——查询t_user_login表中全部用户信息
        return userLoginService.findAllUser();
    }

    /** 添加一条用户数据 */
    //浏览器访问 http://localhost:8085/user-login-bean/addOnceUserInfo
    @RequestMapping("/addOnceUserInfo")
    public boolean addOnceUserInfo(){
        //save() 是mybatis-plus封装好的，构造用户实体的对象数据，传参添加到mysql用户表t_user_login中
        return userLoginService.save(new UserLoginBean("添加的用户名","添加的密码"));
    }

    /** 批量添加用户数据 */
    //浏览器访问 http://localhost:8085/user-login-bean/addBatchUserInfo
    @RequestMapping("/addBatchUserInfo")
    public boolean addBatchUserInfo(){
        //多态机制创建一个List集合，创建两个用户对象数据，调用add()把用户对象数据，依次添加到List集合中
        List<UserLoginBean> userList = new ArrayList<>();
        userList.add(new UserLoginBean("list用户名3","list密码3"));
        userList.add(new UserLoginBean("list用户名4","list密码4"));
        //saveBatch()是mybatis-plus封装好的，批量添加数据使用，接受集合对象，因此上面把两个用户数据添加至集合
        //提供给saveBatch()使用，就实现批量添加功能，返回boolean类型，true为批量添加成功，反之false批量添加失败
        return userLoginService.saveBatch(userList,1);
    }
}
