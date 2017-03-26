package com.xpressstarter.util;

import java.time.LocalDateTime;

import org.springframework.data.rest.core.config.Projection;

import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;

@Projection(name="View",types = { Like.class})
public interface InlineLikeView {

	User getUser();
	LocalDateTime getGivenOn();

}

/*
 * @Projection(name = "inlineAddress", types = { Person.class }) 
interface InlineAddress {

  String getFirstName();

  String getLastName();

  Address getAddress(); 
}
 */
