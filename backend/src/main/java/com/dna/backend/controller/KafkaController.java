package com.dna.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dna.backend.modle.User;
import com.dna.backend.repository.UserRepository;
import com.dna.backend.service.KafKaProducerService;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
	
	/**
	 * Kafka Controller class for requestmapping of kafkaproducer and kafkaconsumer services
	 * 
	 * @authors Naina and Dinesh
	 *  version 1.0
	 * @since 2021/02/19
	 *
	 */

	private KafKaProducerService producerService;

	/**
	 * injecting UserRepository for calling methods used to find different parameters of User Object
	 * 
	 */
	@Autowired
	UserRepository userRepository;

	@Autowired
	public void KafkaProducerController(KafKaProducerService producerService) {
		this.producerService = producerService;
	}
	
	/**@GetMapping to get a user in User object by means of id 
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	public User getUserDetailsById(@PathVariable Integer id) {
		return userRepository.findById(id).orElse(null);
	}
	
	/**@PostMapping to publish any string message in console
	 * 
	 * @param message
	 */
	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
		this.producerService.sendMessage(message);
	}
	
	/**@PostMapping to create a new user in User Object
	 * 
	 * 
	 */
	@PostMapping(value = "/createUser")
	public ResponseEntity<?> sendMessageToKafkaTopic(@RequestBody User user) {
		producerService.saveCreateUserLog(user);
		return new ResponseEntity<>("User created", HttpStatus.CREATED);
	}


	/**@PutMapping to update users in User object by means of id
	 * 
	 * 
	 */
	@PutMapping("/{id}")
	public User putUser(@PathVariable Integer id, @RequestBody User user) {
		User usr = userRepository.findById(id).orElse(null);
		if (usr != null) {
			usr.setUserName(usr.getUserName());
			usr.setFirstName(usr.getFirstName());
			usr.setLastName(usr.getLastName());
			usr.setMiddleName(usr.getMiddleName());
			usr.setEmail(usr.getEmail());
			usr.setAddress(usr.getAddress());
			usr.setOfficePhone(usr.getOfficePhone());
			usr.setCellPhone(usr.getCellPhone());
			usr.setGender(usr.getGender());
			return userRepository.save(usr);
		} else {
			return userRepository.save(user);
		}
	}

	
	/**@GetMapping to get information of all users in User object by means of id 
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/allusers")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
