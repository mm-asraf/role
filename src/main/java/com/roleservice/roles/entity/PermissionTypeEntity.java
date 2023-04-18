package com.roleservice.roles.entity;

import java.io.Serializable;

import com.roleservice.roles.entity.common.PermissionsType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name="permissiontype")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class PermissionTypeEntity implements Serializable {
	
	private transient static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
	
	@Column(name = "DISPLAY_NAME")
	String displayName;
	
	@Column(name = "ASSOCIATED_APIS")
	String associatedApis;
	
	@Column(name = "IS_VISIBLE", columnDefinition = "boolean default false")
	Boolean isVisible;
	
	@Column(name = "IS_ADMIN_VISIBLE", columnDefinition = "boolean default false")
	Boolean isAdminVisible;
	
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    PermissionsType permissionsType;
	
	
}
