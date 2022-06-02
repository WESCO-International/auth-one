
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
public abstract class RequestException extends RuntimeException {
    private static final long serialVersionUID = -694092955495136208L;
    private String message;
    public abstract HttpStatus getStatus();
}