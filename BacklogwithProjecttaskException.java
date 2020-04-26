package com.example.demo;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class BacklogwithProjecttaskException extends RuntimeException {
	public BacklogwithProjecttaskException(String message)
	{
		super(message);
	}

}
