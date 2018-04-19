package com.nearshoretechnology.focalpoint.api.qbo.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("FP-SERVICE-QBO")
public interface AdminOfficesFeign {

	@GetMapping(value="/admin")
    String index2();
}
