package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Like {
	@Id
	private String id;
	@Indexed
	@DBRef
	private User user;
	@Indexed
	@DBRef
	private Campaign campaign;
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
	
	
	
	
}
