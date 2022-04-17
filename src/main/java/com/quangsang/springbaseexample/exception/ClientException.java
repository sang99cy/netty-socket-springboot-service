package com.quangsang.springbaseexample.exception;

import com.quangsang.springbaseexample.enums.ErrorCode;
import com.quangsang.springbaseexample.utils.Const;
import lombok.Getter;

@Getter
public class ClientException extends RuntimeException{
    private String message;
    private ErrorCode errorCode;
    private Object payload;
    private String[] args;

    public ClientException(String message) {
        super(message);
    }

    public ClientException(ErrorCode errorCode) {
        super(errorCode.toString());
        this.errorCode = errorCode;
    }

    public ClientException(ErrorCode errorCode, String... args) {
        super(errorCode.toString());
        this.errorCode = errorCode;
        this.args = args;
    }

    public ClientException(String field, ErrorCode errorCode) {
        super(errorCode.toString());
        this.message = field + " " + errorCode.getMessage();
        this.errorCode = errorCode;
    }

    public ClientException(Object payload) {
        super(Const.ERROR);
        this.payload = payload;
    }
}
