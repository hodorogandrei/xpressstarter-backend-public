package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Donation;

public interface DonationRepository extends MongoRepository<Donation,String> {
	public List<Donation> findByCampaignId(String campaignId);
	public List<Donation> findByUserId(String userId);
	public Donation findByUserIdAndCampaignId(String userId, String campaignId);
}
