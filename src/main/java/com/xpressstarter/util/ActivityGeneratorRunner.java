package com.xpressstarter.util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;


public class ActivityGeneratorRunner implements Runnable{
	private static Random random;
	private static final int MAX_LIKES_TO_GIVE=10;
	private static final int MAX_DONATIONS_TO_GIVE=5;
	private static final AtomicInteger count=new AtomicInteger(1);
	private User user;
	private int number;
	
	private CampaignRepository cRep;
	
	private LikeRepository lRep;
	
	private DonationRepository dRep;
	
	public ActivityGeneratorRunner(User user, CampaignRepository cRep, LikeRepository lRep, DonationRepository dRep) {
		this.user=user;
		ActivityGeneratorRunner.random = new Random();
		this.cRep=cRep;
		this.lRep=lRep;
		this.dRep=dRep;
		this.number=count.getAndIncrement();
	}
	
	@Override
	public void run() {
		System.out.println("Thread "+ number+ " has been started!");
		LocalDateTime currentDate = user.getMemberSince();
		while(currentDate.isBefore(LocalDateTime.now())){
			//System.out.println("Thread-"+number+": started on day "+currentDate);
			generateActivity(currentDate);
			currentDate=currentDate.plusDays(1);
		}
		System.out.println("Thread "+number+" has finished!");
	}
	
	private List<Campaign> getCampaignsActiveByDate(LocalDateTime currentDate){
		List<Campaign> currentCampaigns = new ArrayList<>();
		for (Campaign campaign:cRep.findAll()){
			if(campaign.getStartedOn().isBefore(currentDate) && campaign.getExpiresOn().isAfter(currentDate)){
				currentCampaigns.add(campaign);
			}
		}
		return currentCampaigns;
	}
	
	private void generateActivity(LocalDateTime currentDate){
		List<Campaign> campaigns = getCampaignsActiveByDate(currentDate);
		// If campaing list is empty skip the whole process
		if (campaigns.size()==0) return;
		int likesToGive=ActivityGeneratorRunner.random.nextInt(MAX_LIKES_TO_GIVE);
		int donationsToGive=ActivityGeneratorRunner.random.nextInt(MAX_DONATIONS_TO_GIVE);
		for (int i=1;i<=likesToGive;i++){
			Campaign campaign = campaigns.get(ActivityGeneratorRunner.random.nextInt(campaigns.size()>=0?campaigns.size():0));
			Like like = new Like();
			like.setCampaign(campaign);
			like.setUser(this.user);
			like.setGivenOn(currentDate);
			if (lRep.findByUserIdAndCampaignId(this.user.getId(), campaign.getId())==null){
				lRep.save(like);
			}
		}
		for (int i =1;i<=donationsToGive;i++){
			Campaign campaign = campaigns.get(ActivityGeneratorRunner.random.nextInt(campaigns.size()>=0?campaigns.size():0));
			Donation donation=new Donation();
			donation.setAmount(ActivityGeneratorRunner.random.nextDouble()*100);
			donation.setCampaign(campaign);
			donation.setStatusOK();
			donation.setUser(this.user);
			donation.setDonatedOn(currentDate);
			dRep.save(donation);
		}
	}

}
