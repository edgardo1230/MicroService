package com.nearshoretechnology.focalpoint.config.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FpEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpEurekaApplication.class, args);
	}
}
