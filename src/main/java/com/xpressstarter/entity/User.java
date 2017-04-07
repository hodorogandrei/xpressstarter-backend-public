package com.xpressstarter.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xpressstarter.util.Role;


public class User{

	@Id
	private String id;
	@Indexed
	@NotNull
	@Size(min=3,max=14)
	private String firstname;
	@Indexed
	@NotNull
	@Size(min=3,max=14)
	private String lastname;
	@Indexed
	@NotNull
	@Email
	private String email;
	@JsonIgnore
	private String passwordHash;
	@NotNull
	private Boolean wantsToReceiveEmail;
	@NotNull
	private LocalDateTime memberSince;
	@NotNull
	private Role role;
	@NotNull
	private String profilePicture;
	@NotNull
	private double totalDonated;
	public User(){
		
	}
	
	public User(String firstname, String lastname, String email, String passwordHash, Boolean wantsToReceiveEmail,
			LocalDateTime memberSince, Role role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.passwordHash = passwordHash;
		this.wantsToReceiveEmail = wantsToReceiveEmail;
		this.memberSince = memberSince;
		this.role = role;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Boolean getWantsToReceiveEmail() {
		return wantsToReceiveEmail;
	}
	public void setWantsToReceiveEmail(Boolean wantsToReceiveEmail) {
		this.wantsToReceiveEmail = wantsToReceiveEmail;
	}
	public LocalDateTime getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(LocalDateTime memberSince) {
		this.memberSince = memberSince;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	
	public double getTotalDonated() {
		return new BigDecimal(totalDonated).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}

	public void setTotalDonated(double totalDonated) {
		this.totalDonated = totalDonated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((memberSince == null) ? 0 : memberSince.hashCode());
		result = prime * result + ((passwordHash == null) ? 0 : passwordHash.hashCode());
		result = prime * result + ((profilePicture == null) ? 0 : profilePicture.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((wantsToReceiveEmail == null) ? 0 : wantsToReceiveEmail.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (memberSince == null) {
			if (other.memberSince != null)
				return false;
		} else if (!memberSince.equals(other.memberSince))
			return false;
		if (passwordHash == null) {
			if (other.passwordHash != null)
				return false;
		} else if (!passwordHash.equals(other.passwordHash))
			return false;
		if (profilePicture == null) {
			if (other.profilePicture != null)
				return false;
		} else if (!profilePicture.equals(other.profilePicture))
			return false;
		if (role != other.role)
			return false;
		if (wantsToReceiveEmail == null) {
			if (other.wantsToReceiveEmail != null)
				return false;
		} else if (!wantsToReceiveEmail.equals(other.wantsToReceiveEmail))
			return false;
		return true;
	}
	
	
	
}
