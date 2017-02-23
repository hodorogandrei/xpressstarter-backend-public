package com.xpressstarter.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.DonationRepository;

@Component
@RepositoryEventHandler(Donation.class)
public class DonationEventHandler {

	@Autowired
	DonationRepository dRep;
	
	@Autowired
	CampaignRepository cRep;
	
	@HandleAfterCreate
	@HandleAfterSave
	public void recalculatePledges(Donation donation){
		Campaign campaign = cRep.findOne(donation.getCampaign());
		List<Donation> donations = dRep.findByCampaignId(campaign.getId());
		campaign.setCurrent(donations.stream().mapToDouble(x -> x.getAmount()).sum());
		cRep.save(campaign);
	}
}
