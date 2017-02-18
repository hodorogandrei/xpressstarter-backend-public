package com.xpressstarter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.util.CampaignCategory;

public interface CampaignRepository extends PagingAndSortingRepository<Campaign, String> {

	public Page<Campaign> findByBeneficiaryId(String userId,Pageable Page);
	public Page<Campaign> findByCategory(CampaignCategory category, Pageable Page);
	public Page<Campaign> findByNameLikeOrDescriptionLikeAllIgnoreCase(String keyword, Pageable Page);
	public Campaign findByName(String name);
}
