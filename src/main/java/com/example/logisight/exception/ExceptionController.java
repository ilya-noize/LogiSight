package com.example.logisight.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestController
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public AppError handleValidationExceptions(MethodArgumentNotValidException ex) {
        return loggingAndErrorHandling("Validation Error", BAD_REQUEST, ex);
    }

    @ExceptionHandler({IllegalArgumentException.class, ValidationException.class})
    @ResponseStatus(BAD_REQUEST)
    public AppError handleIllegalArgumentException(IllegalArgumentException ex) {
        return loggingAndErrorHandling("Client error", BAD_REQUEST, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public AppError handleEntityNotFoundExceptions(EntityNotFoundException ex) {
        return loggingAndErrorHandling("Server Error", NOT_FOUND, ex);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public AppError handleExceptions(Exception ex) {
        return loggingAndErrorHandling("Server Error", INTERNAL_SERVER_ERROR, ex);
    }

    private static AppError loggingAndErrorHandling(String message, HttpStatus status, Exception ex) {
        log.error(message, ex);
        return new AppError(status.value(), message, new Date());
    }
}
