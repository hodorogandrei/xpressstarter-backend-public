package com.xpressstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;

@Service
public class DonationService {

	@Autowired
	private DonationRepository dRep;
	
	@Autowired
	private CampaignRepository cRep;
	
	public void addDonation(Donation donation){
		dRep.save(donation);
		recalculateCurrentPledge(donation);
	}
	
	public List<Donation> getDonationsByUser(String userId){
		return dRep.findByUserId(userId);
	}
	
	public List<Donation> getDonationsByCampaign(String campaignId){
		return dRep.findByCampaignId(campaignId);
	}
	
	public List<Donation> getAllDonations(){
		return dRep.findAll();
	}
	
	public Donation getDonationById(String id){
		return dRep.findOne(id);
	}
	
	public void revokeDonation(String donationId){
		Donation donation = dRep.findOne(donationId);
		donation.setStatusRevoked();
		dRep.save(donation);
	}
	private void recalculateCurrentPledge(Donation donation){
		List<Donation> donations=getDonationsByCampaign(donation.getId());
		Campaign campaign = cRep.findOne(donation.getCampaign());
		campaign.setCurrent(donations.stream().mapToDouble(x -> x.getAmount()).sum());
		cRep.save(campaign);
	}
	
}
