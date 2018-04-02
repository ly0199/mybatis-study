package com.lijq.mybatis;

import com.github.pagehelper.PageHelper;
import com.lijq.mybatis.config.DataSourcesConfiguration;
import com.lijq.mybatis.config.MyBatisConfiguration;
import com.lijq.mybatis.entity.User;
import com.lijq.mybatis.entity.UserExample;
import com.lijq.mybatis.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

/**
 * @author Lijq
 * @date 2018/3/28 9:22
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DataSourcesConfiguration.class, MyBatisConfiguration.class})
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {

        String str = "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        Random random = new Random();
        StringBuffer sb = null;

        User user = null;

        for (int i = 0; i < 10; i++) {
            sb = new StringBuffer();
            for (int j = 0; j < 6; ++j) {
                int number = random.nextInt(62);
                sb.append(str.charAt(number));
            }
            user = new User();
            user.setUsername(sb.toString());
            user.setPassword("123456");
            user.setAge(new Random().nextInt(20));
            int k = userMapper.insert(user);
            System.out.println("i = " + k);
        }
    }

    @Test
    @Transactional
    public void query() {
        User user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getUsername());
        user = userMapper.selectByPrimaryKey(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void page() {
        PageHelper.startPage(1, 10);
        List<User> userList = userMapper.selectByExample(new UserExample());
        userList.forEach(System.out::println);
    }


}
