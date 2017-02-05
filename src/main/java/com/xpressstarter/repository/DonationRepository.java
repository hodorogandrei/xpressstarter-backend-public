package com.xpressstarter.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.User;

public interface DonationRepository extends MongoRepository<Donation,String> {
	public List<Donation> findByCampaign(Campaign campaign);
	public List<Donation> findByBenefactor(User benefactor);
}
