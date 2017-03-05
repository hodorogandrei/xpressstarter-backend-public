package com.xpressstarter.exceptions;

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
