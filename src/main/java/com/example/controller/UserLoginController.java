package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.entity.UserLoginBean;
import com.example.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * @author ZSAndroid
 * @since 2021-10-03
 */
@RestController
@RequestMapping("/user-login-bean")
public class UserLoginController {
    /** 常规写法：Spring容器中查询对应类型的bean，根据名称来查找资源 */
    @Autowired
    UserLoginService userLoginService;

    /** *********************************   添加用户数据   **************************************/

    /** 添加一条用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/addOnceUserInfo
    @RequestMapping("/addOnceUserInfo")
    public boolean addOnceUserInfo(){
        //save() 是mybatis-plus封装好的，构造用户实体的对象数据，传参添加到mysql用户表t_user_login中
        return userLoginService.save(new UserLoginBean("添加的用户名","添加的密码"));
    }

    /** 批量添加用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/addBatchUserInfo
    @RequestMapping("/addBatchUserInfo")
    public boolean addBatchUserInfo(){
        //多态机制创建一个List集合，创建两个用户对象数据，调用add()把用户对象数据，依次添加到List集合中
        List<UserLoginBean> userList = new ArrayList<>();
        userList.add(new UserLoginBean("list用户名3","list密码3"));
        userList.add(new UserLoginBean("list用户名4","list密码4"));
        //saveBatch()是mybatis-plus封装好的，批量添加数据使用，接受集合对象，因此上面把两个用户数据添加至集合
        //提供给saveBatch()使用，就实现批量添加功能，返回boolean类型，true为批量添加成功，反之false批量添加失败
        return userLoginService.saveBatch(userList,1);
    }

    /** 注解TableId()——更新And添加用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/addSaveOrUpdateUserInfo/qe用户名/qe密码
    @RequestMapping("/addSaveOrUpdateUserInfo/{username}/{password}")
    //username 和 password 的值，从URL中获取
    public boolean addSaveOrUpdateUserInfo(@PathVariable("username") String username,@PathVariable("password") String password){
        //创建更新条件构造器对象
        UpdateWrapper<UserLoginBean> updateWrapper = new UpdateWrapper<>();
        //eq 等于："ul_userName"（用户名）为"admin"的用户信息存在就更新记录为"qe用户名"，不存在就添加一条用户名记录"admin"
        //eq 等于："ul_password"（密码）为"admin"的用户信息存在就更新记录"qe密码"，不存在就添加一条密码记录"admin"
        updateWrapper.eq("ul_username","admin");
        updateWrapper.eq("ul_password","admin");
        //saveOrUpdate()是mybatis-plus封装好的，需要实体类自增ID使用 注解@TableId()，
        //第一个参数是：实体对象数据，第二个参数是：更新条件构造器对象，返回布尔类型
        return userLoginService.saveOrUpdate(new UserLoginBean("操作后"+username,"操作后"+password),updateWrapper);
    }

    /** 根据ID值，批量更新或添加用户信息,为更好演示批量操作，示例：更新2条，添加2条 */
    //浏览器访问 http://localhost:8085/user-login-bean/addSaveOrUpdateBatchUserInfo
    @RequestMapping("/addSaveOrUpdateBatchUserInfo")
    public boolean addSaveOrUpdateBatchUserInfo(){
        //创建List集合，装载批量数据
        List<UserLoginBean> userList = new ArrayList<>();
        /** 已存在更新情况2条 */
        // id = 1 对应用户信息——用户名："操作后qe用户名"  密码："操作后qe密码"（这条ID为1的用户数据是已经存在的，存在即执行更新）
        userList.add(new UserLoginBean(1,"1更新后qe用户名","1更新后qe密码"));
        // id = 1 对应用户信息——用户名："root"  密码："root"（这条ID为2的用户数据是已经存在的，存在即执行更新）
        userList.add(new UserLoginBean(2,"2更新后root","2更新后qe密码"));
        /** 不存在添加情况2条 */
        // id = 50 对应用户信息——用户名：无  密码：无（这条ID为50的用户数据不存在——添加数据）
        userList.add(new UserLoginBean(50,"50添加用户名","50添加密码"));
        // id = 100 对应用户信息——用户名：无  密码：无（这条ID为100的用户数据不存在——添加数据）
        userList.add(new UserLoginBean(100,"100添加用户名","100添加密码"));
        //返回布尔类型的执行结果
        return userLoginService.saveOrUpdateBatch(userList);
    }


