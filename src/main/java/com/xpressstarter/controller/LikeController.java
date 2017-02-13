package com.xpressstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Like;
import com.xpressstarter.exceptions.AlreadyLikedCampaignException;
import com.xpressstarter.service.LikeService;

@RestController
@RequestMapping("/api/v1/like/")
public class LikeController {
	@Autowired
	private LikeService lServ;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Like getLike(@PathVariable("id") String id){
		return lServ.getLike(id);
		}
	
	@RequestMapping(value="/user/{like}", method=RequestMethod.GET)
	public List<Like> getLikesByUser(@PathVariable("like") String userId){
		return lServ.getLikesByUser(userId);
		}
	
	@RequestMapping(value="/campaign/{campaign}", method=RequestMethod.GET)
	public List<Like> getLikesByCampaign(@PathVariable("campaign") String campaignId){
		return lServ.getLikesByCampaign(campaignId);
		}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Like> getLikes(){
		return lServ.getAllLikes();
		}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> addLike(@RequestBody Like like){
		try {
			lServ.addLike(like);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (AlreadyLikedCampaignException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already liked campaign!");
		}
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void DeleteLike(@RequestParam("id") String likeId){
		lServ.unlikeCampaign(likeId);
	}
}
