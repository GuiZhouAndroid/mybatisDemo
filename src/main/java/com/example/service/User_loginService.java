package com.example.service;

import com.example.entity.User_loginBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务类
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-01
 */
public interface User_loginService extends IService<User_loginBean> {
    public List<User_loginBean> findAllUser();
}
