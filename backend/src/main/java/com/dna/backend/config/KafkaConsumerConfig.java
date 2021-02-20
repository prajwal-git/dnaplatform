package com.dna.backend.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.dna.backend.modle.User;

/**
 * Kafka Consumer Configuration class to create configuration for Kafka
 * Consumers
 * 
 * @authors Naina and Dinesh version 1.0
 * @since 2021/02/19
 *
 */

@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${general.topic.group.id}")
	private String groupId;

	@Value(value = "${user.topic.group.id}")
	private String userGroupId;

	/**
	 * 
	 * ConsumerFactory <K,V) creates consumer instances for consumers to share
	 * common configuration properties
	 * 
	 * @return DefaultKafkaConsumerFactory class for all consumer instances
	 * 
	 * 
	 *
	 */

	/**
	 * 
	 * 1. Consume string data from Kafka - ConsumerFactory <String, String>
	 * 
	 * 
	 */

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(props);
	}

	/**
	 * 
	 * ConcurrentKafkaListenerContainerFactory to build the
	 * "ConcurrentMessageListenerContainer" used for building containers
	 * for @KafkaListener annotated methods
	 * 
	 */

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	/**
	 * 
	 * 2. Consume any object from Kafka - ConsumerFactory <String, (Object Name eg.
	 * User, Role, Message, etc)>
	 * 
	 * 
	 */

	public ConsumerFactory<String, User> userConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, userGroupId);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(User.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, User> userKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userConsumerFactory());
		return factory;
	}
}