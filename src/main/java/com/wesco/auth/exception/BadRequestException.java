
package com.wesco.auth.exception;

import org.springframework.http.HttpStatus;


public class BadRequestException extends RequestException {

    private static final long serialVersionUID = -5299825665351378936L;

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}