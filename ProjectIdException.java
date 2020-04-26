package com.example.demo;

import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.HttpStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException {
	public ProjectIdException(String message)
	{
		super(message);
	}

}
