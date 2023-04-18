package com.roleservice.roles.utils;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.roleservice.roles.entity.BussinessEntity;
import com.roleservice.roles.exceptions.BussinessNotFound;
import com.roleservice.roles.repository.BussinessRepository;

@Service
public class Utils {
	
//	@Autowired
	private BussinessRepository bussinessRepository;

	public BussinessEntity verifyBusinessId(Long businessId) throws BussinessNotFound {

		BussinessEntity businessEntity = null;

		Optional<BussinessEntity> optionalBusinessEntity = bussinessRepository.findById(businessId);
		if (optionalBusinessEntity.isPresent()) {
			businessEntity = optionalBusinessEntity.get();
		} else {
			throw new BussinessNotFound("Given business ID is not valid");
		}
		return businessEntity;
	}

}
