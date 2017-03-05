package com.xpressstarter.exceptions;

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
