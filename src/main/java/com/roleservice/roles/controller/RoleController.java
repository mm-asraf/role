package com.roleservice.roles.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.roleservice.roles.entity.RoleEntity;
import com.roleservice.roles.entity.common.Permission;
import com.roleservice.roles.entity.common.Role;
import com.roleservice.roles.exceptions.BussinessNotFound;
import com.roleservice.roles.exceptions.RoleNotFound;
import com.roleservice.roles.exceptions.UserNotFound;
import com.roleservice.roles.service.RoleService;
import com.roleservice.roles.utils.StringUtilities;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	StringUtilities stringUtilities;

	@RequestMapping(path = "/create",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	@PreAuthorize("hasAuthority('ROLE_CREATE')")
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	public ResponseEntity<RoleEntity> create( @RequestBody Role request) throws NoSuchAlgorithmException, UnsupportedEncodingException, RoleNotFound {

		System.out.println(request.getRoleName());
		log.info(StringUtilities.buildString("Create Role Request: ", request.toJson()));
		RoleEntity roleEntity = roleService.create(request);
		return new ResponseEntity<RoleEntity>(roleEntity,HttpStatus.OK);

	}

	@RequestMapping(path = "/get",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public @ResponseBody List<RoleEntity> getAllRoles() {
		System.out.println("cccccccccccccc");
		System.out.println(roleService.getAllRoles());
		return roleService.getAllRoles();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public @ResponseBody RoleEntity get(@PathVariable Long id) throws RoleNotFound {
		return roleService.getById(id);
	}

	@RequestMapping(value = "/filter", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_READ')")
	public @ResponseBody RoleEntity get(@RequestParam String name) throws RoleNotFound {
		return roleService.getByRoleName(name);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAuthority('ROLE_DELETE')")
	@Transactional
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		roleService.deleteById(id);
	}

	@RequestMapping(value = "/setPermission/{businessId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(value = HttpStatus.OK)
	@Transactional
	@PreAuthorize("hasAuthority('USER_PERMISSIONS')")
	public void setPermissions(@RequestBody Permission permission, @PathVariable Long businessId)
			throws UserNotFound, BussinessNotFound {

		if (businessId == null) {
			throw new BussinessNotFound("Business id not found");
		}

		roleService.setPermission(permission, businessId);

	}

}
