package com.quangsang.springbaseexample.exception;

import com.quangsang.springbaseexample.enums.ErrorCode;
import com.quangsang.springbaseexample.utils.MessageUtil;


public class ErrorDetailPlus {
    private String code;
    private String message;
    private String debugMessage;
    private long timestamp;
    private String details;

    public ErrorDetailPlus(ErrorCode errorCode,String details) {
        this.code = errorCode.getCode();
        if(errorCode.getCode().equals("00")) {
            this.message = MessageUtil.getMessage("en_success");
        } else {
            this.message = MessageUtil.getMessage(ErrorCode.SERVER_ERROR.getMessage());
        }
        this.timestamp = System.currentTimeMillis();
        this.details = details;
    }

    public ErrorDetailPlus(Exception ex) {
        this.timestamp = System.currentTimeMillis();
        if (ex instanceof ServerException) {
            ServerException e = (ServerException) ex;
            if (e.getErrorCode() != null) {
                this.code = e.getErrorCode().getCode();
                this.message = MessageUtil.getMessage(e.getErrorCode().getMessage(), e.getArgs());
            } else {
                this.code = ErrorCode.SERVER_ERROR.getCode();
                this.message = MessageUtil.getMessage(ErrorCode.SERVER_ERROR.getMessage());
            }
        } else if (ex instanceof ClientException) {
            ClientException e = (ClientException) ex;
            if (e.getErrorCode() != null) {
                this.code = e.getErrorCode().getCode();
                this.message = MessageUtil.getMessage(e.getErrorCode().getMessage(), e.getArgs());
            } else {
                this.code = ErrorCode.SERVER_ERROR.getCode();
                this.message = MessageUtil.getMessage(ErrorCode.SERVER_ERROR.getMessage());
            }
        } else {
            this.code = ErrorCode.SERVER_ERROR.getCode();
            this.message = MessageUtil.getMessage(ErrorCode.SERVER_ERROR.getMessage());
        }
        this.debugMessage = ex.getMessage();
    }

    public static ErrorDetailPlus build(Exception ex) {
        return new ErrorDetailPlus(ex);
    }

    public static ErrorDetailPlus build(ErrorCode errorCode,String details) {
        return new ErrorDetailPlus(errorCode,details);
    }

}
