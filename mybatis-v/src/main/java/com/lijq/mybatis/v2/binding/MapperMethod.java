package com.lijq.mybatis.v2.binding;

import com.lijq.mybatis.v2.annotation.Select;
import com.lijq.mybatis.v2.session.SqlSession;

import java.lang.reflect.Method;

/**
 * @author Lijq
 * @date 2018/4/8 13:28
 * @description
 */
public class MapperMethod {

    private Class<?> classInterface;
    private Method method;
    private String sql;

    public MapperMethod(Class<?> classInterface, Method method) {
        this.classInterface = classInterface;
        this.method = method;
    }


    public Object execute(SqlSession sqlSession, Object[] args) {

        Object result;

        String methodName = classInterface.getName() + "." + method.getName();

        Select select = method.getAnnotation(Select.class);
        if (select != null) {
            methodName = select.name().length() > 0 ? select.name() : methodName;
            return sqlSession.select(methodName, args);
        }

        // TODO  还可以添加别的 Insert Delete Update

        return null;
    }
}
