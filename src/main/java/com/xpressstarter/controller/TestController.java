package com.xpressstarter.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.role.Role;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

	@Autowired
	CampaignRepository cRep;
	
	@Autowired
	UserRepository uRep;
	
	@RequestMapping("/test")
	public List<Campaign> doATest(){
		uRep.deleteAll();
		cRep.deleteAll();
		User u = new User();
		u.setEmail("test@test.com");
		u.setFirstname("Test F");
		u.setLastname("Test L");
		u.setMemberSince(LocalDateTime.now());
		u.setPasswordHash("a9s08d09a8sd9");
		u.setRole(Role.BENEFICIARY);
		u.setWantsToReceiveEmail(false);
		u=uRep.save(u);
		for (int i=0;i<10;i++){
			Campaign c = new Campaign();
			c.setBeneficiaryId(u.getId());
			c.setCurrent(i*100+1000);
			c.setDescription("This is a "+i+" test campaign to test the api");
			c.setExpiresOn(LocalDateTime.now());
			c.setStartedOn(LocalDateTime.now());
			c.setName("Test Campaign "+i);
			c.setTarget(i*100+2000);
			
			cRep.save(c);
			
		}
		u.setEmail("newEmail@email.com");
		return cRep.findAll();
	}
	
}
