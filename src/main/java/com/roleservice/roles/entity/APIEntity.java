package com.roleservice.roles.entity;

import java.io.Serializable;
import com.roleservice.roles.entity.common.APITypes;
import com.roleservice.roles.entity.common.DataObjectApis;
import com.roleservice.roles.entity.common.GsonUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "api")
@EqualsAndHashCode(callSuper = false)
@Builder
public class APIEntity implements Serializable,DataObjectApis {

	private transient static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "API_TYPES")
	@Enumerated(EnumType.STRING)
	private APITypes apiName;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "DISPLAY_ORDER")
	private Integer displayOrder;

	private Integer adminDispalyOrder;

	@Column(name = "IS_VISIBLE",columnDefinition = "boolean default false")
	private Boolean isVisible;

	@Column(name = "IS_ADMIN_VISIBLE",columnDefinition = "boolean default false")
	private Boolean isAdminVisible;


	@Override
	public String toJson() {
		return GsonUtil.toJson(this);
	}

}
