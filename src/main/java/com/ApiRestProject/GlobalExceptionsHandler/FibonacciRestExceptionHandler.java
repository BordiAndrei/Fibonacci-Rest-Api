package com.ApiRestProject.GlobalExceptionsHandler;

import com.ApiRestProject.Exception.FibonacciException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FibonacciRestExceptionHandler {

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<FibonacciErrorResponse> handleException (FibonacciException exc){

        // create a StudentErrorResponse
        FibonacciErrorResponse errorResponse = new FibonacciErrorResponse();

        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Add another exception handler __ to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<FibonacciErrorResponse> handlerException(Exception exc){

        FibonacciErrorResponse studentErrorResponse = new FibonacciErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(exc.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
