package com.example.service.impl;

import com.example.entity.UserLoginBean;
import com.example.mapper.UserLoginMapper;
import com.example.service.UserLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-03
 */
@Service
public class UserLoginServiceImpl extends ServiceImpl<UserLoginMapper, UserLoginBean> implements UserLoginService {
    @Autowired
    UserLoginMapper userLoginMapper;

    @Override
    public List<UserLoginBean> findAllUser() {
        return userLoginMapper.findAllUser();
    }

}
