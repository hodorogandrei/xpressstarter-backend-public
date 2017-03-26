package com.xpressstarter.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xpressstarter.util.CampaignCategory;


public class Campaign {

	@Id
	private String id;
	@Indexed
	@NotNull
	@Size(min=7,max=144)
	private String name;
	@TextIndexed
	@NotNull
	@Size(min=7,max=144)
	private String description;
	@DBRef
	@NotNull
	private User beneficiary;
	@NotNull
	@Min(value=0)
	private BigDecimal target;
	@NotNull
	@Min(value=0)
	private BigDecimal current;
	@NotNull
	private LocalDateTime startedOn;
	@NotNull
	private LocalDateTime expiresOn;
	@NotNull
	private CampaignCategory category;
	@NotNull
	private Boolean isActive;
	@NotNull
	private Boolean isApproved;
	@DBRef
	@NotNull
	private User approvedBy;
	@NotNull
	private List<String> pictures;
	@NotNull
	private String mainPicture;
	@NotNull
	private Integer likeCount;

	public Campaign(){
		pictures=new ArrayList<>();
	}
	
	
	public Campaign(String name, String description, User beneficiary, Double target, Double current,
			LocalDateTime startedOn, LocalDateTime expiresOn, CampaignCategory category, Boolean isActive, User approvedBy) {
		super();
		this.name = name;
		this.description = description;
		this.beneficiary = beneficiary;
		this.target = new BigDecimal(target);
		this.current = new BigDecimal(current);
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
		return target.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}
	public void setTarget(Double target) {
		this.target = new BigDecimal(target);
	}
	public Double getCurrent() {
		return current.setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}
	public void setCurrent(Double current) {
		this.current = new BigDecimal(current);
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

	@JsonProperty("percentage")
	public Float calculatePercentage(){
		BigDecimal percentage = new BigDecimal((float)(this.getCurrent()/this.getTarget())*100);
		return percentage.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
	}


	public List<String> getPictures() {
		return pictures;
	}


	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}


	public String getMainPicture() {
		return mainPicture;
	}


	public void setMainPicture(String mainPicture) {
		this.mainPicture = mainPicture;
	}


	public Integer getLikeCount() {
		return likeCount;
	}


	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvedBy == null) ? 0 : approvedBy.hashCode());
		result = prime * result + ((beneficiary == null) ? 0 : beneficiary.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((current == null) ? 0 : current.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((expiresOn == null) ? 0 : expiresOn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
		result = prime * result + ((isApproved == null) ? 0 : isApproved.hashCode());
		result = prime * result + ((mainPicture == null) ? 0 : mainPicture.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pictures == null) ? 0 : pictures.hashCode());
		result = prime * result + ((startedOn == null) ? 0 : startedOn.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campaign other = (Campaign) obj;
		if (approvedBy == null) {
			if (other.approvedBy != null)
				return false;
		} else if (!approvedBy.equals(other.approvedBy))
			return false;
		if (beneficiary == null) {
			if (other.beneficiary != null)
				return false;
		} else if (!beneficiary.equals(other.beneficiary))
			return false;
		if (category != other.category)
			return false;
		if (current == null) {
			if (other.current != null)
				return false;
		} else if (!current.equals(other.current))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (expiresOn == null) {
			if (other.expiresOn != null)
				return false;
		} else if (!expiresOn.equals(other.expiresOn))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isActive == null) {
			if (other.isActive != null)
				return false;
		} else if (!isActive.equals(other.isActive))
			return false;
		if (isApproved == null) {
			if (other.isApproved != null)
				return false;
		} else if (!isApproved.equals(other.isApproved))
			return false;
		if (mainPicture == null) {
			if (other.mainPicture != null)
				return false;
		} else if (!mainPicture.equals(other.mainPicture))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pictures == null) {
			if (other.pictures != null)
				return false;
		} else if (!pictures.equals(other.pictures))
			return false;
		if (startedOn == null) {
			if (other.startedOn != null)
				return false;
		} else if (!startedOn.equals(other.startedOn))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
	
	
	
}
