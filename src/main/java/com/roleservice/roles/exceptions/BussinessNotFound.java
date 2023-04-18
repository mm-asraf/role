package com.roleservice.roles.exceptions;

public class BussinessNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public BussinessNotFound(String message) {
		super(message);
	}

}
