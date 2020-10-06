package com.dna.backend.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("/")
@RestController
public class TemplateController {
	
	@GetMapping("/login")
	public String getLoginView() {
		return "login";
	}
	
	@RequestMapping("user")
	@ResponseBody
	public Principal getUser(Principal principal) {
		return principal;
	}
	
	
	@GetMapping("/login-success")
	public String getCourses() {
		return "index";
	}

}
