package com.xpressstarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.Campaign;

@RestController
public class TestController {

	 @Autowired
	 ApplicationContext applicationContext;
	 
	 @Autowired
	 EntityLinks links;

	
	@GetMapping("/getBeans")
	public String[] getBeans(){
		return applicationContext.getBeanDefinitionNames();
	}
	
	@GetMapping("/getLinks")
	public Link getLink(){
		return links.linkToSingleResource(Campaign.class,"58d98cedf4a35035dc0892d5");
	}
}
