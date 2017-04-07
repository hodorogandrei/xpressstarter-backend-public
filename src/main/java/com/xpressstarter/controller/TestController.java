package com.xpressstarter.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpressstarter.entity.Campaign;

@RestController
public class TestController {

	 @Autowired
	 ApplicationContext applicationContext;
	 
	 @Autowired
	 EntityLinks links;
	 
	 @Autowired
	 Facebook facebook;
	 
	 @Autowired
	 ConnectionRepository repository;

	
	@GetMapping("/getBeans")
	public String[] getBeans(){
		return applicationContext.getBeanDefinitionNames();
	}
	
	@GetMapping("/getLinks")
	public Link getLink(){
		return links.linkToSingleResource(Campaign.class,"58d98cedf4a35035dc0892d5");
	}
	
	@GetMapping("/getFBPrincipal")
	public byte[] getPrincipal(){
		String [] fields = { "id", "email",  "first_name", "last_name" };
		User userProfile = facebook.fetchObject("me", User.class, fields);
		
		ObjectMapper mapper = new ObjectMapper();
//		try {
//			return mapper.writeValueAsString(userProfile);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return facebook.userOperations().getUserProfileImage();
	}
}
