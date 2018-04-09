package com.nearshoretechnology.focalpoint.api.admin.config;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.nearshoretechnology.focalpoint.api.admin.controller.feign")
public class RibbonConfiguration {

}
