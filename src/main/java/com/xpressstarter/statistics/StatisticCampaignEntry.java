package com.xpressstarter.statistics;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;

import com.xpressstarter.entity.Campaign;

public class StatisticCampaignEntry extends Statistical{

	private Campaign campaign;
	private EntityLinks links;
	
	public StatisticCampaignEntry(){
		
	}
	
	public StatisticCampaignEntry(Campaign campaign, Number number,EntityLinks links)  {
		super();
		this.campaign = campaign;
		this.number = number;
		this.links=links;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campaign == null) ? 0 : campaign.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticCampaignEntry other = (StatisticCampaignEntry) obj;
		if (campaign == null) {
			if (other.campaign != null)
				return false;
		} else if (!campaign.equals(other.campaign))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	@Override
	public String getName() {
		return this.campaign.getName();
	}
	
	public Link getLink(){
		return links.linkToSingleResource(Campaign.class, this.campaign.getId());
	}
	
	
	
}
