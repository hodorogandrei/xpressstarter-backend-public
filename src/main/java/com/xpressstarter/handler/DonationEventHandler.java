package com.xpressstarter.handler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;
import com.xpressstarter.repository.UserRepository;

@Component("DonationEventHandler")
@RepositoryEventHandler(Donation.class)
public class DonationEventHandler {

	@Autowired
	DonationRepository dRep;
	
	@Autowired
	CampaignRepository cRep;
	
	@Autowired
	UserRepository uRep;
	@HandleBeforeCreate
	public void validateAndCreate(Donation donation){
		donation.setDonatedOn(LocalDateTime.now());
	}
//	
//	@HandleBeforeSave
//	public void validateAndUpdate(Donation donation){
//		validate(donation);
//	}
	
	@HandleAfterCreate
	@HandleAfterSave
	public void recalculatePledges(Donation donation){
		Campaign campaign = donation.getCampaign();
		List<Donation> campaignDonations = dRep.findByCampaignId(campaign.getId());
		campaign.setCurrent(campaignDonations.stream().mapToDouble(x -> x.getAmount()).sum());
		List<Donation> userDonations = dRep.findByUserId(donation.getUser().getId());
		User user=donation.getUser();
		user.setTotalDonated(userDonations.stream().mapToDouble(x -> x.getAmount()).sum());
		uRep.save(user);
		cRep.save(campaign);
		
	}
}