    /** *********************************   删除用户数据   **************************************/

    /** 通过主键ID值，删除用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/deleteUserInfoById
    @RequestMapping("/deleteUserInfoById")
    public Boolean deleteUserInfoById(){
        //removeById()是mybatis-plus封装好的，通过主键ID值，删除ID值为100的用户信息，返回布尔类型
        return userLoginService.removeById(100);
    }

    /** 通过实体对象ID值，删除用户信息，此方式演示与上面效果相似，省略演示 */
    //浏览器访问 http://localhost:8085/user-login-bean/deleteUserInfoByEntityId
    @RequestMapping("/deleteUserInfoByEntityId")
    public Boolean deleteUserInfoByEntityId(){
        //removeById()是mybatis-plus封装好的，通过实体对象ID值，删除ID值为5的用户信息，返回布尔类型
        return userLoginService.removeById(new UserLoginBean(5));
    }

    /** 通过实体对象条件，删除用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/deleteUserInfoByEntity
    @RequestMapping("/deleteUserInfoByEntity")
    public Boolean deleteUserInfoByEntity(){
        //创建一个QueryWrapper对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //构造查询条件：删除自增ID大于等于6的用户信息（"DELETE FROM t_user_login WHERE ul_id >= 6"）
        queryWrapper.ge("ul_id","6");
        //返回布尔类型
        return userLoginService.remove(queryWrapper);
    }

    /** 通过Map集合条件，删除相应用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/deleteUserInfoByMap
    @RequestMapping("/deleteUserInfoByMap")
    public Boolean deleteUserInfoByMap() {
        //创建Map集合，装载条件数据
        HashMap<String, Object> hashMap = new HashMap<>();
        //构造map查询条件，（DELETE FROM t_user_login WHERE ul_id = 2 AND ul_password = "2更新后qe密码" AND ul_username = "2更新后root"）
        //每一个put()等同于一个MySQL的并且"AND"
        hashMap.put("ul_id",2);
        hashMap.put("ul_username","2更新后root");
        hashMap.put("ul_password","2更新后qe密码");
        //返回布尔类型
        return userLoginService.removeByMap(hashMap);
    }

    /** 通过主键ID，批量删除用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/deleteBatchUserInfoByIds
    @RequestMapping("/deleteBatchUserInfoByIds")
    public Boolean deleteBatchUserInfoByIds(){
        //创建List集合，装载批量数据
        List<Integer> userLoginBeans = new ArrayList<>();
        //把自增ID值为1、3的用户信息，放到List集合中
        userLoginBeans.add(1);
        userLoginBeans.add(3);
        //根据List保存的自增ID值，执行批量删除操作【DELETE FROM t_user_login WHERE ul_id IN ( 1 , 3 )】
        //返回布尔类型
        return userLoginService.removeByIds(userLoginBeans);
    }

    /** *********************************   修改用户数据   **************************************/

    /** *********************************   查询用户数据   **************************************/

    /** 通过ID值，查询用户全部信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/getUserById
    @RequestMapping("/getUserById")
    public UserLoginBean getUserById(){
        //getById是mybatis-plus封装好的，通过ID值，查找映射表中的信息
        return userLoginService.getById(1);
    }

    /** 查询全部用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/findAllUser
    @RequestMapping("/findAllUser")
    public List<UserLoginBean> findAllUser(){
        //findAllUser() 是自定义的SQL查询映射方法——查询t_user_login表中全部用户信息
        return userLoginService.findAllUser();
    }
}
