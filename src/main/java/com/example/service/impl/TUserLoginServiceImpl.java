package com.example.service.impl;

import com.example.model.TUserLogin;
import com.example.mapper.TUserLoginMapper;
import com.example.service.ITUserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-30
 */
@Service
public class TUserLoginServiceImpl extends ServiceImpl<TUserLoginMapper, TUserLogin> implements ITUserLoginService {
    @Autowired
    private TUserLoginMapper tUserLoginMapper;
    @Override
    public List<TUserLogin> findAllUser() {
        return tUserLoginMapper.findAllUser();
    }
}
