package com.liteprofile.ws.utils.exception;

import com.liteprofile.ws.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    Message message;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getFieldError().getDefaultMessage(), request.getDescription(false), LocalDateTime.now());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ErrorMessage handleNoSuchElementException(NoSuchElementException exception, WebRequest request) {
        return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), message.getDataNotFound(), request.getDescription(false), LocalDateTime.now());
    }

    @ExceptionHandler(DisabledException.class)
    public ErrorMessage handleDisabledException(DisabledException exception, WebRequest request) {
        return new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), message.getAccountDisabled(), request.getDescription(false), LocalDateTime.now());
    }

}
