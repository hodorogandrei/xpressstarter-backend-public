package com.xpressstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.DonationRepository;

@RestController
@RequestMapping("/api/v1/donation/")
public class DonationController {
	@Autowired
	private DonationRepository dRep;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Donation getDonation(@PathVariable("id") String id){
		return dRep.findOne(id);
		}
	
	@RequestMapping(value="/user/{benefactor}", method=RequestMethod.GET)
	public List<Donation> getDonationByUser(@PathVariable("benefactor") User benefactor){
		return dRep.findByBenefactor(benefactor);
		}
	
	@RequestMapping(value="/campaign/{campaign}", method=RequestMethod.GET)
	public List<Donation> getDonationByCampaign(@PathVariable("campaign") Campaign campaign){
		return dRep.findByCampaign(campaign);
		}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Donation> getDonations(){
		return dRep.findAll();
		}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void addDonation(@RequestBody Donation donation){
		dRep.save(donation);
	}
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void editDonation(@RequestBody Donation donation){
		dRep.save(donation);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void DeleteDonation(@RequestParam("id") String id){
		dRep.delete(id);;
	}
}
