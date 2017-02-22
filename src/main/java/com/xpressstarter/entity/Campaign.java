package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;

import com.xpressstarter.util.CampaignCategory;


public class Campaign {

	@Id
	private String id;
	@Indexed
	private String name;
	@TextIndexed
	private String description;
	private String beneficiaryId;
	private Double target;
	private Double current;
	private LocalDateTime startedOn;
	private LocalDateTime expiresOn;
	private CampaignCategory category;
	private Boolean isActive;
	private Boolean isApproved;
	private String approvedBy;
	public Campaign(){
		
	}
	
	
	public Campaign(String name, String description, String beneficiaryId, Double target, Double current,
			LocalDateTime startedOn, LocalDateTime expiresOn, CampaignCategory category, Boolean isActive) {
		super();
		this.name = name;
		this.description = description;
		this.beneficiaryId = beneficiaryId;
		this.target = target;
		this.current = current;
		this.startedOn = startedOn;
		this.expiresOn = expiresOn;
		this.category = category;
		this.isActive = isActive;
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
	
	public String getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
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

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	
}
