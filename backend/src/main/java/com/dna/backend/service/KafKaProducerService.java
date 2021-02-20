package com.dna.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.dna.backend.modle.User;

@Service
public class KafKaProducerService {

	/**
	 * Kafka Producer Service class to complete the callback process
	 * 
	 * @authors Naina and Dinesh version 1.0
	 * @since 2021/02/19
	 *
	 */

	private static final Logger logger = LoggerFactory.getLogger(KafKaProducerService.class);

	/**
	 * 
	 * 1. General topic with any string message (inject KafkaTemplate <String,
	 * String> here
	 * 
	 * 
	 */

	@Value(value = "${general.topic.name}")
	private String topicName;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * 
	 * 2. Topic with database in User object (or any other object) (inject
	 * KafkaTemplate <String, User> here
	 * 
	 * 
	 */

	@Value(value = "${user.topic.name}")
	private String userTopicName;

	@Autowired
	private KafkaTemplate<String, User> userKafkaTemplate;

	/**
	 * ListenableFuture is used to accept completion callbacks after sending message (to print in console)
	 * 
	 * 
	 */

	public void sendMessage(String message) {
		ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topicName, message);

		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			@Override
			public void onSuccess(SendResult<String, String> result) {
				logger.info("Sent message: " + message + " with offset: " + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("Unable to send message : " + message, ex);
			}
		});
	}

	/**
	 * 
	 * Here ListenableFuture is used to accept completion callbacks from database of
	 * User object
	 * 
	 * 
	 */

	public void saveCreateUserLog(User user) {
		ListenableFuture<SendResult<String, User>> future = this.userKafkaTemplate.send(userTopicName, user);

		future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
			@Override
			public void onSuccess(SendResult<String, User> result) {
				logger.info("User created: " + user + " with offset: " + result.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(Throwable ex) {
				logger.error("User created : " + user, ex);
			}
		});
	}
}