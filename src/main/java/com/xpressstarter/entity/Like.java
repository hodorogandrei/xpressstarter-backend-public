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
	public LocalDateTime getGivenOn() {
		return givenOn;
	}
	public void setGivenOn(LocalDateTime givenOn) {
		this.givenOn = givenOn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCampaignId() {
		return campaignId;
	}
	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}
	
	
	
}
