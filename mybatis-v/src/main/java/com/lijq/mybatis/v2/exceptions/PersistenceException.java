package com.lijq.mybatis.v2.exceptions;

/**
 * @author Lijq
 * @date 2018/4/11 10:01
 * @description
 */
public class PersistenceException extends RuntimeException {

    public PersistenceException() {
        super();
    }

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }
}
