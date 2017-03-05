package com.xpressstarter.exceptions;

public class UserDoesNotExistException extends RuntimeException{

	/**
	 * Used when a userId is used, but which is not present in the database
	 */
	private static final long serialVersionUID = -7436907312637975998L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "User does not exist.";
	}
}
