package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.campaign.category.CampaignCategory;
import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.User;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

	public List<Campaign> findByBeneficiary(User user);
	public List<Campaign> findByCategory(CampaignCategory category);
	public List<Campaign> findByNameLikeOrDescriptionLikeAllIgnoreCase(String keyword);
}
