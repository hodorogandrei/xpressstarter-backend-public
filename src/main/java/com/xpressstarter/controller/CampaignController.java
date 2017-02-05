package com.xpressstarter.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.role.Role;

@RestController
@RequestMapping("/api/v1/campaign/")
public class CampaignController {
	
	@Autowired
	private CampaignRepository cRep;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Campaign getCampaign(@PathVariable("id") String id){
		return cRep.findOne(id);
		}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Campaign> getCampaign(){
		return cRep.findAll();
		}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void addCampaing(@RequestBody Campaign campaign){
		cRep.save(campaign);
	}
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void editCampaing(@RequestBody Campaign campaign){
		cRep.save(campaign);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void DeleteCampaing(@RequestParam("id") String id){
		cRep.delete(id);;
	}
	@RequestMapping("/test")
	public List<Campaign> doATest(){
		User u = new User();
		u.setEmail("test@test.com");
		u.setFirstname("Test F");
		u.setLastname("Test L");
		u.setMemberSince(LocalDateTime.now());
		u.setPasswordHash("a9s08d09a8sd9");
		u.setRole(Role.BENEFICIARY);
		u.setWantsToReceiveEmail(false);
		for (int i=0;i<10;i++){
			Campaign c = new Campaign();
			c.setBeneficiary(u);
			c.setCurrent(i*100+1000);
			c.setDescription("This is a "+i+" test campaign to test the api");
			c.setExpiresOn(LocalDateTime.now());
			c.setStartedOn(LocalDateTime.now());
			c.setName("Test Campaign "+i);
			c.setTarget(i*100+2000);
			cRep.save(c);
		}
		return cRep.findAll();
	}
	

	
}
