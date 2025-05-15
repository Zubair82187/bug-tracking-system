package com.bug_tracker.exception;

import com.bug_tracker.model.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullValuesException.class)
    public ResponseEntity<Object> handleNullValues(NullValuesException ex){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(DeletionFailedException.class)
    public ResponseEntity<Object> handleDeletionFailed(DeletionFailedException ex){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NotCreatedException.class)
    public ResponseEntity<Object> handleNotCreated(NotCreatedException ex){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_MODIFIED.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_MODIFIED);
    }

    @ExceptionHandler(NotAuthenticatedException.class)
    public ResponseEntity<Object> authenticationException(NotAuthenticatedException ex){
        ResponseError error = new ResponseError(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return new  ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }
}
