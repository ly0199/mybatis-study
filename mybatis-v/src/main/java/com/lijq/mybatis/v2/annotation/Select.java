package com.lijq.mybatis.v2.annotation;

import java.lang.annotation.*;

/**
 * @author Lijq
 * @date 2018/4/11 13:33
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {

    String name() default "";

    String value();

}
