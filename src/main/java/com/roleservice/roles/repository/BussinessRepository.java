package com.roleservice.roles.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import com.roleservice.roles.entity.BussinessEntity;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface BussinessRepository extends JpaRepository<BussinessEntity, Long>,JpaSpecificationExecutor<BussinessEntity> {
	
	Optional<BussinessEntity> findById(Integer businessId);
}
