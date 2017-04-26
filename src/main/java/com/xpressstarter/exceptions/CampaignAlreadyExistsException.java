package com.xpressstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Campaign already exists.")
public class CampaignAlreadyExistsException extends RuntimeException{

	/**
	 * This exception is used when trying to add an already existing Campaign
	 */
	private static final long serialVersionUID = 3188039192328295752L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Campaign already exists.";
	}
}
