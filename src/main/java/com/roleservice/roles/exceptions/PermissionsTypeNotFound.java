package com.roleservice.roles.exceptions;

public class PermissionsTypeNotFound extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public PermissionsTypeNotFound(String message) {
		super(message);
	}
}
