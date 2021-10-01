package com.example.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户登录表
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-30
 */
@Data
public class TUserLogin extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 登录自增ID
     */
    @TableId(value = "ul_id", type = IdType.AUTO)
    private Integer ulId;

    /**
     * 登录名
     */
    private String ulUsername;

    /**
     * 登录密码
     */
    private String ulPassword;


}
