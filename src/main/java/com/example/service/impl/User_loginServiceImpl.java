package com.example.service.impl;

import com.example.entity.User_loginBean;
import com.example.mapper.User_loginMapper;
import com.example.service.User_loginService;
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
 * @since 2021-10-01
 */
@Service
public class User_loginServiceImpl extends ServiceImpl<User_loginMapper, User_loginBean> implements User_loginService {
    @Autowired
    private User_loginMapper userLoginMapper;
    @Override
    public List<User_loginBean> findAllUser() {
        return userLoginMapper.findAllUser();
    }
}
