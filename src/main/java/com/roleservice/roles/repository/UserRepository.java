package com.roleservice.roles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.roleservice.roles.entity.BussinessEntity;
import com.roleservice.roles.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByUserName(String userName);
	
	Optional<UserEntity> findById(Long id);
	
	List<UserEntity> findAll();
	
	List<UserEntity> findByBusinessEntity(BussinessEntity businessEntity);
}
