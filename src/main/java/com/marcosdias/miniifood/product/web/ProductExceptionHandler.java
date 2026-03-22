package com.marcosdias.miniifood.product.web;

import com.marcosdias.miniifood.product.service.ProductAlreadyExistsException;
import com.marcosdias.miniifood.product.service.ProductNotFoundException;
import com.marcosdias.miniifood.user.web.dto.ApiError;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ProductNotFoundException ex) {
        ApiError error = new ApiError(
            HttpStatus.NOT_FOUND.value(),
            "Product not found",
            OffsetDateTime.now(),
            List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleConflict(ProductAlreadyExistsException ex) {
        ApiError error = new ApiError(
            HttpStatus.CONFLICT.value(),
            "Product conflict",
            OffsetDateTime.now(),
            List.of(ex.getMessage())
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}

