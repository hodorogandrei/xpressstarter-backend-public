package com.xpressstarter.statistics;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;

import com.xpressstarter.entity.User;

public class StatisticalUserEntry extends Statistical{

	private User user;
	private EntityLinks links;
	
	
	public StatisticalUserEntry(User user,Number number,EntityLinks links) {
		super();
		this.user = user;
		this.number = number;
		this.links=links;
	}
	@Override
	public String getName() {
		return user.getFirstname()+" "+user.getLastname();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatisticalUserEntry other = (StatisticalUserEntry) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}
	
	public Link getLink(){
		return links.linkToSingleResource(User.class, this.user.getId());
	}
	

}
