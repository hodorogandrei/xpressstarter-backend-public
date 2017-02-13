package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.campaign.category.CampaignCategory;
import com.xpressstarter.entity.Campaign;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

	public List<Campaign> findByBeneficiaryId(String userId);
	public List<Campaign> findByCategory(CampaignCategory category);
	public List<Campaign> findByNameLikeOrDescriptionLikeAllIgnoreCase(String keyword);
	public Campaign findByName(String name);
}
