package com.xpressstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpressstarter.campaign.category.CampaignCategory;
import com.xpressstarter.entity.Campaign;
import com.xpressstarter.exceptions.CampaignAlreadyExistsException;
import com.xpressstarter.exceptions.CampaignDoesNotExistException;
import com.xpressstarter.repository.CampaignRepository;

@Service
public class CampaignService {

	@Autowired
	private CampaignRepository cRep;
	
	public void addCampaign(Campaign newCampaign) throws CampaignAlreadyExistsException{
		Campaign campaign=cRep.findByName(newCampaign.getName());
		if (campaign!=null){
			cRep.save(newCampaign);
		} else{
			throw new CampaignAlreadyExistsException();
		}
	}
	
	public void editCampaign(Campaign campaign) throws CampaignDoesNotExistException{
		Campaign c = cRep.findOne(campaign.getId());
		if (c!=null){
			cRep.save(campaign);
		} else {
			throw new CampaignDoesNotExistException();
		}
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
	
	public List<Campaign> getCampaignsByBeneficiary(String beneficiaryId){
		return cRep.findByBeneficiaryId(beneficiaryId);
	}
	
	public List<Campaign> getCampaignsByCategory(CampaignCategory category){
		return cRep.findByCategory(category);
	}
	
	public void deleteCampaign(String id){
		Campaign campaign = cRep.findOne(id);
		campaign.setIsActive(false);
		cRep.save(campaign);
	}
}
