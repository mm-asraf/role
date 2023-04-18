package com.roleservice.roles.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.roleservice.roles.entity.PermissionTypeEntity;
import com.roleservice.roles.entity.common.PermissionsType;
import com.roleservice.roles.exceptions.PermissionTypeNotFound;
import com.roleservice.roles.service.PermissionTypeService;
import com.roleservice.roles.utils.StringUtilities;

@RestController
@RequestMapping("/api/permissiontype")
public class PermissionTypeController {

	/*
	 * There is no security to get the APIs. Everyone should have access to this. If
	 * required, it can be added later.
	 */

	@Autowired
	PermissionTypeService permissionService;

	@Autowired
	StringUtilities stringUtilities;

	@PostMapping("/create")
	public PermissionTypeEntity createPermissionType(@RequestBody PermissionTypeEntity permissionTypeEntity) {
		return permissionService.create(permissionTypeEntity);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody List<PermissionTypeEntity> getAllPermissions() {
		return permissionService.getAllPermissionsTypes();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody PermissionTypeEntity get(@PathVariable Long id) throws PermissionTypeNotFound {
		return permissionService.getById(id);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody PermissionTypeEntity get(@RequestParam PermissionsType permissionsType) throws PermissionTypeNotFound {
		return permissionService.getByPermissionName(permissionsType);
	}

}
