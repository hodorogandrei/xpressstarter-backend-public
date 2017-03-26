package com.xpressstarter.util;

import java.time.LocalDateTime;

import org.springframework.data.rest.core.config.Projection;

import com.xpressstarter.entity.Donation;
import com.xpressstarter.entity.User;

@Projection(name="View",types = { Donation.class})
public interface InlineDonationView {

	User getUser();
	LocalDateTime getDonatedOn();
	Double getAmount();
}
