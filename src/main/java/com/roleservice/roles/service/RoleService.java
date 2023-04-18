package com.roleservice.roles.service;


import java.util.List;
import com.roleservice.roles.entity.RoleEntity;
import com.roleservice.roles.entity.common.Permission;
import com.roleservice.roles.entity.common.Role;
import com.roleservice.roles.exceptions.BussinessNotFound;
import com.roleservice.roles.exceptions.RoleNotFound;
import com.roleservice.roles.exceptions.UserNotFound;

public interface RoleService {
	
	RoleEntity create(Role role);

	List<RoleEntity> getAllRoles();

	RoleEntity getById(Long id) throws RoleNotFound;

	RoleEntity getByRoleName(String roleName) throws RoleNotFound;

	void deleteById(Long id);
	
	Boolean setPermission( Permission permission, Long businessId) throws UserNotFound, BussinessNotFound;

	
	
}
