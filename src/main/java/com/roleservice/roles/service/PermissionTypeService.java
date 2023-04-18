package com.roleservice.roles.service;

import java.util.List;

import com.roleservice.roles.entity.PermissionTypeEntity;
import com.roleservice.roles.entity.common.PermissionsType;
import com.roleservice.roles.exceptions.PermissionTypeNotFound;
import com.roleservice.roles.exceptions.PermissionsTypeNotFound;

public interface PermissionTypeService {

	PermissionTypeEntity create(PermissionTypeEntity permissionTypeEntity);

	PermissionTypeEntity setPermissionType(String permissionType);

	List<PermissionTypeEntity> getAllPermissionsTypes();

	PermissionTypeEntity getById(Long id) throws PermissionTypeNotFound;

	PermissionTypeEntity getByPermissionName(PermissionsType permissionsType) throws PermissionTypeNotFound;

}
