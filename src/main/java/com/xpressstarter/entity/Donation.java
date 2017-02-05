package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public class Donation {

	@Id
	private String id;
	private User benefactor;
	private float amount;
	private LocalDateTime donatedOn;
	private Campaign campaign;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getBenefactor() {
		return benefactor;
	}
	public void setBenefactor(User benefactor) {
		this.benefactor = benefactor;
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
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	
}
