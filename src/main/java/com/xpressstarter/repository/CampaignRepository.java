package com.xpressstarter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.util.CampaignCategory;

public interface CampaignRepository extends PagingAndSortingRepository<Campaign, String> {

	public Page<Campaign> findByBeneficiaryId(@Param("userId")String userId,Pageable Page);
	public Page<Campaign> findByCategory(@Param("category")CampaignCategory category, Pageable Page);
	public Page<Campaign> findByNameContainingOrDescriptionContainingAllIgnoreCase(@Param("keyword") String name,@Param("keyword") String description , Pageable Page);
	
	public Campaign findByName(String name);
}
