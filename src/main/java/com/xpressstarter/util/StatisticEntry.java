package com.xpressstarter.util;

import com.xpressstarter.entity.Campaign;

public class StatisticEntry implements Comparable<StatisticEntry>{

	private Campaign campaign;
	private Number number;
	
	
	
	public StatisticEntry(){
		
	}
	
	public StatisticEntry(Campaign campaign, Number number)  {
		super();
		this.campaign = campaign;
		this.number = number;
	}
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	public Number getNumber() {
		return number;
	}
	public void setNumber(Number number) {
		this.number = number;
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
		StatisticEntry other = (StatisticEntry) obj;
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
	public int compareTo(StatisticEntry other) {
		double thisValue=this.number.doubleValue();
		double otherValue=other.number.doubleValue();
		
		if (thisValue > otherValue) return 1;
		if (thisValue < otherValue) return -1;
		if (thisValue == otherValue) return 0;
		return -1;
	}
	
	
}
