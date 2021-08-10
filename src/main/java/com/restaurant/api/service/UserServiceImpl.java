package com.restaurant.api.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import com.restaurant.api.UserDetails;
import com.restaurant.api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	public ResponseEntity<?> createUser(UserDetails userDetails) {
		// TODO Auto-generated method stub
		String str = "user successfully created with userid ";
		String str1 = "user with this user id already exist";

		if ((userRepository.findByUserid(userDetails.getUserid()) != null)) {
			throw new RuntimeException(str1);
		}

		userDetails.setFirstName(userDetails.getFirstName());
		userDetails.setLastName(userDetails.getLastName());
		userDetails.setContactNo(userDetails.getContactNo());
		userDetails.setEmail(userDetails.getEmail());
		userDetails.setPassword(userDetails.getPassword());
		String firstName = (userDetails.getFirstName()).substring(0, 1);
		String lastName = (userDetails.getLastName()).substring(0, 1);
		String contactNo = (userDetails.getContactNo()).substring(0, 3);
		String userid = firstName + lastName + contactNo;
		userDetails.setUserid(userid);

		if ((userRepository.findByUserid(userDetails.getUserid()) != null)) {
			throw new RuntimeException(str1);//changed github
		}
		System.out.println(userRepository.findByUserid(userDetails.getUserid()));
		System.out.println(userDetails.getUserid());
System.out.println("github again");
		String encodedString = Base64.getEncoder().withoutPadding()
				.encodeToString((userDetails.getPassword()).getBytes());
		userDetails.setPassword(encodedString);
		System.out.println(encodedString);
		userRepository.save(userDetails);

		return ResponseEntity.ok(str + userDetails.getUserid());
	}

	@Override
	public ResponseEntity<?> userLogin(String userid, String password) {
		// TODO Auto-generated method stub
		String str = "user successfully logged in ";
		UserDetails userDetails = userRepository.findByUserid(userid);

		if (userDetails == null) {
			throw new RuntimeException("No such user exist");
		}

		if (!userDetails.getUserid().equals(userid)) {
			throw new RuntimeException("Userid not matched");

		}

		byte[] decodedBytes = Base64.getDecoder().decode(userDetails.getPassword());
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);
		if (!decodedString.equals(password)) {
			throw new RuntimeException("password not matched");
		}
		return ResponseEntity.ok(str);

	}

}
