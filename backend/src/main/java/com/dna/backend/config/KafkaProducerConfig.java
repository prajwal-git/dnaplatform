package com.dna.backend.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.dna.backend.modle.User;

@Configuration
public class KafkaProducerConfig {

	/**
	 * Kafka Producer Configuration class to configure Kafka Producer
	 * 
	 * @authors Naina and Dinesh version 1.0
	 * @since 2021/02/19
	 *
	 */

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	/**
	 * 
	 * ProducerFactory <K,V) sets the strategy for creating Kafka Producer
	 * instances.
	 * 
	 * @return DefaultKafkaProducerFactory class for all producer instances
	 * 
	 * 
	 *
	 */

	/**
	 * 
	 * 1. Send string data to Kafka - ProducerFactory <String, String>
	 * 
	 * 
	 */

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory<>(props);
	}

	/**
	 * KafkaTemplate is used for executing send message operations in all supported
	 * ways.
	 * 
	 * @return
	 */

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * 
	 * 2. Send any object to Kafka - ProducerFactory <String, (Object Name eg. User,
	 * Role, Message, etc)> *Note: the object should be similar to the object used
	 * in ConsumerFactory creating user producer factory using default configs
	 * 
	 */

	@Bean
	public ProducerFactory<String, User> userProducerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, User> userKafkaTemplate() {
		return new KafkaTemplate<>(userProducerFactory());
	}
}
