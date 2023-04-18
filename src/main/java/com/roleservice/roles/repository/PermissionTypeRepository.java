package com.roleservice.roles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.roleservice.roles.entity.PermissionTypeEntity;
import com.roleservice.roles.entity.common.PermissionsType;

@Repository
public interface PermissionTypeRepository extends JpaRepository<PermissionTypeEntity, Long> {

	PermissionTypeEntity findByPermissionsType(PermissionsType permissionsType);

//	PermissionTypeEntity findById(Long id);

	List<PermissionTypeEntity> findByIsVisibleIsOrderByDisplayNameAsc(Boolean isVisible);
}
