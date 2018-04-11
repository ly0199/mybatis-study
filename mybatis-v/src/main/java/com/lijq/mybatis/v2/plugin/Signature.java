package com.lijq.mybatis.v2.plugin;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lijq
 * @date 2018/4/11 9:42
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({})
public @interface Signature {

    Class<?> type();

    String method();

    Class<?>[] args();
}
