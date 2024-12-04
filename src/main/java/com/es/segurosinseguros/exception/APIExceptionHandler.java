package com.es.segurosinseguros.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class APIExceptionHandler {
    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient handleBadRequest(HttpServletRequest request, Exception e){
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient handleValidationException(HttpServletRequest request, ValidationException e){
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageForClient handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException e){
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessageForClient handleDuplicateException(HttpServletRequest request, DuplicateException e){
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }
}
