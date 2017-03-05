package com.xpressstarter.handler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.xpressstarter.entity.User;
import com.xpressstarter.exceptions.UserEmailAlreadyExists;
import com.xpressstarter.repository.UserRepository;

@Component("UserEventHandler")
@RepositoryEventHandler(User.class)
public class UserEventHandler {

	@Autowired
	UserRepository uRep;
	
	@HandleBeforeCreate
	public void validateAndSet(User user){
		checkIfEmailIsUsed(user);
		user.setMemberSince(LocalDateTime.now());
	}
	
	private void checkIfEmailIsUsed(User user){
		User check = uRep.findByEmail(user.getEmail());
		if (check!=null){
			throw new UserEmailAlreadyExists();
		}
	}
}
