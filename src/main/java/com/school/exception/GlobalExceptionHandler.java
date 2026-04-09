package com.school.exception;

import com.school.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 🔴 Validation Error Handler
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return new ApiResponse<>(400, "Validation Failed", errors);
    }

    // 🔴 Not Found Handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiResponse<String> handleNotFound(ResourceNotFoundException ex) {
        return new ApiResponse<>(404, ex.getMessage(), null);
    }

    // 🔴 Generic Error
    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleGeneric(Exception ex) {
        return new ApiResponse<>(500, "Something went wrong", null);
    }
}