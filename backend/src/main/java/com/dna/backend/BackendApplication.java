package com.dna.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.dna.backend.controller.MeetingController;
import com.dna.backend.gridtask.MeetingGridTask;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication // this is bootstrap classloader this is load first
@EnableSwagger2
@EnableScheduling
@EnableAutoConfiguration
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		

}

}
