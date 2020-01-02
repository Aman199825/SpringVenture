package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler
{
   @ExceptionHandler
    public final ResponseEntity<Object> handleProjectException(ProjectIdException ex, WebRequest req)
   {
       ProjectIdExceptionResponse exceptionResponse=new ProjectIdExceptionResponse(ex.getMessage());
       return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
   }
}
