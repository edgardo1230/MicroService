package com.nearshoretechnology.focalpoint.api.qbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
public class FpQborServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpQborServiceApplication.class, args);
	}
}
