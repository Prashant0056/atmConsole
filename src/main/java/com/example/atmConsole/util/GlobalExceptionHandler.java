package com.example.atmConsole.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


@ExceptionHandler({RuntimeException.class})
    public ResponseEntity handleRuntime(RuntimeException e)
{
    Map<String,String> body  = Map.of("error message", e.getMessage());
    return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
}


}
