package com.example.mapper;

import com.example.model.TUserLogin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户登录表 Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-30
 */
@Mapper
public interface TUserLoginMapper extends BaseMapper<TUserLogin> {
    public List<TUserLogin> findAllUser();
}
