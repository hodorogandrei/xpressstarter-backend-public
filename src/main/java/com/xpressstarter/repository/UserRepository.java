package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.xpressstarter.entity.User;
import com.xpressstarter.util.Role;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByEmail(@Param("email")String email);
	public List<User> findByRole(Role role);
}
