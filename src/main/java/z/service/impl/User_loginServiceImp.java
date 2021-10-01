package z.service.impl;

import z.entity.User_loginEntity;
import z.mapper.User_loginDao;
import z.service.User_loginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录表 服务实现类
 * </p>
 *
 * @author z
 * @since 2021-10-01
 */
@Service
public class User_loginServiceImp extends ServiceImpl<User_loginDao, User_loginEntity> implements User_loginService {

}
