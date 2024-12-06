package com.airtribe.newsaggregatorapp.news_aggregator_app.exceptionhandling;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    // For Handling exceptions related to User or any Resource not found
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Resource Not Found");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // For Handling exceptions related to User already present in system
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleResourceNotFound(UserAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "User Already Exists");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.IM_USED);
    }

    // For Handling exceptions related constraints violated during creating a user
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ConstraintViolationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Constraint Violated");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    // For Handling exceptions related constraints violated during creating a user
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleResourceNotFound(UserNotAuthorizedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "User Not Authorized");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
