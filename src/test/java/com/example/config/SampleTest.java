package com.example.config;

import com.example.entity.User_loginBean;
import com.example.mapper.User_loginMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * created by on 2021/10/1
 * 描述：
 *
 * @author ZSAndroid
 * @create 2021-10-01-18:31
 */
@SpringBootTest
public class SampleTest {

    @Autowired
    private User_loginMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User_loginBean> userList = userMapper.selectList(null);
        Assert.assertEquals(2, userList.size());
        userList.forEach(System.out::println);
    }
}
