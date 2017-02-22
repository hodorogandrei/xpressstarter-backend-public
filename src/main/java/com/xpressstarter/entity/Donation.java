package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.xpressstarter.util.DonationStatus;

public class Donation {

	@Id
	private String id;
	@Indexed
	private String userId;
	private Double amount;
	private LocalDateTime donatedOn;
	@Indexed
	private String campaignId;
	private DonationStatus status;
	
	public Donation(){
		
	}
	public Donation(String userId, Double amount, LocalDateTime donatedOn, String campaignId, DonationStatus status) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.donatedOn = donatedOn;
		this.campaignId = campaignId;
		this.status = status;
	}
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
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
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
	public DonationStatus getStatus(){
		return status;
	}
	public void setStatusPending(){
		status=DonationStatus.PENDING;
	}
	public void setStatusOK(){
		status=DonationStatus.OK;
	}
	public void setStatusRevoked(){
		status=DonationStatus.REVOKED;
	}
}
