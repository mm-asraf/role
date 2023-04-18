package com.roleservice.roles.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.roleservice.roles.service.PermissionService;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

	@Autowired
	PermissionService permissionService;

	@RequestMapping(value = "/lists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> getPermissions() {
		List<JSONObject> permissionItems = permissionService.getAllPermissions();
		String permissionItemString = permissionItems.toString();
		return new ResponseEntity<>(permissionItemString,HttpStatus.OK);
	}

}
