package com.roleservice.roles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roleservice.roles.entity.APIEntity;
import com.roleservice.roles.entity.common.APITypes;

@Repository
public interface ApiRepository extends JpaRepository<APIEntity, Long>{
	
	APIEntity findByApiName(APITypes apiName);

	Optional<APIEntity> findById(Long id);
	
	List<APIEntity> findByIsVisibleIsOrderByDisplayOrderAsc(Boolean isVisible);
	
	List<APIEntity> findByIsVisibleIs(Boolean isVisible);
}
