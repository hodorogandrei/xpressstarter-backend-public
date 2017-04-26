package com.xpressstarter.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.util.CampaignCategory;
import com.xpressstarter.util.Role;

@Component
public class StatisticsInMemoryStorage {

	private List<Campaign> campaigns;
	private List<User> users;
	private List<Like> likes;
	private List<Donation> donations;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CampaignRepository cRep;
	@Autowired
	private UserRepository uRep;
	@Autowired
	private LikeRepository lRep;
	@Autowired
	private DonationRepository dRep;
	
	
	//@Scheduled(fixedDelay=300000)
	private void syncData(){
		this.campaigns=new ArrayList<>();
		for(Campaign campaign:this.cRep.findAll()){
			this.campaigns.add(campaign);
		}
		this.users=this.uRep.findAll();
		this.likes=this.lRep.findAll();
		this.donations=this.dRep.findAll();
		logger.info("Statistics have been refreshed!");
		
	}
	//@PostConstruct
	private void initialStart(){
		logger.info("Warming up statistics cache");
		syncData();
	}
	
	public List<Campaign> getCampaigns(){
		return this.campaigns;
	}
	public List<Campaign> getCampaignsByCategory(CampaignCategory category){
		return this.campaigns.parallelStream()
				.filter(x -> x.getCategory().equals(category)).collect(Collectors.toList());
	}
	public List<User> getUsersByRole(Role role){
		return this.users.parallelStream().filter(x -> x.getRole().equals(role)).collect(Collectors.toList());
	}
}


