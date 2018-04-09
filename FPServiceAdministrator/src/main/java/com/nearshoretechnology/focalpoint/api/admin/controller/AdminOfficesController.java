package com.nearshoretechnology.focalpoint.api.admin.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nearshoretechnology.focalpoint.api.admin.controller.feign.AdminOfficesFeign;
import com.nearshoretechnology.focalpoint.api.admin.model.Office;
import com.nearshoretechnology.focalpoint.api.admin.service.IAdminOfficesService;
import com.nearshoretechnology.focalpoint.common.async.response.AsyncResponseEntity;
import com.nearshoretechnology.focalpoint.common.form.AdminOfficeDTO;
import com.nearshoretechnology.focalpoint.common.util.ControllerBase;

@RestController
@RequestMapping("/admin")
public class AdminOfficesController extends ControllerBase {
	@Autowired
	AdminOfficesFeign adminOfficeFeign;

	@Autowired
	IAdminOfficesService service;

	@GetMapping
	public AsyncResponseEntity<Office> index2(Model model) {
		AdminOfficeDTO form = new AdminOfficeDTO();
		form.setUsername("ABC");
		return makeAsyncResponse(service.findAllOffices(form));
	}

	@GetMapping("/ping")
	public String doPing() {
		@SuppressWarnings("deprecation")
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return "Pong form -->"+localInstance.getHost() + ":" + localInstance.getPort() + " Time: "+LocalDateTime.now();
	}
	
	@GetMapping("/noRibbon/ping")
	public String doRestAlive() {
		@SuppressWarnings("deprecation")
		ServiceInstance localInstance = client.getLocalServiceInstance();
		return new RestTemplate().getForObject("http://"+localInstance.getHost() + ":" + localInstance.getPort() + "/admin/ping",
				String.class);
	}

	@GetMapping("/ribbon/ping")
	public String doRestAliveUsingEurekaAndRibbonPing() {
		return restTemplate.getForObject("http://ADMINISTRATOR-SERVICE/admin/ping", String.class);
	}
	
	@GetMapping("/office/ribbon")
	public String doRestAliveUsingEurekaAndRibbon() {
		return restTemplate.getForObject("http://ADMINISTRATOR-SERVICE/admin", String.class);
	}

	@GetMapping("/office/feign")
	public String index(Model model) {
		return this.adminOfficeFeign.index2();
	}

}
