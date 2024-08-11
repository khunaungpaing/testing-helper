package com.khun.testinghelper.exception;

import com.khun.testinghelper.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        var response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "bad request",
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        var response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "not found",
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorResponse> handleResourceConflictException(ResourceConflictException e) {
        var response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "conflict",
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
