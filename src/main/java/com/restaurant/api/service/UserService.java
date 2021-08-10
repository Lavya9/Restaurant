package com.restaurant.api.service;

import org.springframework.http.ResponseEntity;

import com.restaurant.api.UserDetails;

public interface UserService {

	ResponseEntity<?> createUser(UserDetails userDetails);

	ResponseEntity<?> userLogin(String userid, String password);

}
