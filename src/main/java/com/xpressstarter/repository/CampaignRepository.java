package com.xpressstarter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xpressstarter.entity.Campaign;

public interface CampaignRepository extends MongoRepository<Campaign, String> {

}
