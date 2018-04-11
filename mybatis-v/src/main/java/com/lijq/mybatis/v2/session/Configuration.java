package com.lijq.mybatis.v2.session;

import com.lijq.mybatis.v2.binding.MapperRegistry;
import com.lijq.mybatis.v2.plugin.Interceptor;
import com.lijq.mybatis.v2.plugin.InterceptorChain;
import lombok.Getter;

/**
 * @author Lijq
 * @date 2018/4/8 11:41
 * @description
 */
public class Configuration {

    @Getter
    private MapperRegistry mapperRegistry = new MapperRegistry(this);

    /**
     * 插件
     */
    @Getter
    private InterceptorChain interceptorChain = new InterceptorChain();


    public void addInterceptor(Interceptor interceptor) {
        interceptorChain.addInterceptor(interceptor);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public <T> T get(String key) {
        return mapperRegistry.get(key);
    }

    public Configuration buildMappers(Class<?>... classes) {
        for (Class<?> clazz : classes) {
            mapperRegistry.addMapper(clazz);
        }
        return this;
    }

    public Configuration buildInterceptors(Interceptor... interceptors) {
        for (Interceptor interceptor : interceptors) {
            addInterceptor(interceptor);
        }
        return this;
    }


}
