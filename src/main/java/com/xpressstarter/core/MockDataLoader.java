package com.xpressstarter.core;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.util.CampaignCategory;
import com.xpressstarter.util.Role;

@Component
public class MockDataLoader implements ApplicationRunner{

	@Autowired
	private CampaignRepository cRep;
	
	@Autowired
	private UserRepository uRep;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		uRep.deleteAll();
		cRep.deleteAll();
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
		
		IntStream.range(0,90).forEach((x) -> {
			User u = users.get(x % users.size());
			String template = templates[x % templates.length];
			String buzzword = causes[x % causes.length];
			String title = String.format(template, buzzword);
			Campaign c = new Campaign(title, "This is a test description", u.getId(), (double)(x*100+1),(double) (x*12-x), LocalDateTime.now(), LocalDateTime.now(), CampaignCategory.ARTS, true);
			cRep.save(c);
		});
	
	}
}


