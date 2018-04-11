package com.lijq.mybatis.v1;

/**
 * @author Lijq
 * @date 2018/4/2 10:51
 * @description
 */
public class SqlSession {

    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class clazz) {
        return configuration.getMapper(clazz, this);
    }


    public <T> T selectOne(String sql, String parameter) {
        return executor.query(sql, parameter);
    }

}
