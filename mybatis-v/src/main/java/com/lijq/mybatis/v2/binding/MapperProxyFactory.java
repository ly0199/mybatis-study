package com.lijq.mybatis.v2.binding;

import com.lijq.mybatis.v2.session.SqlSession;
import lombok.Getter;

import java.lang.reflect.Proxy;

/**
 * @author Lijq
 * @date 2018/4/9 10:38
 * @description
 */
public class MapperProxyFactory<T> {

    @Getter
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        super();
        this.mapperInterface = mapperInterface;
    }

    @SuppressWarnings("unchecked")
    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, mapperProxy);
    }
}
