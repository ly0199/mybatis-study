package com.lijq.mybatis.biz;

import com.lijq.mybatis.v1.Configuration;
import com.lijq.mybatis.v1.SimpleExecutor;
import com.lijq.mybatis.v1.SqlSession;

/**
 * @author Lijq
 * @date 2018/4/2 11:17
 * @description
 */
public class Test {

    public static void main(String[] args) {

        SqlSession sqlSession = new SqlSession(new Configuration(), new SimpleExecutor());

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(1);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println(" Have None Object!");
        }


    }
}
