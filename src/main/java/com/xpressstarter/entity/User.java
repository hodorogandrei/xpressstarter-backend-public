package com.xpressstarter.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.xpressstarter.util.Role;


public class User {

	@Id
	private String id;
	@Indexed
	private String firstname;
	@Indexed
	private String lastname;
	@Indexed
	private String email;
	private String passwordHash;
	private Boolean wantsToReceiveEmail;
	private LocalDateTime memberSince;
	private Role role;
	
	public User(){
		
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
