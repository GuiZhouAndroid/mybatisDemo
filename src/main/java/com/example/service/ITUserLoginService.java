package com.example.service;

import com.example.model.TUserLogin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-30
 */
public interface ITUserLoginService extends IService<TUserLogin> {
    public List<TUserLogin> findAllUser();
}
