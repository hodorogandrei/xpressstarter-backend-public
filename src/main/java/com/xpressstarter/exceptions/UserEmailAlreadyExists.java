package com.xpressstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="A user with this email already exists.")
public class UserEmailAlreadyExists extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7934875484997471319L;

	@Override
	public String getMessage() {
		return "A user with this email already exists.";
	}

	
}
