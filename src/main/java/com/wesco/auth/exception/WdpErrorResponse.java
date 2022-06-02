package com.wesco.auth.exception;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class WdpErrorResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private  WdpErrorCode errorCode;

    private final Date timestamp;

    protected WdpErrorResponse(final String message, final WdpErrorCode errorCode, HttpStatus status) {
        this.message = message;
        //this.errorCode = errorCode;
        this.status = status;
        this.timestamp = new Date();
    }

    protected WdpErrorResponse(final String message, HttpStatus status) {
        this.message = message;
        this.status = status;
        this.timestamp = new Date();
    }

    public static WdpErrorResponse of(final String message, final WdpErrorCode errorCode, HttpStatus status) {
        return new WdpErrorResponse(message, errorCode, status);
    }
    public static WdpErrorResponse ErrorOf(final String message, HttpStatus status) {
        return new WdpErrorResponse(message,status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public WdpErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
