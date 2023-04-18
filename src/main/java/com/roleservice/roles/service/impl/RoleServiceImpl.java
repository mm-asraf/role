package com.roleservice.roles.service.impl;


import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roleservice.roles.entity.APIEntity;
import com.roleservice.roles.entity.PermissionTypeEntity;
import com.roleservice.roles.entity.RoleEntity;
import com.roleservice.roles.entity.RolePermissionsEntity;
import com.roleservice.roles.entity.UserEntity;
import com.roleservice.roles.entity.common.APITypes;
import com.roleservice.roles.entity.common.GsonUtil;
import com.roleservice.roles.entity.common.Permission;
import com.roleservice.roles.entity.common.PermissionsType;
import com.roleservice.roles.entity.common.Role;
import com.roleservice.roles.exceptions.BussinessNotFound;
import com.roleservice.roles.exceptions.RoleNotFound;
import com.roleservice.roles.exceptions.UserNotFound;
import com.roleservice.roles.repository.ApiRepository;
import com.roleservice.roles.repository.PermissionTypeRepository;
import com.roleservice.roles.repository.RoleRepository;
import com.roleservice.roles.repository.UserRepository;
import com.roleservice.roles.service.RoleService;
import com.roleservice.roles.utils.Utils;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RoleServiceImpl implements RoleService {

	@Autowired
	PermissionTypeRepository permissionTypeRepository;

	@Autowired
	ApiRepository apiRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PermissionTypeRepository permissionsRepository;

	@Autowired
	Utils utils;


	@Override
	public RoleEntity create(Role role) {

		Set<RolePermissionsEntity> permissionsEntities = new HashSet<>();

		RolePermissionsEntity rolePermissionsEntity = null;

		APIEntity apiEntity;

		PermissionTypeEntity permissionTypeEntity = null;

		if(role.getPermissions() != null) {
			System.out.println(role.getPermissions().entrySet()+"______      ");
			for (Map.Entry<APITypes, List<PermissionsType>> perMission : role.getPermissions().entrySet()) {

				apiEntity = apiRepository.findByApiName(perMission.getKey());

				if(perMission.getValue() != null) {

					for(int i = 0;i <  perMission.getValue().size();i++) {
						permissionTypeEntity = permissionTypeRepository.findByPermissionsType(perMission.getValue().get(i));
						
						System.out.println(perMission.getValue()+"ppppppppppppppppp");
						
						System.out.println("+++++++++++++"+permissionTypeEntity);
						rolePermissionsEntity = RolePermissionsEntity.builder()
							.api(apiEntity)
								.permissionType(permissionTypeEntity)
								.build();

						log.info(GsonUtil.toJson(permissionTypeEntity));
						permissionsEntities.add(rolePermissionsEntity);
					}

				}

			}
		}

		log.info(GsonUtil.toJson(permissionTypeEntity));
		RoleEntity roleEntity = RoleEntity.builder()
				.roleName(role.getRoleName())
				.rolePermissions(permissionsEntities)
				.build();

		roleEntity = roleRepository.save(roleEntity);
		log.info("Role saved successfully");

		return roleEntity;
	}


	@Override
	public Boolean setPermission(Permission permission, Long businessId) throws UserNotFound, BussinessNotFound {

		//verify bussiness
		utils.verifyBusinessId(businessId);

//		PermissionsEntity permissionsEntity = null;

		Optional<UserEntity> optionalUserEntity = userRepository.findById(permission.getUserId());

		if(!optionalUserEntity.isPresent()) {
			throw new UserNotFound("user not found exception");
		}

		try {

			log.info("Get Permission",permission.getPermissions().toString());

			if(permission.getPermissions() != null) {

				UserEntity userEntity = optionalUserEntity.get();

				List<APIEntity> apiVisibleEntities = apiRepository.findByIsVisibleIs(true);

				apiVisibleEntities.forEach(apiItem -> {
					Long apiId = apiItem.getId();

//					Integer deleted = permissionsRepository.deleteByUserIdandApiIdAndBusinessId(permission.getUserId(), apiId, businessId);

					log.info(permission.getUserId() + " == " + apiId + " == " + businessId);
//					log.info("Deleted count:" + deleted.toString());
				});

				// Set Permissions
				for (Map.Entry<APITypes, List<PermissionsType>> objPermission : permission.getPermissions()
						.entrySet()) {

					APIEntity apiEntity = apiRepository.findByApiName(objPermission.getKey());
					log.info("API Name " + objPermission.getKey());
					for (int i = 0; i < objPermission.getValue().size(); i++) {
						PermissionsType permissionType = objPermission.getValue().get(i);
						PermissionTypeEntity permissionTypeEntity = permissionTypeRepository
								.findByPermissionsType(permissionType);
						log.info("Permission Type " + permissionType.toString());

						if (permissionTypeEntity == null) {
							throw new UserNotFound("Invalid permission type supplied");
						}

						System.out.println("---------"+permissionTypeEntity);
					PermissionTypeEntity	permissionsTypeEntity = PermissionTypeEntity.builder()
								.id(businessId)
//								.api(apiEntity)
//								.permissionType(permissionTypeEntity)
//								.userId(userEntity).businessId(businessId)
								.associatedApis("")
								.isAdminVisible(true)
								.isVisible(true)
								.displayName("")
								.permissionsType(permissionType)
								.build();
						permissionsRepository.save(permissionsTypeEntity);

					}

				}

				return true;
			}else {
				return false;
			}

		} catch (Exception e) {
			throw new UserNotFound("Failed to update user permission");
		}

	}


	@Override
	public List<RoleEntity> getAllRoles() {

		return roleRepository.findAll();
	}

	@Override
	public RoleEntity getById(Long id) throws RoleNotFound {

		Optional<RoleEntity> optionEntity = roleRepository.findById(id);
		if (optionEntity.isPresent())
			return optionEntity.get();
		else
			throw new RoleNotFound("Role not found with the given id.");
	}

	@Override
	public RoleEntity getByRoleName(String roleName) throws RoleNotFound {

		RoleEntity optionEntity = roleRepository.findByRoleName(roleName);
		if (optionEntity != null)
			return optionEntity;
		else
			throw new RoleNotFound("Role not found with the given id.");
		
	}

	@Override
	public void deleteById(Long id) {
		roleRepository.deleteById(id);

	}



}
