package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomRsponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex,WebRequest request)
	{
		ProjectIdExceptionResponse exceptionResponse=new ProjectIdExceptionResponse(ex.getMessage());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler
//	public final ResponseEntity<Object> BacklogwithProjecttaskException(BacklogwithProjecttaskException	ex,WebRequest request)
//	{
//		BacklogwithProjecttaskException exceptionResponse=new BacklogwithProjecttaskException(ex.getMessage());
//		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
//	}
	@ExceptionHandler
	public final ResponseEntity<Object> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException	ex1,WebRequest request)
	{
		UserNameAlreadyExitsResponse exceptionResponse1=new UserNameAlreadyExitsResponse(ex1.getMessage());
		return new ResponseEntity(exceptionResponse1,HttpStatus.BAD_REQUEST);
	}
}
