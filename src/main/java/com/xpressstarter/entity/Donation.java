package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Donation {

	@Id
	private String id;
	private String userId;
	private float amount;
	private LocalDateTime donatedOn;
	private String campaignId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBenefactor() {
		return userId;
	}
	public void setBenefactor(String userId) {
		this.userId = userId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDateTime getDonatedOn() {
		return donatedOn;
	}
	public void setDonatedOn(LocalDateTime donatedOn) {
		this.donatedOn = donatedOn;
	}
	public String getCampaign() {
		return campaignId;
	}
	public void setCampaign(String campaignId) {
		this.campaignId = campaignId;
	}
	
	
}
