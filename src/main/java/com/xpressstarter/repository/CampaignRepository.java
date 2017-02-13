package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.util.CampaignCategory;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

	public List<Campaign> findByBeneficiaryId(String userId);
	public List<Campaign> findByCategory(CampaignCategory category);
	public List<Campaign> findByNameLikeOrDescriptionLikeAllIgnoreCase(String keyword);
	public Campaign findByName(String name);
}
