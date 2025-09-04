package br.com.api.autenticacao.adapters.servers.controller;

import br.com.api.autenticacao.adapters.servers.contract.response.ErrorResponse;
import br.com.api.autenticacao.adapters.servers.contract.response.ErrorDataResponse;
import br.com.api.autenticacao.domain.exception.UsuarioExistingException;
import br.com.api.autenticacao.domain.exception.UsuarioUnauthorizedException;
import br.com.api.autenticacao.domain.exception.UsuarioNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(UsuarioUnauthorizedException.class)
    ResponseEntity<ErrorDataResponse> usuarioInvalidException(UsuarioUnauthorizedException e, WebRequest request) {
        HttpStatus STATUS_CODE = HttpStatus.UNAUTHORIZED;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(STATUS_CODE.value())
                .message(e.getMessage())
                .build();

        ErrorDataResponse response = ErrorDataResponse.builder()
                .data(errorResponse)
                .build();

        return new ResponseEntity<>(response, STATUS_CODE);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    ResponseEntity<ErrorDataResponse> usuarioNotFoundException(UsuarioNotFoundException e, WebRequest request) {
        HttpStatus STATUS_CODE = HttpStatus.NOT_FOUND;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(STATUS_CODE.value())
                .message(e.getMessage())
                .build();

        ErrorDataResponse response = ErrorDataResponse.builder()
                .data(errorResponse)
                .build();

        return new ResponseEntity<>(response, STATUS_CODE);
    }

    @ExceptionHandler(UsuarioExistingException.class)
    ResponseEntity<ErrorDataResponse> usuarioExistedException(UsuarioExistingException e, WebRequest request) {
        HttpStatus STATUS_CODE = HttpStatus.CONFLICT;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(STATUS_CODE.value())
                .message(e.getMessage())
                .build();

        ErrorDataResponse response = ErrorDataResponse.builder()
                .data(errorResponse)
                .build();

        return new ResponseEntity<>(response, STATUS_CODE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDataResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList())
                        .toString());

        ErrorDataResponse response = ErrorDataResponse.builder()
                .data(errorResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDataResponse> constraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                ex.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .collect(Collectors.toList())
                        .toString());

        ErrorDataResponse response = ErrorDataResponse.builder()
                .data(errorResponse)
                .build();

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }
}
