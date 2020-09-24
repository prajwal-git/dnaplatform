package com.dna.backend.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.dna.backend.modle.User;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;
	
	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	User user;

	@Test
	public void testGetAllUsers() {
		ArrayList<User> al = new ArrayList<User>();
		user.setUserName("Prajwal");
		al.add(user);
		when(userController.getAllUsers()).thenReturn(al);
		assertEquals(1, userController.getAllUsers().size());
	}
	
	@Test
	public void testRest() {
		User user1 = new User();
		user1.setId(1);
		user1.setUserName("Prajwal");
		when(restTemplate.getForEntity("https://localhost:8089/user/", User.class)).thenReturn(
				new ResponseEntity<User>(user1,HttpStatus.OK));
		assertEquals(user1, userController.getUser(1));
		
	}
	
	
	
}
