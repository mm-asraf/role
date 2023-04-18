package com.roleservice.roles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.roleservice.roles.entity.RoleEntity;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long>{
	
	RoleEntity findByRoleName(String roleName);
	
	List<RoleEntity> findAll();
	
	Optional<RoleEntity> findById(Long id);

	void deleteById(Long id);
	
	List<RoleEntity> findAllByType(String type);

}
