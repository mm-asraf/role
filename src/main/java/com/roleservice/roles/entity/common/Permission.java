package com.roleservice.roles.entity.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission implements Serializable {

	private static final long serialVersionUID = 1L;
	

//	@NotNull
	Map<APITypes, List<PermissionsType>> permissions;
	
//	@NotNull
	public Long userId;
}
