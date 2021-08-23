package com.restaurant.api.exception;

public class MenuNotFoundException extends RuntimeException {

	public MenuNotFoundException(String id) {
		super("Menu id not found : " + id);
	}
}
