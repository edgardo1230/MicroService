package com.nearshoretechnology.focalpoint.api.admin.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ADMINISTRATOR-SERVICE")
public interface AdminOfficesFeign {

	@GetMapping(value="/admin")
    String index2();
}
