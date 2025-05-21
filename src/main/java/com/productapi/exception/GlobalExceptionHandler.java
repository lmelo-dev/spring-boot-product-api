package com.productapi.exception;

import com.productapi.constant.ErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Global exception handler for the application.
 * Provides consistent error responses across all endpoints.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles IllegalArgumentException by returning a 400 Bad Request response.
     * This is used for validation errors in the controller.
     *
     * @param e The exception containing the error message
     * @return ResponseEntity with BAD_REQUEST status and error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(e.getMessage());
    }

    /**
     * Handles MethodArgumentTypeMismatchException by returning a 400 Bad Request response.
     * This occurs when path variables cannot be converted to the expected type.
     *
     * @param e The exception containing the error details
     * @return ResponseEntity with BAD_REQUEST status and error message
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(String.format(ErrorMessages.INVALID_PARAMETER_TYPE, e.getMessage()));
    }
} 