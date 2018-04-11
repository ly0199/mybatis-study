package com.lijq.mybatis.v2.plugin;

import java.lang.annotation.*;

/**
 * @author Lijq
 * @date 2018/4/11 9:40
 * @description
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    Signature[] value();
}
