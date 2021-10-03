package com.example.mapper;

import com.example.entity.UserLoginBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户登录表 Mapper 接口
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-03
 */
@Mapper
public interface UserLoginMapper extends BaseMapper<UserLoginBean> {
    List<UserLoginBean> findAllUser();
}
