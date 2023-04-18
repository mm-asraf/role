package com.roleservice.roles.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roleservice.roles.entity.APIEntity;
import com.roleservice.roles.entity.PermissionTypeEntity;
import com.roleservice.roles.repository.ApiRepository;
import com.roleservice.roles.repository.PermissionTypeRepository;
import com.roleservice.roles.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
	
	
//	@Autowired
//	PermissionsRepository permissionRepository;
	
	@Autowired
	ApiRepository apiRepository;
	
	@Autowired
	PermissionTypeRepository permissionTypeRepository;

	@Override
	public List<JSONObject> getAllPermissions() {
		
		List<APIEntity> apiEntities = apiRepository.findByIsVisibleIsOrderByDisplayOrderAsc(true);
		List<PermissionTypeEntity> permissionTypeEntities = permissionTypeRepository.findByIsVisibleIsOrderByDisplayNameAsc(true);
		
		List<JSONObject> jsonObject = new ArrayList<JSONObject>();
		
		apiEntities.forEach( api -> {
			JSONObject apiObject = new JSONObject();
			String apiName = api.getApiName().toString();
			
			
			apiObject.put("displayName", api.getDisplayName());
			apiObject.put("apiName", apiName);
			
			List<JSONObject> permissionObjectLists = new ArrayList<JSONObject>();
			
			permissionTypeEntities.forEach( permissionType -> {
				JSONObject permissionObject = new JSONObject();
				String associatedApisName = permissionType.getAssociatedApis().trim();
				
//				String associatedApisName = permissionType
				String[] associatedApisNameArr = associatedApisName.split(",");
				
				
				if (!associatedApisName.isEmpty() 
						&& Arrays.asList(associatedApisNameArr).contains(apiName)) {
					permissionObject.put("displayName", permissionType.getDisplayName());
					permissionObject.put("permission", apiName +"_"+ permissionType.getPermissionsType().toString());
					permissionObjectLists.add(permissionObject);
					
				} else if(associatedApisName.isEmpty()) {
					permissionObject.put("displayName", permissionType.getDisplayName());
					permissionObject.put("permission", apiName +"_"+ permissionType.getPermissionsType().toString());
					permissionObjectLists.add(permissionObject);
				}
			});
			
			apiObject.put("permissionTypes", permissionObjectLists);
			
			jsonObject.add(apiObject);
		});
		
		
		return jsonObject;
	}

}
