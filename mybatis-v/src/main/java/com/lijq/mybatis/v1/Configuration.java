package com.lijq.mybatis.v1;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lijq
 * @date 2018/4/2 10:54
 * @description
 */
public class Configuration {

    @SuppressWarnings("unchecked")
    public <T> T getMapper(Class clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{clazz},
                new MapperProxy(sqlSession));
    }


    /**
     * hardcode 简化步骤
     * 构造弱版硬编码 Configuration
     * 正常解析xml获取里面的信息
     */
    static class UserMapperXml {

        public static final String namespace = "com.lijq.mybatis.biz.UserMapper";

        public static Map<String, String> methodSqlMapping = new HashMap<>();

        static {
            methodSqlMapping.put("selectByPrimaryKey", "select * from t_user where id = %d");
        }
    }
}

