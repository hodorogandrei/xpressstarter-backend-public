package com.xpressstarter.statistics;

import com.xpressstarter.util.CampaignCategory;

public class StatisticCategoryEntry extends Statistical{

	private CampaignCategory category;

	
	public StatisticCategoryEntry(CampaignCategory category,Number number) {
		this.category=category;
		this.number=number;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return category.name();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
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
		StatisticCategoryEntry other = (StatisticCategoryEntry) obj;
		if (category != other.category)
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	

}
