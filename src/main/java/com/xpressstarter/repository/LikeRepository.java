package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;

public interface LikeRepository extends MongoRepository<Like, String>{

	public List<Like> findByCampaign(Campaign campaign);
	public List<Like> findByUser(User user);
}
