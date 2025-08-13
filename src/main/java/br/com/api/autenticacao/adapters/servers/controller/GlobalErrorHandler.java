package br.com.api.autenticacao.adapters.servers.controller;

import br.com.api.autenticacao.adapters.servers.contract.response.ErrorResponse;
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
    ResponseEntity<ErrorResponse> usuarioInvalidException(UsuarioUnauthorizedException e, WebRequest request) {
        HttpStatus STATUS_CODE = HttpStatus.UNAUTHORIZED;

        ErrorResponse response = ErrorResponse.builder()
                .status(STATUS_CODE.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, STATUS_CODE);
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    ResponseEntity<ErrorResponse> usuarioNotFoundException(UsuarioNotFoundException e, WebRequest request) {
        HttpStatus STATUS_CODE = HttpStatus.NOT_FOUND;

        ErrorResponse response = ErrorResponse.builder()
                .status(STATUS_CODE.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, STATUS_CODE);
    }

    @ExceptionHandler(UsuarioExistingException.class)
    ResponseEntity<ErrorResponse> usuarioExistedException(UsuarioExistingException e, WebRequest request) {
        HttpStatus STATUS_CODE = HttpStatus.CONFLICT;

        ErrorResponse response = ErrorResponse.builder()
                .status(STATUS_CODE.value())
                .message(e.getMessage())
                .build();

        return new ResponseEntity<>(response, STATUS_CODE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(FieldError::getDefaultMessage)
                                .collect(Collectors.toList())
                                .toString()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                        ex.getConstraintViolations()
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .collect(Collectors.toList())
                                .toString()));
    }
}
