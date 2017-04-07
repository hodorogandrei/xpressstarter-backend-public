package com.xpressstarter.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.service.StatisticsService;
import com.xpressstarter.statistics.Statistical;
import com.xpressstarter.util.CampaignSorter;

@RestController
@RequestMapping("/api/v1/statistics")
public class StatisticsController {

	@Autowired
	StatisticsService statsService;
	
	@Autowired
	EntityLinks links;
	
	
	@GetMapping("/gettopcampaigns")
	public List<Statistical> getTopCampaigns(@RequestParam("type") int type,@RequestParam("number") int number) throws IOException{
		if (type==1) return statsService.getTopCampaigns(number, CampaignSorter.BY_ACTIVITY);
		if (type==2) return statsService.getTopCampaigns(number, CampaignSorter.BY_DONATION_COUNT);
		if (type==3) return statsService.getTopCampaigns(number, CampaignSorter.BY_DONATION_SUM);
		throw new IOException("Unknown Type!");		
	}
	
	@GetMapping("/getnearlyfunded")
	public List<Statistical> getNearlyFundedCampaigns(@RequestParam("number") int number) throws IOException{
		return statsService.getNearlyFundedCampaigns(number);
	}
	
	@GetMapping("/avgdonation")
	public List<Statistical> getAverageDonationPerCategory(){
		return statsService.getAverageDonationPerCategory();
	}
	
	@GetMapping("/topdonatingusers")
	public List<Statistical> getTopDonatingUsers(@RequestParam("number") int number){
		return statsService.getTopUsersByDonationSum(number);
	}
}
