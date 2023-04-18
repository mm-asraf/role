package com.roleservice.roles.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roleservice.roles.entity.APIEntity;
import com.roleservice.roles.repository.ApiRepository;
import com.roleservice.roles.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService{
	
	@Autowired
	ApiRepository apiRepository;

	@Override
	public APIEntity createApiEntity(APIEntity apiEntity) {
		APIEntity apiEnt = apiRepository.save(apiEntity);
		return apiEnt;
	}

}
