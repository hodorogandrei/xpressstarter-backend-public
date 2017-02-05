package com.xpressstarter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.User;

public interface UserRepository extends MongoRepository<User, String>{

}
