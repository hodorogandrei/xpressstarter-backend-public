package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Like {
	@Id
	private String id;
	@Indexed
	private String userId;
	@Indexed
	private String campaignId;
	private LocalDateTime givenOn;
	
	public Like(){
		
	}
	public String getUser() {
		return userId;
	}
	public void setUser(String userId) {
		this.userId = userId;
	}
	public String getCampaign() {
		return campaignId;
	}
	public void setCampaign(String campaignId) {
		this.campaignId = campaignId;
	}
	public LocalDateTime getGivenOn() {
		return givenOn;
	}
	public void setGivenOn(LocalDateTime givenOn) {
		this.givenOn = givenOn;
	}
	
	
}
