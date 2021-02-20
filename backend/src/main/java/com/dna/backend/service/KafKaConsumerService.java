package com.dna.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.dna.backend.modle.User;

@Service
public class KafKaConsumerService {

	/**
	 * Kafka Consumer Service class to complete the message consuming process
	 * 
	 * @authors Naina and Dinesh version 1.0
	 * @since 2021/02/19
	 *
	 */

	private final Logger logger = LoggerFactory.getLogger(KafKaConsumerService.class);

	/**
	 * 
	 * 1. General topic with any string message
	 * 
	 * 
	 */

	@KafkaListener(topics = "${general.topic.name}", groupId = "${general.topic.group.id}")
	public void consume(String message) {
		logger.info(String.format("Message recieved -> %s", message));
	}

	/**
	 * 
	 * 2. Topic with database in User object (or any other object)
	 * 
	 * 
	 */

	@KafkaListener(topics = "${user.topic.name}", groupId = "${user.topic.group.id}", containerFactory = "userKafkaListenerContainerFactory")
	public void consume(User user) {
		logger.info(String.format("User created -> %s", user));
	}

}