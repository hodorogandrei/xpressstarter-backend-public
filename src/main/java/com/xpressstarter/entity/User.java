package com.xpressstarter.entity;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xpressstarter.util.Role;


public class User {

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
	
	
	
}
