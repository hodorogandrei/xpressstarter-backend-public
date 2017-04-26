package com.xpressstarter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Campaign was already like by this user.")
public class AlreadyLikedCampaignException extends RuntimeException {

	/**
	 * Used when adding a new exception
	 */
	private static final long serialVersionUID = 7746085303927375259L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Campaign was already like by this user.";
	}

}
