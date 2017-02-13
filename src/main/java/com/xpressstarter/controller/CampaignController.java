package com.xpressstarter.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.campaign.category.CampaignCategory;
import com.xpressstarter.entity.Campaign;
import com.xpressstarter.exceptions.CampaignAlreadyExistsException;
import com.xpressstarter.service.CampaignService;

@RestController
@RequestMapping("/api/v1/campaign/")
public class CampaignController {
	
	@Autowired
	private CampaignService cServ;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Campaign getCampaign(@PathVariable("id") String id){
		return cServ.getCampaign(id);
		}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Campaign> getCampaign(){
		return cServ.getAllCampaigns();
		}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> addCampaing(@RequestBody Campaign campaign){
		try{
			cServ.addCampaign(campaign);
			return ResponseEntity.status(HttpStatus.OK).body("Campaign Added!");
		} catch (CampaignAlreadyExistsException ex){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campaign exists already!");
		}
	}
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void editCampaing(@RequestBody Campaign campaign){
		cServ.editCampaign(campaign);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void DeleteCampaing(@RequestParam("id") String id){
		cServ.deleteCampaign(id);
	}
	
	@RequestMapping(value="/search/{keyword}", method=RequestMethod.GET)
	public List<Campaign> getCampaignsLikeKeyword(
			@PathParam("keyword") String keyword){
		return cServ.getCampaignsLike(keyword);
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public CampaignCategory[] getCampaignsCategories(){
		return CampaignCategory.values() ;
	}
	
	@RequestMapping(value="/category/{category}", method=RequestMethod.GET)
	public List<Campaign> getCampaignsByCategory(@PathParam("category") CampaignCategory category){
		return cServ.getCampaignsByCategory(category);
	}
	
	@RequestMapping(value="/user/{id}", method=RequestMethod.GET)
	public List<Campaign> getCampaignsByBeneficiary(@PathParam("id") String userId){
		return cServ.getCampaignsByBeneficiary(userId);
	}
	
	
	
	
}
