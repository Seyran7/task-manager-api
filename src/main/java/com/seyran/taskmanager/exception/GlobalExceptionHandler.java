package com.seyran.taskmanager.exception;

import com.seyran.taskmanager.dto.ApiResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;


public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ChangeSetPersister.NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFoundException(NotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.<Void>builder()
                        .success(false)
                        .message(e.getMessage())
                        .data(null)
                        .build()
        );
    }
}
