package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Like {
	@Id
	private String id;
	private User user;
	private Campaign campaign;
	private LocalDateTime givenOn;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	public LocalDateTime getGivenOn() {
		return givenOn;
	}
	public void setGivenOn(LocalDateTime givenOn) {
		this.givenOn = givenOn;
	}
	
	
}
