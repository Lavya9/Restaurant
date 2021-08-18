package com.restaurant.api.service;

import org.springframework.http.ResponseEntity;

import com.restaurant.api.entity.UserDetails;

public interface UserService {

	ResponseEntity<?> createUser(UserDetails userDetails);

	ResponseEntity<?> userLogin(String userid, String password);

	UserDetails userAlreadyLoggedIn(String userid);

	UserDetails getUserDetails(String userid, String email);

	UserDetails getUserDetailsOne(String emailid);

}
