package z.mapper;

import z.entity.User_loginEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户登录表 Mapper 接口
 * </p>
 *
 * @author z
 * @since 2021-10-01
 */
@Mapper
public interface User_loginDao extends BaseMapper<User_loginEntity> {

}
