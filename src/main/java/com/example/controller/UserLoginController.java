package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.UserLoginBean;
import com.example.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户登录表 前端控制器
 * </p>
 *
 * UpdateWrapper：实体对象封装操作类，用于查询
 * QueryWrapper：实体条件封装操作类，用于更新
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

    /** 通过实体对象ID值，批量更新或添加用户信息,为更好演示批量操作，示例：更新2条，添加2条 */
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
        //构造map条件：删除自增ID大于等于6的用户信息（"DELETE FROM t_user_login WHERE ul_id >= 6"）
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
        //构造map条件，（DELETE FROM t_user_login WHERE ul_id = 2 AND ul_password = "2更新后qe密码" AND ul_username = "2更新后root"）
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

    /** 通过实体对象ID值，更新用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/updateUserInfoById
    @RequestMapping("/updateUserInfoById")
    public Boolean updateUserInfoById(){
        //通过ID值为1，修改对应的用户名和密码，返回布尔类型
        //修改前 用户名"root"，密码"root"
        return userLoginService.updateById(new UserLoginBean(1,"更新用户名root","更新密码root"));
    }

    /** 通过 UpdateWrapper 条件，更新用户信息 需要设置 sqlset */
    //浏览器访问 http://localhost:8085/user-login-bean/updateUserInfoBySqlSet
    @RequestMapping("/updateUserInfoBySqlSet")
    public Boolean updateUserInfoBySqlSet(){
        //创建 UpdateWrapper 对象
        UpdateWrapper<UserLoginBean> updateWrapper = new UpdateWrapper<>();
        //构造条件
        updateWrapper
                .eq("ul_username","更新用户名root") // ul_username = '更新用户名root'
                .eq("ul_password","更新密码root") // ul_password = '更新密码root'
                //更新的set参数值
                .setSql("ul_username = 'update用户名',ul_password = 'update密码'");
        /** 以上构造条件，效果等同于以下单独set() */
//        updateWrapper
//                .eq("ul_username","更新用户名root")
//                .eq("ul_password","更新密码root")
//                .set("ul_username","update用户名")
//                .set("ul_password","update密码");
        //返回布尔类型
        return userLoginService.update(updateWrapper);
    }

    /** 通过实体条件，更新用户信息 不需要设置 sqlset */
    //浏览器访问 http://localhost:8085/user-login-bean/updateUserInfoByEntity
    @RequestMapping("/updateUserInfoByEntity")
    public Boolean updateUserInfoByEntity(){
        //创建 UpdateWrapper 对象
        UpdateWrapper<UserLoginBean> updateWrapper = new UpdateWrapper<>();
        //构造条件
        updateWrapper
                .eq("ul_username","update用户名") // ul_username = 'update用户名'
                .eq("ul_password","update密码"); // ul_password = 'update密码'
        //创建用户对象
        UserLoginBean user = new UserLoginBean("新root","新root");
        //第一个参数：实体对象初始化信息，第二个参数：构造的where条件。返回布尔类型
        return userLoginService.update(user,updateWrapper);
    }

    /** 通过实体对象ID值，批量更新用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/updateUserInfoByBatchById
    @RequestMapping("/updateUserInfoByBatchById")
    public Boolean updateUserInfoByBatchById(){
        //创建List集合，装载批量数据
        List<UserLoginBean> userLoginBeans = new ArrayList<>();
        //创建两个用户对象数据，分别对应ID值1、2，后面的用户名和密码，即使对应更新数据参数
        userLoginBeans.add(new UserLoginBean(1,"1批量更新用户名root","1批量更新密码root"));
        userLoginBeans.add(new UserLoginBean(2,"2批量更新用户名root","2批量更新密码root"));
        //返回布尔类型
        return userLoginService.updateBatchById(userLoginBeans);
    }

    /** *********************************   查询单条用户数据   **************************************/

    /** 通过自增ID值，查询用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectUserInfoById
    @RequestMapping("/selectUserInfoById")
    public UserLoginBean selectUserInfoById(){
        //通过主键ID值，查询对应用户名和密码，返回查询当前用户的实体对象
        return userLoginService.getById(1);
    }

    /** 通过 QueryWrapper，查询一条用户信息。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1") */
    //浏览器访问 http://localhost:8085/user-login-bean/selectUserInfoByWrapper
    @RequestMapping("/selectUserInfoByWrapper")
    public UserLoginBean selectUserInfoByWrapper(){
        //创建查询条件对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //构造查询条件
        queryWrapper
                .select("ul_id","ul_username","ul_password") // SELECT ul_id,ul_username,ul_password FROM t_user_login
                .eq("ul_username","1批量更新用户名root")
                .eq("ul_password","1批量更新密码root") // WHERE (ul_username = '1批量更新用户名root' AND ul_password = '1批量更新密码root')
                //限制查询一条数据，避免数据库中有同时重复用户名+密码，我对用户名进行唯一约束的因此不会查询出多条数据就不会抛出异常
                .last("LIMIT 1"); //LIMIT 1
        //返回查询当前用户的实体对象
        return userLoginService.getOne(queryWrapper);
    }

    /** 通过 QueryWrapper，查询一条用户信息。有多个结果集 result 是否抛出异常 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectUserInfoByWrapperIfThrow
    @RequestMapping("/selectUserInfoByWrapperIfThrow")
    public UserLoginBean selectUserInfoByWrapperIfThrow(){
        //创建查询条件对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //构造查询条件
        queryWrapper
                .select("ul_id","ul_username","ul_password") // SELECT ul_id,ul_username,ul_password FROM t_user_login
                .eq("ul_username","1批量更新用户名root")
                .eq("ul_password","1批量更新密码root")// WHERE (ul_password = '1批量更新密码root')
                .last("LIMIT 1"); //LIMIT 1
        //返回查询当前用户的实体对象
        return userLoginService.getOne(queryWrapper,false);
    }

    /** 通过QueryWrapper，查询一条用户信息，存放至map集合 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectUserInfoByWrapperBackMap
    @RequestMapping("/selectUserInfoByWrapperBackMap")
    public Map<String, Object> selectUserInfoByWrapperBackMap(){
        //创建查询条件对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //构造查询条件
        queryWrapper
                .select("ul_id","ul_username","ul_password") // SELECT ul_id,ul_username,ul_password FROM t_user_login
                .eq("ul_username","2批量更新用户名root")
                .eq("ul_password","2批量更新密码root")// WHERE (ul_password = '2批量更新密码root')
                .last("LIMIT 1"); //LIMIT 1
        //当前查询用户信息返回存放map集合
        return userLoginService.getMap(queryWrapper);
    }

    /** *********************************   查询List用户数据   **************************************/

    /** 查询全部用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectAllUserInfo
    @RequestMapping("/selectAllUserInfo")
    public List<UserLoginBean> selectAllUserInfo(){
        //用户信息存放到List集合
        return userLoginService.list();
    }

    /** 通过QueryWrapper条件，列表查询用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectListUserInfoByWrapper
    @RequestMapping("/selectListUserInfoByWrapper")
    public List<UserLoginBean> selectListUserInfoByWrapper(){
        //创建查询条件对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .gt("ul_id",1); //查询自增ID值大于1的用户信息
        //用户信息存放到List集合
        return userLoginService.list(queryWrapper);
    }

    /** 通过主键自增ID列表，批量查询用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectListUserInfoByListIds
    @RequestMapping("/selectListUserInfoByListIds")
    public List<UserLoginBean> selectListUserInfoByListIds(){
        //创建List集合，装载批量主键自增ID数据
        List<Integer> loginBeans = new ArrayList<>();
        //ID的List列表放入1，即查询自增ID为1的用户全部信息
        loginBeans.add(1);
        //用户信息存放到List集合
        return userLoginService.listByIds(loginBeans);
    }

    /** 通过Map集合中column值，批量列表查询用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectListUserInfoByMapColumn
    @RequestMapping("/selectListUserInfoByMapColumn")
    public List<UserLoginBean> selectListUserInfoByMapColumn(){
        //创建Map集合，存放Column数据
        HashMap<String,Object> hashMap = new HashMap<>();
        //构造参数
        hashMap.put("ul_password","root");
        //字段名ul_password对应密码为"root"的全部用户信息存放到List集合
        return userLoginService.listByMap(hashMap);
    }

    /** 查询全部用户信息列表，放入Map,返回到List */
    //浏览器访问 http://localhost:8085/user-login-bean/selectListAndMapAllUserInfo
    @RequestMapping("/selectListAndMapAllUserInfo")
    public List<Map<String, Object>> selectListAndMapAllUserInfo(){
        //查询全部用户信息，返回到List集合
        return userLoginService.listMaps();
    }

    /** 通过QueryWrapper查询用户信息列表，放入Map,返回到List */
    //浏览器访问 http://localhost:8085/user-login-bean/selectListAndMapUserInfoByWrapper
    @RequestMapping("/selectListAndMapUserInfoByWrapper")
    public List<Map<String, Object>> selectListAndMapUserInfoByWrapper(){
        //创建查询条件对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("ul_id","ul_username","ul_password")
                //.ge("ul_id",2)//大于等于2 ul_id >= 2
                //.le("ul_id",6)//小于等于6 ul_id <= 6
                //.or()
                .like("ul_password","o");//模糊查询 ul_password LIKE '%o%'
        //用户信息，返回到List集合
        return userLoginService.listMaps(queryWrapper);
    }

    /** 查询全部记录 */
    //浏览器访问 http://localhost:8085/user-login-bean/selectListUserInfoByObjs
    @RequestMapping("/selectListUserInfoByObjs")
    public List<Object> selectListUserInfoByObjs(){
        return userLoginService.listObjs();
    }

    /** *********************************   分页查询用户数据   **************************************/

    /** 无条件分页查询【IPage(实体对象)】第1页数据（每页2条记录）*/
    //浏览器访问 http://localhost:8085/user-login-bean/unconditionalPage
    @RequestMapping("/unconditionalPage")
    public IPage<UserLoginBean> unconditionalPage(){
        // 1.创建IPage实例，初始化分页查询参数：第1页，显示2条数据
        IPage<UserLoginBean> iPage = new Page<>(1,2);
        /** 获取分页数据 */
        System.out.println(iPage.getCurrent()); // 获取当前页
        System.out.println(iPage.getTotal()); // 获取总记录数
        System.out.println(iPage.getSize()); // 获取每页的条数 默认10
        System.out.println(iPage.getRecords()); // 获取每页数据的集合
        System.out.println(iPage.getPages()); // 获取总页数
        /** IPage没有hasNext()、hasPrevious()，Page有hasNext()、hasPrevious() */
        //System.out.println(iPage.hasNext()); // 是否存在下一页
        //System.out.println(iPage.hasPrevious()); // 是否存在上一页
        //2.开始执行无条件分页查询
        return userLoginService.page(iPage);
        /** 以下是无条件分页查询代码优化版，注释上面代码，去除以下代码即可 */
        //匿名对象方式设置分页参数，开始执行无条件分页查询
        //return userLoginService.page(new Page<>(1,2));
    }

    /** 有条件分页查询第1页数据（每页2条记录）*/
    //浏览器访问 http://localhost:8085/user-login-bean/conditionalPage
    @RequestMapping("/conditionalPage")
    public IPage<UserLoginBean> conditionalPage(){
        //1.创建IPage实例，初始化分页查询参数：第1页，显示2条数据
        IPage<UserLoginBean> userLoginBeanPage = new Page<>(1,2);
        //构造分页查询QueryWrapper条件（链式调用 lambda 式、QueryWrapper实例，两种方式均可以构造分页查询条件）
        /** 创建条件对象方式一：new QueryWrapper */
        //QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //构造分页查询条件
        //queryWrapper.select("ul_id","ul_username","ul_password")
                //.ge("ul_id",1)//大于等于2 ul_id >= 1
                //.le("ul_id",6);//小于等于6 ul_id <= 6
        /** 创建条件对象方式二：new LambdaQueryWrapper */
        //2.创建Wrapper对象
        LambdaQueryWrapper<UserLoginBean> wrapper = new LambdaQueryWrapper<>();
        //构造分页查询条件
        wrapper
                .ge(UserLoginBean::getUlId,1) //大于等于2 ul_id >= 2
                .le(UserLoginBean::getUlId,6); //小于等于6 ul_id <= 6
        /** 省略获取分页数据，可选操作 */
        //System.out.println(iPage....); // 获取当前页
        //...
        //3.调用IService封装的page()，传入分页查询参数+分页查询条件--->开始执行分页查询操作，返回用户信息分页查询结果
        return userLoginService.page(userLoginBeanPage, wrapper);
    }

    /** 无条件Map分页查询第1页数据（每页1条记录）*/
    //浏览器访问 http://localhost:8085/user-login-bean/unconditionalPageMaps
    @RequestMapping("/unconditionalPageMaps")
    public IPage<Map<String,Object>> unconditionalPageMaps(){
        //1.创建IPage实例，初始化分页查询参数：第1页，显示1条数据
        IPage<Map<String,Object>> mapIPage = new Page<>(1,1);
        /** 省略获取分页数据，可选操作 */
        //System.out.println(iPage....); // 获取当前页
        //...
        //2.返回用户信息分页查询结果
        return userLoginService.pageMaps(mapIPage);
        /** 以下是无条件分页查询代码优化版，注释上面代码，去除以下代码即可 */
        //return userLoginService.pageMaps(new Page<>(1,1));
    }

    /** 有条件Map分页查询第1页数据（每页1条记录）*/
    //浏览器访问 http://localhost:8085/user-login-bean/conditionalPageMaps
    @RequestMapping("/conditionalPageMaps")
    public IPage<Map<String,Object>> conditionalPageMaps(){
        //1.创建IPage实例，初始化分页查询参数：第1页，显示1条数据
        IPage<Map<String,Object>> iPageParam = new Page<>(1,1);
        //2.创建Wrapper对象（链式调用 lambda 式、QueryWrapper实例，两种方式均可以构造分页查询条件）
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //3.构造分页查询条件
        queryWrapper.select("ul_id","ul_username","ul_password")
        .ge("ul_id",1)//大于等于2 ul_id >= 1
        .le("ul_id",6);//小于等于6 ul_id <= 6
        /** 省略获取分页数据，可选操作 */
        //System.out.println(iPage....); // 获取当前页
        //...
        //4.调用IService封装的page()，传入分页查询参数+分页查询条件--->开始执行分页查询操作，返回用户信息分页查询结果
        return userLoginService.pageMaps(iPageParam, queryWrapper);
    }

    /** 查询全部用户信息数量 */
    //浏览器访问 http://localhost:8085/user-login-bean/countUserInfo
    @RequestMapping("/countUserInfo")
    public Long countUserInfo(){
        //返回Long类型，数值为用户信息总条数
        return userLoginService.count();
    }

    /** 通过QueryWrapper条件，查询全部用户信息数量 */
    //浏览器访问 http://localhost:8085/user-login-bean/countUserInfoByWrapper
    @RequestMapping("/countUserInfoByWrapper")
    public Long countUserInfoByWrapper(){
        //创建Wrapper条件对象
        QueryWrapper<UserLoginBean> queryWrapper = new QueryWrapper<>();
        //构造查询条件：自增ID在2——5之间有多少条用户数据
        queryWrapper.between("ul_id",2,5);//目前用户表有4条
        //返回Long类型，数值为用户信息总条数
        return userLoginService.count(queryWrapper);
    }

    /** UserLoginMapper.xml 自定义SQL，查询全部用户信息 */
    //浏览器访问 http://localhost:8085/user-login-bean/findAllUser
    @RequestMapping("/findAllUser")
    public List<UserLoginBean> findAllUser(){
        //findAllUser() 是自定义的SQL查询映射方法——查询t_user_login表中全部用户信息
        return userLoginService.findAllUser();
    }
}
