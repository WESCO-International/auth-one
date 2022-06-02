package com.wesco.auth.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotFoundException extends RequestException {
    private static final long serialVersionUID = -3324526085358462965L;
    private String message;

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}