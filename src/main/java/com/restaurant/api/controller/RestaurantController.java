package com.restaurant.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.entity.UserDetails;
import com.restaurant.api.service.UserService;

@RestController // ("/Restaurant")
public class RestaurantController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> userRegistration(@RequestBody UserDetails userDetails) {

		ResponseEntity<?> createUser = userService.createUser(userDetails);

		return createUser;
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> userLogin(@RequestBody UserDetails userDetails) {

		ResponseEntity<?> userLogin = userService.userLogin(userDetails.getUserid(), userDetails.getPassword());

		return userLogin;
	}

}