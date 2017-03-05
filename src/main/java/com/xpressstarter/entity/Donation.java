package com.xpressstarter.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.xpressstarter.util.DonationStatus;

public class Donation {

	@Id
	private String id;
	@Indexed
	@DBRef
	@NotNull
	private User user;
	@NotNull
	@Min(value=0L)
	private Double amount;
	@NotNull
	private LocalDateTime donatedOn;
	@Indexed
	@DBRef
	@NotNull
	private Campaign campaign;
	@NotNull
	private DonationStatus status;
	
	public Donation(){
		
	}
	public Donation(User user, Double amount, LocalDateTime donatedOn, Campaign campaign, DonationStatus status) {
		super();
		this.user = user;
		this.amount = amount;
		this.donatedOn = donatedOn;
		this.campaign = campaign;
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getBenefactor() {
		return user;
	}
	public void setBenefactor(User user) {
		this.user = user;
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
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
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
