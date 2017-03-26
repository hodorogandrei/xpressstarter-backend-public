package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.xpressstarter.entity.Donation;
import com.xpressstarter.util.InlineName;

@RepositoryRestResource(excerptProjection = InlineName.class)	
public interface DonationRepository extends MongoRepository<Donation,String> {
	public List<Donation> findByCampaignId(@Param("campaignid") String campaignId);
	public List<Donation> findByUserId(@Param("userid") String userId);
	public Donation findByUserIdAndCampaignId(@Param("userid") String userId, @Param("campaignid") String campaignId);
	public int countByCampaignId(@Param("campaignid")String campaignId);
}
