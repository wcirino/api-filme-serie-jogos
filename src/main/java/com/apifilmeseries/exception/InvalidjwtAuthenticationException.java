package com.apifilmeseries.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidjwtAuthenticationException extends AuthenticationException{

	private static final long serialVersionUID = 1L;

	public InvalidjwtAuthenticationException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
