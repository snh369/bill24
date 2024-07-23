package com.kosign.bill24.exception;

import com.kosign.bill24.model.dto.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status, WebRequest request
    ) {
        return Response.create(HttpStatus.BAD_REQUEST, Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(NotFoundExceptionClass.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundExceptionClass ex) {
        return Response.create(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NullExceptionClass.class)
    public ResponseEntity<Object> handleNullException(NotFoundExceptionClass ex) {
        return Response.create(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
    }

    @ExceptionHandler(BadRequestExceptionClass.class)
    public ResponseEntity<Object> handleBadRequestException(BadRequestExceptionClass ex) {
        return Response.create(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

}
