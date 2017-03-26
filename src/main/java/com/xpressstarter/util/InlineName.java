package com.xpressstarter.util;

import org.springframework.data.rest.core.config.Projection;

import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;

@Projection(name="Name",types = { Like.class, Donation.class})
public interface InlineName {

	User getUser();

}

/*
 * @Projection(name = "inlineAddress", types = { Person.class }) 
interface InlineAddress {

  String getFirstName();

  String getLastName();

  Address getAddress(); 
}
 */
