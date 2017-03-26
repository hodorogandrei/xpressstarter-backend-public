package com.xpressstarter.service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.LikeRepository;
import com.xpressstarter.util.CampaignSorter;
import com.xpressstarter.util.StatisticEntry;

@Service
public class StatisticsService {

	@Autowired
	private CampaignRepository cRep;
	
//	@Autowired
//	private UserRepository uRep;
	
	@Autowired
	private DonationRepository dRep;
	
	@Autowired
	private LikeRepository lRep;
	
	
	public Object[][] getTopCampaigns(int number, CampaignSorter type) throws IOException{
		List<StatisticEntry> campaignData;
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
		Collections.sort(campaignData, new Comparator<StatisticEntry>(){
			public int compare(StatisticEntry entry1, StatisticEntry entry2){
			    return entry2.compareTo(entry1);
			}
		});
		if (number > campaignData.size() || number <=0){
			throw new IOException("Invalid top number");
		}
		List<StatisticEntry> topEntries = campaignData.subList(0, number);
		Object[][] results = new Object[number][2];
		for (int index=0;index<topEntries.size();index++){
			StatisticEntry entry = topEntries.get(index);
			results[index][0]=entry.getCampaign().getName();
			results[index][1]=entry.getNumber().doubleValue();
		}
		return results;
		
	}
	
	public Object[][] getNearlyFundedCampaigns(int number) throws IOException{
		List<StatisticEntry> campaignData=getNearlyFoundedCampaigns();
		Collections.sort(campaignData, new Comparator<StatisticEntry>(){
			public int compare(StatisticEntry entry1, StatisticEntry entry2){
			    return entry2.compareTo(entry1);
			}
		});
		if (number > campaignData.size() || number <=0){
			throw new IOException("Invalid top number");
		}
		List<StatisticEntry> topEntries = campaignData.subList(0, number);
		Object[][] results = new Object[number][2];
		for (int index=0;index<topEntries.size();index++){
			StatisticEntry entry = topEntries.get(index);
			results[index][0]=entry.getCampaign().getName();
			results[index][1]=entry.getNumber().doubleValue();
		}
		return results;
	}
	
	private List<StatisticEntry> getCampaignsWithDonationSum(){
		List<StatisticEntry> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			entries.add(new StatisticEntry(campaign, getAllDonationsValueByCampaign(campaign)));
		}
		return entries;
	}
	private List<StatisticEntry> getCampaignsWithDonationCount(){
		List<StatisticEntry> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			entries.add(new StatisticEntry(campaign, getDonationCountByCampaign(campaign)));
		}
		return entries;
	}
	private List<StatisticEntry> getCampaignsActivity(){
		List<StatisticEntry> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			entries.add(new StatisticEntry(campaign, getDonationCountByCampaign(campaign)+getLikesCountByCampaign(campaign)));
		}
		return entries;
	}
	
	private List<StatisticEntry> getNearlyFoundedCampaigns(){
		List<StatisticEntry> entries = new LinkedList<>();
		for (Campaign campaign:cRep.findAll()){
			if (campaign.calculatePercentage()<100){
				entries.add(new StatisticEntry(campaign,campaign.calculatePercentage()));
			}
		}
		return entries;
	}
	
	private Double getAllDonationsValueByCampaign(Campaign campaign){
		List<Donation> donations = dRep.findByCampaignId(campaign.getId());
		Double sum = donations.stream().mapToDouble(c -> c.getAmount()).sum();
		return sum;
	}
	private Integer getDonationCountByCampaign(Campaign campaign){
		return dRep.findByCampaignId(campaign.getId()).size();
		
	}
	private Integer getLikesCountByCampaign(Campaign campaign){
		return lRep.findByCampaignId(campaign.getId()).size();
	}
}
