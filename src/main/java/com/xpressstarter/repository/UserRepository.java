package com.xpressstarter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.xpressstarter.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

	public User findByEmail(@Param("email")String email);
}
