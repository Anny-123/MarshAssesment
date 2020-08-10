package com.marsh.Assessment.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice ///to shared across multiple controllers
public class EmployeeResponseExceptionHandler  extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleGenericExcptions(Exception ex ,WebRequest request)
	{
		
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(exceptionDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(EmployeeException.class)
	public final ResponseEntity<Object> handleEmployeeNotFoundExcptions(EmployeeException ex ,WebRequest request)
	{
		
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(exceptionDetails,HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		ExceptionDetails exceptionDetails=new ExceptionDetails(new Date(), "Incorrect Data", ex.getBindingResult().toString());
		return new ResponseEntity(exceptionDetails,HttpStatus.BAD_REQUEST);
	}
}
