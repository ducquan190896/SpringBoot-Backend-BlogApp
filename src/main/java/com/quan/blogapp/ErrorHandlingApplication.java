package com.quan.blogapp;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.quan.blogapp.Exception.EntityNotFoundException;
import com.quan.blogapp.Exception.ErrorResponse;

@ControllerAdvice
public class ErrorHandlingApplication {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handlingNotFoundException(EntityNotFoundException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), ex, LocalDateTime.now());
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlingValidationException(MethodArgumentNotValidException ex) {
        ErrorResponse err = new ErrorResponse(ex.getMessage(), ex, LocalDateTime.now());
        return new ResponseEntity<Object>(err, HttpStatus.BAD_REQUEST);
    }

}
