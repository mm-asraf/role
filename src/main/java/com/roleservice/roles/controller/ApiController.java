package com.roleservice.roles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roleservice.roles.entity.APIEntity;
import com.roleservice.roles.service.ApiService;

@RestController
@RequestMapping("api/apitype")
public class ApiController {

		@Autowired
		ApiService apiService;
		
		@PostMapping("/create")
		public APIEntity createApiEntity(@RequestBody APIEntity apiEntity) {
			return apiService.createApiEntity(apiEntity);
		}
}
