package com.nearshoretechnology.focalpoint.config.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class FpCloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpCloudConfigApplication.class, args);
	}
}
