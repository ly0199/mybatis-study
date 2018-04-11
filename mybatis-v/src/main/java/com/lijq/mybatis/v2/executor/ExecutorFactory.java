package com.lijq.mybatis.v2.executor;

import com.lijq.mybatis.v2.session.Configuration;

/**
 * @author Lijq
 * @date 2018/4/8 14:59
 * @description
 */
public class ExecutorFactory {

    public static final String SIMPLE = "SIMPLE";
    public static final String CACHING = "CACHING";

    public static Executor DEFAULT(Configuration configuration) {
        return get(SIMPLE, configuration);
    }

    public static Executor get(String key, Configuration configuration) {

        if (SIMPLE.equalsIgnoreCase(key)) {
            return new BaseExecutor(configuration);
        }

        if(CACHING.equalsIgnoreCase(key)){

        }

        throw new RuntimeException("Can't find Executor by key : " + key);
    }
}
