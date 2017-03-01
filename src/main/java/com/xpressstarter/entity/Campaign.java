package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.xpressstarter.util.CampaignCategory;


public class Campaign {

	@Id
	private String id;
	@Indexed
	private String name;
	@TextIndexed
	private String description;
	@DBRef
	private User beneficiary;
	private Double target;
	private Double current;
	private LocalDateTime startedOn;
	private LocalDateTime expiresOn;
	private CampaignCategory category;
	private Boolean isActive;
	private Boolean isApproved;
	@DBRef
	private User approvedBy;

	public Campaign(){
		
	}
	
	
	public Campaign(String name, String description, User beneficiary, Double target, Double current,
			LocalDateTime startedOn, LocalDateTime expiresOn, CampaignCategory category, Boolean isActive, User approvedBy) {
		super();
		this.name = name;
		this.description = description;
		this.beneficiary = beneficiary;
		this.target = target;
		this.current = current;
		this.startedOn = startedOn;
		this.expiresOn = expiresOn;
		this.category = category;
		this.isActive = isActive;
		this.approvedBy=approvedBy;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(User beneficiary) {
		this.beneficiary = beneficiary;
	}

	public Double getTarget() {
		return target;
	}
	public void setTarget(Double target) {
		this.target = target;
	}
	public Double getCurrent() {
		return current;
	}
	public void setCurrent(Double current) {
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

	public CampaignCategory getCategory() {
		return category;
	}

	public void setCategory(CampaignCategory category) {
		this.category = category;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public User getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}


	
	
	
}
