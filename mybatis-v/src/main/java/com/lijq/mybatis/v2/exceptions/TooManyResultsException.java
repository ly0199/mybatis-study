package com.lijq.mybatis.v2.exceptions;

/**
 * @author Lijq
 * @date 2018/4/11 10:04
 * @description
 */
public class TooManyResultsException extends PersistenceException {

    public TooManyResultsException() {
        super();
    }

    public TooManyResultsException(String message) {
        super(message);
    }

    public TooManyResultsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyResultsException(Throwable cause) {
        super(cause);
    }
}
