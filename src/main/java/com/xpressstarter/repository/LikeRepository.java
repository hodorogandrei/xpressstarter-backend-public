package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Like;

public interface LikeRepository extends MongoRepository<Like, String>{

	public List<Like> findByCampaignId(String campaignId);
	public List<Like> findByUserId(String userId);
}
