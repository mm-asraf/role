package com.roleservice.roles.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roleservice.roles.entity.PermissionTypeEntity;
import com.roleservice.roles.entity.common.PermissionsType;
import com.roleservice.roles.exceptions.PermissionTypeNotFound;
import com.roleservice.roles.exceptions.PermissionsTypeNotFound;
import com.roleservice.roles.repository.PermissionTypeRepository;
import com.roleservice.roles.service.PermissionTypeService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PermissionTypeServiceImpl implements PermissionTypeService {


	@Autowired
	PermissionTypeRepository permissionTypeRepository;

//		@Autowired
//		StringUtilities stringUtilities;

	@Override
	public PermissionTypeEntity create(PermissionTypeEntity permissionsType) {
		
		//log.info(stringUtilities.buildString("Create permissionsType Request received: ", permissionsType.toString()));
//		PermissionTypeEntity entity = PermissionTypeEntity.builder().(permissionsType).build();
		
		PermissionTypeEntity	entity = permissionTypeRepository.save(permissionsType);

		log.info("Permission type saves successfuly!");

		return entity;

	}

	@Override
	public List<PermissionTypeEntity> getAllPermissionsTypes() {
		return permissionTypeRepository.findAll();
	}

	@Override
	public PermissionTypeEntity getById(Long id) throws PermissionsTypeNotFound {

		Optional<PermissionTypeEntity> entity =permissionTypeRepository.findById(id);
		if(entity!=null)
			return entity.get();
		else
			throw new PermissionTypeNotFound("Permission Type Not Found with the given id.");
	}

	@Override
	public PermissionTypeEntity getByPermissionName(PermissionsType permissionsType) throws PermissionsTypeNotFound {

		PermissionTypeEntity entity =permissionTypeRepository.findByPermissionsType(permissionsType);
		if(entity!=null)
			return entity;
		else
			throw new PermissionTypeNotFound("Permission Type Not Found with the given type.");
	}

	@Override
	public PermissionTypeEntity setPermissionType(String permissionType) {
		PermissionsType valueOf = PermissionsType.valueOf(permissionType);
		PermissionTypeEntity a= new PermissionTypeEntity(1L, permissionType, "", true, true, valueOf);
		PermissionTypeEntity permissionTypeD = permissionTypeRepository.save(a);
		
		return permissionTypeD;
	}

}
