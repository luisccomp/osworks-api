package com.luisccomp.osworks.api.controller;

import com.luisccomp.osworks.exception.NotFoundException;
import com.luisccomp.osworks.representation.dto.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleNotFound(RuntimeException ex, WebRequest request) {
        return createResponse(ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                          HttpHeaders headers,
                                                          HttpStatus status,
                                                          WebRequest request) {
        final var fields = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error -> {
                    final var field = ErrorMessage.Field.builder()
                            .fieldName(((FieldError) error).getField())
                            .errorMessage(error.getDefaultMessage())
                            .build();

                    return field;
                })
                .collect(Collectors.toList());

        final var errorMessage = ErrorMessage.builder()
                .status(status.value())
                .message(ex.getMessage())
                .fields(fields)
                .build();

        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }

    private ResponseEntity createResponse(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final var errorMessage = ErrorMessage.builder()
                .status(status.value())
                .message(ex.getMessage())
                .build();

        return handleExceptionInternal(ex, errorMessage, headers, status, request);
    }

}
