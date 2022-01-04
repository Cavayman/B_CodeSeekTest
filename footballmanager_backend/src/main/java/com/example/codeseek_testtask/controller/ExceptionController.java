package com.example.codeseek_testtask.controller;

import com.example.codeseek_testtask.exception.AlreadyExistsException;
import com.example.codeseek_testtask.exception.NotFoundException;
import com.example.codeseek_testtask.exception.TransferException;
import com.example.codeseek_testtask.model.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(NotFoundException exception, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO(
                exception.getMessage(),
                request.getRequest().getRequestURI(),
                request.getRequest().getMethod()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleAlreadyExistsException(AlreadyExistsException exception, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO(
                exception.getMessage(),
                request.getRequest().getRequestURI(),
                request.getRequest().getMethod()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TransferException.class)
    public ResponseEntity<ErrorResponseDTO> handleTransferException(TransferException exception, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDTO(
                exception.getMessage(),
                request.getRequest().getRequestURI(),
                request.getRequest().getMethod()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, ServletWebRequest request) {
        final List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        Set<String> messages = fieldErrors
                .stream()
                .map(item -> String.format("Error: %s, Cause: %s == '%s'.", item.getDefaultMessage(), item.getField(), item.getRejectedValue()))
                .collect(Collectors.toSet());

        String errMessages = String.join(" ", messages);

        return new ResponseEntity<>(new ErrorResponseDTO(
                errMessages, request.getRequest().getRequestURI(),
                request.getRequest().getMethod()),
                HttpStatus.BAD_REQUEST);
    }
}
