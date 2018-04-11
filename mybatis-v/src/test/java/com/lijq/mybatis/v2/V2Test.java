package com.lijq.mybatis.v2;

import com.lijq.mybatis.dao.User;
import com.lijq.mybatis.v2.executor.BaseExecutor;
import com.lijq.mybatis.v2.session.Configuration;
import com.lijq.mybatis.v2.session.SqlSession;
import org.junit.Test;

/**
 * @author Lijq
 * @date 2018/4/9 16:57
 * @description
 */
public class V2Test {


    @Test
    public void test(){

        Configuration configuration = new Configuration().buildMappers(UserMapper.class);

        SqlSession sqlSession = new SqlSession(configuration, new BaseExecutor(configuration));

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectByPrimaryKey("1");

        System.out.println(user);

    }


}
