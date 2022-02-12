package com.dna.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dna.backend.modle.User;

@RequestMapping("/fname")
@Controller
public class UsersResource {
	
	@Autowired
	UsersCache usersCache;
	
	@GetMapping("/{firstName}")
	public User getUser(@PathVariable final String firstName) {
		return usersCache.getUser(firstName); 
	}

}
