package com.lijq.mybatis.v2.executor;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Lijq
 * @date 2018/4/11 11:48
 * @description
 */
public class ResultHandler {

    public <T> T handler(ResultSet rs, Class<T> type) throws Exception {

        T entity = type.newInstance();

        if (rs.next()) {
            for (Field field : type.getDeclaredFields()) {

                Method method = entity.getClass().getMethod("set" + StringUtils.capitalize(field.getName()), field.getType());

                System.out.println(method.getName());
                method.invoke(entity, getValue(rs, field));
            }
        }

        return entity;
    }


    private Object getValue(ResultSet rs, Field field) throws SQLException {

        System.out.println("field -->" + field.getType() + " " + field.getName());

        // 可以做一些特殊处理

        return rs.getObject(field.getName());
    }


}
