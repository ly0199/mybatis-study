package com.lijq.mybatis.v1;

/**
 * @author Lijq
 * @date 2018/4/2 10:58
 * @description
 */
public interface Executor {

    <T> T query(String sql, String parameters);
}
