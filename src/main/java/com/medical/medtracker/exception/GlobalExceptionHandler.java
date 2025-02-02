package com.medical.medtracker.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleInvalidType() {
        return ResponseEntity.badRequest().body(
            new ErrorResponse("Invalid moving average type. Valid values: SMA, EMA, WMA")
        );
    }
    
    private record ErrorResponse(String message) {}
}
