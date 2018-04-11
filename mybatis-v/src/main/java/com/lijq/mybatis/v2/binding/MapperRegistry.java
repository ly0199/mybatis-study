package com.lijq.mybatis.v2.binding;

import com.lijq.mybatis.v2.annotation.Select;
import com.lijq.mybatis.v2.session.Configuration;
import com.lijq.mybatis.v2.session.SqlSession;
import lombok.Data;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lijq
 * @date 2018/4/8 13:15
 * @description
 */
public class MapperRegistry {

    @Getter
    private final Configuration config;

    public MapperRegistry(Configuration config) {
        this.config = config;
    }

    private Map<String, MapperData> sqlMethodMapping = new HashMap<>();
    private Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    public <T> T get(String key) {
        return (T) sqlMethodMapping.get(key);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type " + type + " is not know to the MapperRegistry.");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }

    public <T> boolean hasMapper(Class<T> type) {
        return knownMappers.containsKey(type);
    }

    @SuppressWarnings("unchecked")
    public <T> void addMapper(Class<T> type) {
        if (type.isInterface()) {
            if (hasMapper(type)) {
                throw new RuntimeException("Type " + type + " is already to the MapperRegistry.");
            }

            knownMappers.put(type, new MapperProxyFactory<>(type));

            Method[] methods = type.getMethods();
            for (Method method : methods) {

                String name = method.getDeclaringClass().getName() + "." + method.getName();

                Select select = method.getAnnotation(Select.class);
                if (select != null) {
                    name = select.name().length() > 0 ? select.name() : name;
                    String sql = select.value();
                    sqlMethodMapping.put(name, new MapperData(sql, method.getReturnType()));
                }

            }
        }
    }


    @Data
    public static class MapperData<T> {
        private String sql;
        private Class<T> type;

        public MapperData(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }
    }
}
