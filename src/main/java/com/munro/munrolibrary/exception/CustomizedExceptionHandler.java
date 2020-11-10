package com.munro.munrolibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllException(Exception ex, WebRequest request){
        return getResponseEntity(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MinGreaterThanMaxException.class)
    public final ResponseEntity handleMethodArgumentTypeMismatchException(Exception ex, WebRequest request){
        return getResponseEntity(ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity getResponseEntity(String message, String description, HttpStatus status){
        ExceptionResponse response = ExceptionResponse.builder().timestamp(new Date()).message(message).details(description).build();
        return new ResponseEntity(response, status);
    }
}
