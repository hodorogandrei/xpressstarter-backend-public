package com.xpressstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpressstarter.campaign.category.CampaignCategory;
import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;

@Service
public class CampaignService {

	@Autowired
	private CampaignRepository cRep;
	
	public void addCampaign(Campaign campaign){
		cRep.save(campaign);
	}
	
	public void editCampaign(Campaign campaign){
		cRep.save(campaign);
	}
	
	public Campaign getCampaign(String id){
		return cRep.findOne(id);
	}
	
	public List<Campaign> getAllCampaigns(){
		return cRep.findAll();
	}
	
	public List<Campaign> getCampaignsLike(String keyword){
		return cRep.findByNameLikeOrDescriptionLikeAllIgnoreCase(keyword.toLowerCase());
	}
	
	public List<Campaign> getCampaignsByBeneficiary(User user){
		return cRep.findByBeneficiary(user);
	}
	
	public List<Campaign> getCampaignsByCategory(CampaignCategory category){
		return cRep.findByCategory(category);
	}
	
	public void deleteCampaign(String id){
		cRep.delete(id);
	}
}
