package com.example.service;

import com.example.entity.UserLoginBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务类
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-03
 */
public interface UserLoginService extends IService<UserLoginBean> {
    List<UserLoginBean> findAllUser();
}
