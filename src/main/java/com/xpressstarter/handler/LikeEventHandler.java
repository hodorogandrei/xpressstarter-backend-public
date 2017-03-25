package com.xpressstarter.handler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;
import com.xpressstarter.exceptions.AlreadyLikedCampaignException;
import com.xpressstarter.exceptions.CampaignDoesNotExistException;
import com.xpressstarter.exceptions.UserDoesNotExistException;
import com.xpressstarter.repository.CampaignRepository;
import com.xpressstarter.repository.LikeRepository;

@Component("LikeEventHandler")
@RepositoryEventHandler(Like.class)
public class LikeEventHandler {

	@Autowired
	LikeRepository lRep;
	
	@Autowired
	CampaignRepository cRep;
	
	@HandleBeforeCreate
	public void validateAndSet(Like like) {
		like.setGivenOn(LocalDateTime.now());
//		checkCampaign(like.getCampaign());
//		checkUser(like.getUser());
		checkIfAlreadyGiven(like);
		
	}
	@HandleAfterCreate
	public void setLikeCount(Like like){
		Campaign campaign = like.getCampaign();
		campaign.setLikeCount(lRep.countByCampaignId(campaign.getId()));
		cRep.save(campaign);
	}
	private void checkCampaign(Campaign campaign){
		if (campaign==null) throw new CampaignDoesNotExistException();
	}
	private void checkUser(User user){
		if (user==null) throw new UserDoesNotExistException();
	}
	private void checkIfAlreadyGiven(Like like){
		Like check = lRep.findByUserIdAndCampaignId(like.getUser().getId(), like.getCampaign().getId());
		if(check != null){
			throw new AlreadyLikedCampaignException();
		}
	}
}
