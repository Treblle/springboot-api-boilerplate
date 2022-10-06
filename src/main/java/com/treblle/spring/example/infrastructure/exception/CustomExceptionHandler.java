package com.treblle.spring.example.infrastructure.exception;

import com.treblle.spring.example.controller.IAMController;
import com.treblle.spring.example.controller.PostController;
import com.treblle.spring.example.controller.UserController;
import com.treblle.spring.example.service.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(assignableTypes = { IAMController.class, PostController.class, UserController.class })
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
    LOGGER.error("HttpMessageNotReadableException", exception);
    return handleExceptionInternal(
        exception,
        new Response().status(false).message("Invalid request or missing data"),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST,
        request
    );
  }

  @ExceptionHandler(value = { ResponseStatusException.class })
  protected ResponseEntity<Object> handleResponseStatus(ResponseStatusException exception, WebRequest request) {
    LOGGER.error("ResponseStatusException", exception);
    return handleExceptionInternal(
        exception,
        new Response().status(false).message(exception.getReason()),
        new HttpHeaders(),
        exception.getStatus(),
        request);
  }

  @ExceptionHandler(value = { Exception.class })
  protected ResponseEntity<Object> handleGeneric(Exception exception, WebRequest request) {
    LOGGER.error("Exception", exception);
    return handleExceptionInternal(
        exception,
        new Response().status(false).message("Internal service error"),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR,
        request);
  }

}
