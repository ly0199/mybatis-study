package com.lijq.mybatis.v2.plugin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Lijq
 * @date 2018/4/11 9:31
 * @description
 */
@AllArgsConstructor
public class Invocation {

    @Getter
    private Object target;
    @Getter
    private Method method;
    @Getter
    private Object[] args;


    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(target, args);
    }
}
