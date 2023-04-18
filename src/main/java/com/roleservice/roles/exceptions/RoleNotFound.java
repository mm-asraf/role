package com.roleservice.roles.exceptions;

public class RoleNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public RoleNotFound(String message) {
		super(message);
	}

}
