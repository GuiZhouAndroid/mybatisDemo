package com.example.mapper;

import com.example.entity.User_loginBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户登录表 Mapper 接口
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-01
 */
@Mapper
public interface User_loginMapper extends BaseMapper<User_loginBean> {
//     List<User_loginBean> findAllUser();
}
