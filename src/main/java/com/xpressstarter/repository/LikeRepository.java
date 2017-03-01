package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.xpressstarter.entity.Like;

public interface LikeRepository extends MongoRepository<Like, String>{

	public List<Like> findByCampaignId(@Param("campaignid")String campaignId);
	public List<Like> findByUserId(@Param("userid")String userId);
	public Like findByUserIdAndCampaignId(@Param("userid")String userId,@Param("campaignid")String campaignId);
	public int countByCampaignId(@Param("campaign")String campaignId);
}
