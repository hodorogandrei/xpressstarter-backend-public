package com.xpressstarter.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.EntityLinks;
import org.springframework.stereotype.Service;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;
import com.xpressstarter.repository.UserRepository;
import com.xpressstarter.statistics.StatisticCampaignEntry;
import com.xpressstarter.statistics.StatisticCategoryEntry;
import com.xpressstarter.statistics.Statistical;
import com.xpressstarter.statistics.StatisticalUserEntry;
import com.xpressstarter.util.CampaignCategory;
import com.xpressstarter.util.CampaignSorter;
import com.xpressstarter.util.Role;

@Service
public class StatisticsService {

	@Autowired
	private CampaignRepository cRep;
	
	@Autowired
	private UserRepository uRep;
	
	@Autowired
	private DonationRepository dRep;
	
	@Autowired
	private LikeRepository lRep;
	
	@Autowired
	EntityLinks links;
	
	public Object[][] getTopCampaigns(int number, CampaignSorter type) throws IOException{
		List<Statistical> campaignData;
		switch(type){
		case BY_ACTIVITY:
			campaignData = getCampaignsActivity();
			break;
		case BY_DONATION_COUNT:
			campaignData = getCampaignsWithDonationCount();
			break;
		case BY_DONATION_SUM:
			campaignData = getCampaignsWithDonationSum();
			break;
		default:
			throw new IOException();
		}
		Collections.sort(campaignData, new Comparator<Statistical>(){
			public int compare(Statistical entry1, Statistical entry2){
			    return entry2.compareTo(entry1);
			}
		});
		if (number > campaignData.size() || number <=0){
			throw new IOException("Invalid top number");
		}
		List<Statistical> topEntries = campaignData.subList(0, number);
		return formatResults(topEntries);
		
		
	}
	
	public Object[][] getNearlyFundedCampaigns(int number) throws IOException{
		List<Statistical> campaignData=getNearlyFoundedCampaigns();
		Collections.sort(campaignData, new Comparator<Statistical>(){
			public int compare(Statistical entry1, Statistical entry2){
			    return entry2.compareTo(entry1);
			}
		});
		if (number > campaignData.size() || number <=0){
			throw new IOException("Invalid top number");
		}
		List<Statistical> topEntries = campaignData.subList(0, number);
		return formatResults(topEntries);
	}
	
	public Object[][] getAverageDonationPerCategory(){
		List<Statistical> values= new LinkedList<>();
		List<CampaignCategory> categories = Arrays.asList(CampaignCategory.values());
		for(CampaignCategory category:categories){
			double sum=0;
			int donationCount = 0;
			
			for (Campaign campaign:cRep.findByCategory(category,new PageRequest(0,Integer.MAX_VALUE))){
				sum+=campaign.getCurrent();
				donationCount+=dRep.countByCampaignId(campaign.getId());
			}
			values.add(new StatisticCategoryEntry(category,sum/donationCount));
			
		}
		return formatResults(values);
	}
	public Object[][] getTopUsersByDonationSum(int number){
		List<User> users =uRep.findByRole(Role.BENEFACTOR);
		List<Statistical> values = new LinkedList<>();
//		for (User user:users){
//			double sum=0;
//			sum+=dRep.findByUserId(user.getId()).parallelStream().mapToDouble(x -> x.getAmount()).sum();
//			values.add(new StatisticalUserEntry(user,sum));
//		}
		users.parallelStream().forEach((user)->{
			
			values.add(new StatisticalUserEntry(user,user.getTotalDonated()));
		});
		values.sort((x,y) -> y.compareTo(x));
		return formatResults(values.subList(0, number));
	}
	
	private Object[][] formatResults(List<Statistical> statistics){
		Object[][] results = new Object[statistics.size()][2];
		for (int index=0;index<statistics.size();index++){
			results[index][0]=statistics.get(index).getName();
			results[index][1]=statistics.get(index).getValue();
		}
		return results;
	}
	
	
	
	
	private List<Statistical> getCampaignsWithDonationSum(){
		List<Statistical> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			entries.add(new StatisticCampaignEntry(campaign, campaign.getCurrent()));
		}
		return entries;
	}
	private List<Statistical> getCampaignsWithDonationCount(){
		List<Statistical> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			entries.add(new StatisticCampaignEntry(campaign, getDonationCountByCampaign(campaign)));
		}
		return entries;
	}
	private List<Statistical> getCampaignsActivity(){
		List<Statistical> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			entries.add(new StatisticCampaignEntry(campaign, getDonationCountByCampaign(campaign)+getLikesCountByCampaign(campaign)));
		}
		return entries;
	}
	
	private List<Statistical> getNearlyFoundedCampaigns(){
		List<Statistical> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			if (campaign.calculatePercentage()<100){
				entries.add(new StatisticCampaignEntry(campaign,campaign.calculatePercentage()));
			}
		}
		return entries;
	}
	
	private Integer getDonationCountByCampaign(Campaign campaign){
		return dRep.countByCampaignId(campaign.getId());
		
	}
	private Integer getLikesCountByCampaign(Campaign campaign){
		return lRep.countByCampaignId(campaign.getId());
	}
	
	
}
