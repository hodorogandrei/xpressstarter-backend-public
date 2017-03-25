package com.xpressstarter.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.util.CampaignCategory;
import com.xpressstarter.util.DonationStatus;
import com.xpressstarter.util.Role;

@RestController
@RequestMapping("/loadMockData")
public class MockDataController {

	@Autowired
	private CampaignRepository cRep;
	
	@Autowired
	private UserRepository uRep;

	@Autowired
	private DonationRepository dRep;
	
	@Autowired
	private LikeRepository lRep;
	
	@RequestMapping
	public void loadMockData(){
		uRep.deleteAll();
		cRep.deleteAll();
		dRep.deleteAll();
		lRep.deleteAll();
		
		uRep.save(new User("Andrei","Dumitrescu","andrei@test.com","ksdhfisd",false,LocalDateTime.now(),Role.ADMIN));
		uRep.save(new User("Vlad","Petreanu","vpetreanu@test.com","ksdhfisd",false,LocalDateTime.now(),Role.BENEFICIARY));
		uRep.save(new User("Andrei","Hodorog","andrei.hodorog@test.com","ksdhfisd",false,LocalDateTime.now(),Role.ADMIN));
		
		
		final List<User> users=uRep.findAll();
		String[] causes = {
				"Wales",
				"Parks",
				"Buildings",
				"Galeries",
				"Football Fields",
				"Local Watering Holes"
		};
		String[] templates = {
				"Save the %s",
				"Rescue the %s",
				"Let's not forget about %s",
				"Where would we be without %s ? "
				
		};
		Random random = new Random();
		IntStream.range(0,90).forEach((x) -> {
			User u = users.get(x % users.size());
			String template = templates[x % templates.length];
			String buzzword = causes[x % causes.length];
			String title = String.format(template, buzzword);
			
			Campaign c = new Campaign(
					title, 
					"This is a test description", 
					u, 
					(double)(x*100+1),
					(double) 100,
					LocalDateTime.now(),
					LocalDateTime.now().plusDays(random.nextInt(100)),
					CampaignCategory.ARTS,
					true,
					users.get(0));
			c.setLikeCount(0);
			c=cRep.save(c);
			Donation d = new Donation(u,100.00,LocalDateTime.now(),c,DonationStatus.OK);
			d=dRep.save(d);
		});
	

	}
}
