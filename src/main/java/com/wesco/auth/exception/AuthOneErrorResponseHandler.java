package com.wesco.auth.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class AuthOneErrorResponseHandler extends ResponseEntityExceptionHandler implements AccessDeniedHandler {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    @ExceptionHandler(AccessDeniedException.class)
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.FORBIDDEN.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("You don't have permission to perform this operation!",
                            WdpErrorCode.PERMISSION_DENIED, HttpStatus.FORBIDDEN));
        }
    }

    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(HttpServletRequest request, HttpServletResponse response,
                       NotFoundException notFoundException) throws IOException,
            ServletException {
        log.error("Not found exception [{}]", notFoundException.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of(notFoundException.getMessage(),
                            WdpErrorCode.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND));
        }
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public void handleDataIntegrityViolationException(HttpServletRequest request, HttpServletResponse response,
                                                      DataIntegrityViolationException dataIntegrityViolationException) throws IOException,
            ServletException {
        log.error("Data integrity exception [{}]", dataIntegrityViolationException.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("Violation!! Data Integrity",
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST));
        }
    }

    @ExceptionHandler(SQLException.class)
    public void handleSQLException(HttpServletRequest request, HttpServletResponse response,
                                   SQLException exception) throws IOException,
            ServletException {
        log.error("SQL exception [{}]", exception.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("Violation!! SQL",
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST));
        }
    }

    @ExceptionHandler(InvalidFormatException.class)
    public void handleInvalidFormatException(HttpServletRequest request, HttpServletResponse response,
                                       InvalidFormatException exception) throws IOException,
            ServletException {
        log.error("Runtime exception [{}]", exception.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("OOPS! something went wrong in runtime!",
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }

    @ExceptionHandler(AuthenticationException.class)
    public void handleAuthenticationException(HttpServletRequest request, HttpServletResponse response,
                                          AuthenticationException exception) throws IOException,
            ServletException {
        log.error("Generic exception [{}]", exception.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("Authentication failed!",
                            WdpErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        }
    }

    @ExceptionHandler(BadRequestException.class)
    public void handleBadRequestException(HttpServletRequest request, HttpServletResponse response,
                                              BadRequestException exception) throws IOException,
            ServletException {
        log.error("Generic exception [{}]", exception.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of(exception.getMessage(),
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST));
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.ErrorOf(exception.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @ExceptionHandler(NumberFormatException.class)
    public void handleNumberFormatException(HttpServletRequest request, HttpServletResponse response,
                                            NumberFormatException exception) throws IOException,
            ServletException {
        log.error("NumberFormatException exception [{}]", exception.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("Invalid data.",
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST));
        }
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public void handleInvalidDataAccessApiUsageException(HttpServletRequest request, HttpServletResponse response,
                                            InvalidDataAccessApiUsageException exception) throws IOException,
            ServletException {
        log.error("InvalidDataAccessApiUsageException exception [{}]", exception.getMessage());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of("Invalid data access.",
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.BAD_REQUEST));
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void handleException(HttpServletRequest request, HttpServletResponse response,
                                                   Exception exception) throws IOException,
            ServletException {
         log.error("Generic exception [{}]", exception.toString());
        if (!response.isCommitted()) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            String errorMessage = ObjectUtils.isEmpty(exception.getMessage()) ? exception.getMessage() : "OOPS! something went wrong!!!";
            mapper.writeValue(response.getWriter(),
                    WdpErrorResponse.of(errorMessage,
                            WdpErrorCode.BAD_REQUEST_PARAMS, HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
}
