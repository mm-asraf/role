package com.roleservice.roles.entity.common;

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
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Role implements DataObjectApis{
	
//	@NotBlank
	String roleName;

//	@NotNull
	Map<APITypes, List<PermissionsType>> permissions;
	
	@Override
	public String toJson() {
		return GsonUtil.toJson(this);
	}
	
	
}
