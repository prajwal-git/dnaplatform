package com.dna.backend.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

/**
 * Kafka Topic Configuration class to add topics for all beans of type NewTopic.
 * 
 * @authors Naina and Dinesh version 1.0
 * @since 2021/02/19
 *
 */

@Configuration
class KafkaTopicConfig {

	/**
	 * 
	 * bootstrapAddress is the starting point for Kafka to discover running servers
	 */

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${general.topic.name}")
	private String topicName;

	@Value(value = "${user.topic.name}")
	private String userTopicName;

	/**
	 * TopicBuilder creates new topics and refers to existing topics in
	 * Kafka.KafkaAdmin here, both partition and replication numbers can be given
	 * bean creation for new topic
	 */

	@Bean
	public NewTopic generalTopic() {
		return TopicBuilder.name(topicName).partitions(1).replicas(1).build();
	}

	@Bean
	public NewTopic userTopic() {
		return TopicBuilder.name(userTopicName).partitions(1).replicas(1).build();
	}

	/**
	 * 
	 * If we are not using springboot, we need to make KafkaAdmin bean like below. S
	 * In springboot,KafkaAdmid is created automatically.
	 */

	@Bean
	public KafkaAdmin kafkaAdmin() {
		Map<String, Object> configs = new HashMap<>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		return new KafkaAdmin(configs);
	}
}