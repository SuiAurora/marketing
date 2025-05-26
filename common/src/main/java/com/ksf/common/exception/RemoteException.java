package com.ksf.common.exception;

import com.ksf.common.errorcode.BaseErrorCode;
import com.ksf.common.errorcode.ErrorCode;

/**
 * 远程服务异常
 */
public class RemoteException extends AbstractException {

    public RemoteException(ErrorCode errorCode) {
        this(null, null, errorCode);
    }

    public RemoteException(String message) {
        this(message, null, BaseErrorCode.REMOTE_ERROR);
    }

    public RemoteException(String message, ErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, ErrorCode errorCode) {
        super(message, throwable, errorCode);
    }

    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}