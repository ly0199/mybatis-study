package com.lijq.mybatis.v2.exceptions;

import com.lijq.mybatis.v2.executor.ErrorContext;

/**
 * @author Lijq
 * @date 2018/4/11 10:05
 * @description
 */
public class ExceptionFactory {

    /**
     * 禁止实例化
     */
    private ExceptionFactory() {
    }

    public static RuntimeException wrapException(String message, Exception e) {
        return new PersistenceException(ErrorContext.instance().message(message).cause(e).toString(), e);
    }
}
