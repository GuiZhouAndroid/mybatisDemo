package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户登录表
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-03
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user_login")
@ApiModel(value = "UserLoginBean对象", description = "用户登录表")
public class UserLoginBean extends Model<UserLoginBean> {

    @ApiModelProperty("登录自增ID")
    @TableId(value = "ul_id", type = IdType.AUTO)
    private Integer ulId;

    @ApiModelProperty("登录名")
    @TableField("ul_username")
    private String ulUsername;

    @ApiModelProperty("登录密码")
    @TableField("ul_password")
    private String ulPassword;

    //自动生成的pkVal()可以删除
    @Override
    public Serializable pkVal() {
        return this.ulId;
    }

    /**
     * 有参构造初始化用户对象数据
     * @param ulUsername 用户名
     * @param ulPassword 密码
     */
    public UserLoginBean(String ulUsername, String ulPassword) {
        this.ulUsername = ulUsername;
        this.ulPassword = ulPassword;
    }

    /**
     * @param ulId 自增ID
     * @param ulUsername 用户名
     * @param ulPassword 密码
     */
    public UserLoginBean(Integer ulId, String ulUsername, String ulPassword) {
        this.ulId = ulId;
        this.ulUsername = ulUsername;
        this.ulPassword = ulPassword;
    }
}
