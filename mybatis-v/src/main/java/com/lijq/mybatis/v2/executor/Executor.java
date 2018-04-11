package com.lijq.mybatis.v2.executor;

import com.lijq.mybatis.v2.binding.MapperRegistry;

/**
 * @author Lijq
 * @date 2018/4/8 11:42
 * @description
 */
public interface Executor {

    <T> T selectOne(MapperRegistry.MapperData mapperData, Object parameter);
}
