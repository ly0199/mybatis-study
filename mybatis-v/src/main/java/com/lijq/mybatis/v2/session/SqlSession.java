package com.lijq.mybatis.v2.session;

import com.lijq.mybatis.v2.executor.Executor;
import lombok.Getter;

/**
 * @author Lijq
 * @date 2018/4/8 11:41
 * @description
 */
public class SqlSession {

    @Getter
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration) {
        this.configuration = configuration;
        this.executor = configuration.newExecutor();
    }


    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapperRegistry().getMapper(type, this);
    }


    public <T> T select(String methodName, Object parameter) {
        return executor.selectOne(configuration.get(methodName), parameter);
    }


    // TODO Insert Delete Update
}