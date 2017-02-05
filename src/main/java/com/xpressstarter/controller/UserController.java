package com.xpressstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpressstarter.entity.User;
import com.xpressstarter.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
	@Autowired
	private UserRepository uRep;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable("id") String id){
		return uRep.findOne(id);
		}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<User> getUsers(){
		return uRep.findAll();
		}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public void addUser(@RequestBody User user){
		uRep.save(user);
	}
	@RequestMapping(value="/", method=RequestMethod.PUT)
	public void editUser(@RequestBody User user){
		uRep.save(user);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void DeleteUser(@RequestParam("id") String id){
		uRep.delete(id);
	}
}
