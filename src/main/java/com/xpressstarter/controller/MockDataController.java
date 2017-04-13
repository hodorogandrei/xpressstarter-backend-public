package com.xpressstarter.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.util.ActivityGeneratorRunner;
import com.xpressstarter.util.CampaignCategory;
import com.xpressstarter.util.DonationStatus;
import com.xpressstarter.util.MockCampaign;
import com.xpressstarter.util.MockUser;
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
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping
	public void loadMockData(){
		uRep.deleteAll();
		cRep.deleteAll();
		dRep.deleteAll();
		lRep.deleteAll();
		
		uRep.save(new User("Andrei","Dumitrescu","andrei@test.com","ksdhfisd",false,LocalDateTime.now(),Role.ADMIN));
		uRep.save(new User("Andrei","Hodorog","andrei.hodorog@test.com","ksdhfisd",false,LocalDateTime.now(),Role.ADMIN));
		loadMockDataFromJSON(); 
		Long start=System.currentTimeMillis();
		generateActivity(cRep.findAll());
		Long stop=System.currentTimeMillis();
		logger.debug("Generating mock data took "+(stop-start)+" ms!");
		for (Campaign campaign:cRep.findAll()){
			recalculateLikesAndDonations(campaign);
		}
		for (User user:uRep.findByRole(Role.BENEFACTOR)){
			recalculateUserDonated(user);
		}
		

	}
	
	private void loadMockDataFromJSON(){
		loadMockUsersFromJSON();
		loadMockCampaignsFromJSON();
	}
	
	private void loadMockUsersFromJSON(){
		File mockUsersFile = new File("mock_users.json");
		ObjectMapper jsonMapper = new ObjectMapper();
		Random random = new Random();
		try {
			List<MockUser> mockUsers= jsonMapper.readValue( new FileReader(mockUsersFile),new TypeReference<List<MockUser>>(){});
			System.out.println(mockUsers.size());
			for (MockUser mockUser:mockUsers){
				User user = new User();
				user.setEmail(mockUser.getEmail());
				user.setFirstname(mockUser.getFirst_name());
				user.setLastname(mockUser.getLast_name());
				user.setPasswordHash(mockUser.getPassword());
				user.setWantsToReceiveEmail(random.nextBoolean());
				user.setMemberSince(LocalDateTime.of(2017, 1+random.nextInt(3), 1+random.nextInt(26), random.nextInt(24), random.nextInt(60)));
				int role = random.nextInt(2);
				if (role==1){
					user.setRole(Role.BENEFACTOR);
				} else {
					user.setRole(Role.BENEFICIARY);
				}
				uRep.save(user);
				
			}
			System.out.println(mockUsers.size()+" users have been added!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadMockCampaignsFromJSON(){
		ObjectMapper jsonMapper = new ObjectMapper();
		Random random = new Random();
		final List<User> users=uRep.findAll().stream().filter(x -> x.getRole().equals(Role.BENEFICIARY)).collect(Collectors.toList());
		File mockCampaignsFile = new File("mock_campaigns.json");
		List<MockCampaign> mockCampaigns=null;
		List<CampaignCategory> categories = Arrays.asList(CampaignCategory.values());
		try {
			mockCampaigns = jsonMapper.readValue( new FileReader(mockCampaignsFile),new TypeReference<List<MockCampaign>>(){});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (MockCampaign mockCampaign:mockCampaigns){
			User u = users.get(random.nextInt(users.size()));
			String title = mockCampaign.getName();
			Campaign c = new Campaign(
					title, 
					mockCampaign.getDescription(), 
					u, 
					10000.00+(random.nextDouble()*90000),
					0.0,
					LocalDateTime.now().minusDays(random.nextInt(100)),
					LocalDateTime.now().plusDays(random.nextInt(100)),
					categories.get(random.nextInt(categories.size())),
					true,
					users.get(0));
			c.setLikeCount(0);
			c=cRep.save(c);
			Donation d = new Donation(u,100.00,LocalDateTime.now(),c,DonationStatus.OK);
			d=dRep.save(d);
		}
		System.out.println(mockCampaigns.size()+" campaigns have been added");
	}
	
	private void generateActivity(Iterable<Campaign> campaigns){
		ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (User user:uRep.findByRole(Role.BENEFACTOR)){
			es.execute(new ActivityGeneratorRunner(user,cRep,lRep,dRep,campaigns));
		}
		es.shutdown();
		try {
			es.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void recalculateLikesAndDonations(Campaign campaign){
		campaign.setLikeCount(lRep.countByCampaignId(campaign.getId()));
		List<Donation> donations = dRep.findByCampaignId(campaign.getId());
		campaign.setCurrent(donations.stream().mapToDouble(x -> x.getAmount()).sum());
		cRep.save(campaign);
	}
	private void recalculateUserDonated(User user){
		List<Donation> donations = dRep.findByUserId(user.getId());
		user.setTotalDonated(donations.parallelStream().mapToDouble(donation -> donation.getAmount()).sum());
		uRep.save(user);
	}
}
