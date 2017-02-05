package com.xpressstarter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Like;

public interface LikeRepository extends MongoRepository<Like, String>{

}
