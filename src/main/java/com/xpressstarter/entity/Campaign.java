package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;


public class Campaign {

	@Id
	private String id;
	private String Name;
	private String Description;
	private User beneficiary;
	private Integer target;
	private Integer current;
	private LocalDateTime startedOn;
	private LocalDateTime expiresOn;
	
	public Campaign(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public User getBeneficiary() {
		return beneficiary;
	}
	public void setBeneficiary(User beneficiary) {
		this.beneficiary = beneficiary;
	}
	public Integer getTarget() {
		return target;
	}
	public void setTarget(Integer target) {
		this.target = target;
	}
	public Integer getCurrent() {
		return current;
	}
	public void setCurrent(Integer current) {
		this.current = current;
	}
	public LocalDateTime getStartedOn() {
		return startedOn;
	}
	public void setStartedOn(LocalDateTime startedOn) {
		this.startedOn = startedOn;
	}
	public LocalDateTime getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(LocalDateTime expiresOn) {
		this.expiresOn = expiresOn;
	}
	
	
}
