package com.lijq.mybatis.v2;

import com.alibaba.fastjson.JSON;
import com.lijq.mybatis.v2.binding.MapperRegistry;
import com.lijq.mybatis.v2.executor.Executor;
import com.lijq.mybatis.v2.plugin.*;

import java.util.Properties;

/**
 * @author Lijq
 * @date 2018/4/11 17:38
 * @description
 */
@Intercepts(@Signature(type = Executor.class, method = "selectOne", args = {MapperRegistry.MapperData.class, Object.class}))
public class DemoInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        MapperRegistry.MapperData mapperData = (MapperRegistry.MapperData) invocation.getArgs()[0];
        System.out.println(String.format("plugin output parameter %s", JSON.toJSONString(mapperData)));

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
