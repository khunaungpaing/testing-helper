package com.khun.testinghelper.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFoundException(ResourceNotFoundException ex) {
        return "404";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "500";
    }
}
