package z.entity;

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
 * @author z
 * @since 2021-10-01
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("t_user_login")
@ApiModel(value = "User_loginEntity对象", description = "用户登录表")
public class User_loginEntity extends Model<User_loginEntity> {

    @ApiModelProperty("登录自增ID")
    @TableId(value = "ul_id", type = IdType.AUTO)
    private Integer ul_id;

    @ApiModelProperty("登录名")
    @TableField("ul_username")
    private String ul_username;

    @ApiModelProperty("登录密码")
    @TableField("ul_password")
    private String ul_password;


    @Override
    public Serializable pkVal() {
        return this.ul_id;
    }

}
