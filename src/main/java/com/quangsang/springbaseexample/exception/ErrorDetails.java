package com.quangsang.springbaseexample.exception;

import com.quangsang.springbaseexample.enums.ErrorCode;

import java.util.Date;

public class ErrorDetails {
    private ErrorCode errorCode;
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(ErrorCode errorCode,Date timestamp, String message, String details) {
        super();
        this.errorCode = errorCode;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
