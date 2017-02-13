package com.xpressstarter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpressstarter.entity.Like;
import com.xpressstarter.exceptions.AlreadyLikedCampaignException;
import com.xpressstarter.repository.LikeRepository;

@Service
public class LikeService {

	@Autowired
	private LikeRepository lRep;
	
	public Like getLike(String likeId){
		return lRep.findOne(likeId);
	}
	
	public void addLike(Like like) throws AlreadyLikedCampaignException{
		if (lRep.findOne(like.getId()) == null) {
			lRep.save(like);
		} else {
			throw new AlreadyLikedCampaignException();
		}
	}
	
	public List<Like> getAllLikes(){
		return lRep.findAll();
	}
	
	public List<Like> getLikesByUser(String userId){
		return lRep.findByUserId(userId);
	}
	
	public List<Like> getLikesByCampaign(String campaignId){
		return lRep.findByCampaignId(campaignId);
	}
	public void unlikeCampaign(String likeId){
		lRep.delete(likeId);
	}
}
