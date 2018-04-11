package com.lijq.mybatis.v2.plugin;

import java.util.Properties;

/**
 * @author Lijq
 * @date 2018/4/8 9:30
 * @description
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    Object plugin(Object target);

    void setProperties(Properties properties);

}
