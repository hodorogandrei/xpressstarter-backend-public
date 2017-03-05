package com.xpressstarter.exceptions;

public class CampaignDoesNotExistException extends RuntimeException{

	/**
	 * Used when a campaign does not exist
	 */
	private static final long serialVersionUID = 7165239889545778183L;
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Campaign does not exist.";
	}
}
