package com.liteprofile.ws.utils.exception;

import com.liteprofile.ws.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    Message message;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
        return new ErrorMessage(HttpStatus.BAD_REQUEST.value(),exception.getFieldError().getDefaultMessage(),request.getDescription(false),LocalDateTime.now());
    }
}
