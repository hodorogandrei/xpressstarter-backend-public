package com.xpressstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Campaign;
import com.xpressstarter.entity.Like;
import com.xpressstarter.entity.User;
import com.xpressstarter.repository.LikeRepository;

@RestController
@RequestMapping("/api/v1/like/")
public class LikeController {
	@Autowired
	private LikeRepository lRep;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public Like getLike(@PathVariable("id") String id){
		return lRep.findOne(id);
		}
	
	@RequestMapping(value="/user/{like}", method=RequestMethod.GET)
	public List<Like> getLikesByUser(@PathVariable("like") User user){
		return lRep.findByUser(user);
		}
	
	@RequestMapping(value="/campaign/{campaign}", method=RequestMethod.GET)
	public List<Like> getLikesByCampaign(@PathVariable("campaign") Campaign campaign){
		return lRep.findByCampaign(campaign);
		}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<Like> getLikes(){
		return lRep.findAll();
		}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void addLike(@RequestBody Like like){
		lRep.save(like);
	}
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void editLike(@RequestBody Like like){
		lRep.save(like);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void DeleteLike(@RequestParam("id") String id){
		lRep.delete(id);;
	}
}
