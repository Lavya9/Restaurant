package com.restaurant.api.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.UserDetails;
import com.restaurant.api.repository.UserRepository;
import com.restaurant.api.util.ConstantUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	public ResponseEntity<?> createUser(UserDetails userDetails) {
		if ((userRepository.findByUserid(userDetails.getUserid()) != null)) {
			System.out.println(ConstantUtil.USERALREADYEXIST);
			return new ResponseEntity<String>(ConstantUtil.USERALREADYEXIST, HttpStatus.CONFLICT);
		}
		userDetails.setFirstName(userDetails.getFirstName());
		userDetails.setLastName(userDetails.getLastName());
		userDetails.setContactNo(userDetails.getContactNo());
		userDetails.setEmail(userDetails.getEmail());
		userDetails.setPassword(userDetails.getPassword());
		String firstName = (userDetails.getFirstName()).substring(0, 2);
		String lastName = (userDetails.getLastName()).substring(0, 2);
		String contactNo = (userDetails.getContactNo()).substring(6,10);
		String userid = firstName + lastName + contactNo;
		userDetails.setUserid(userid);

		//System.out.println(userid);
		if ((userRepository.findByUserid(userDetails.getUserid()) != null)) {
			System.out.println(ConstantUtil.USERALREADYEXIST);
			return new ResponseEntity<String>(ConstantUtil.USERALREADYEXIST, HttpStatus.CONFLICT);
		}

		String encodedString = Base64.getEncoder().withoutPadding()
				.encodeToString((userDetails.getPassword()).getBytes());
		userDetails.setPassword(encodedString);
		userRepository.save(userDetails);

		return ResponseEntity.ok(ConstantUtil.USERCREATEDSUCCESSFULLY + userDetails.getUserid());
	}

	@Override
	public ResponseEntity<?> userLogin(String userid, String password) {
		// TODO Auto-generated method stub

		UserDetails userDetails = userRepository.findByUserid(userid);
		if (userDetails == null) {
			System.out.println(ConstantUtil.USERNOTEXIST + userid);
			return new ResponseEntity<String>(ConstantUtil.USERNOTEXIST + userid, HttpStatus.NOT_FOUND);
		}

		byte[] decodedBytes = Base64.getDecoder().decode(userDetails.getPassword());
		String decodedString = new String(decodedBytes);

		if (!decodedString.equals(password)) {
			System.out.println(ConstantUtil.INVALIDPASSWORD + userid);
			return new ResponseEntity<String>(ConstantUtil.INVALIDPASSWORD + userid, HttpStatus.UNAUTHORIZED);

		}

		return ResponseEntity.ok(ConstantUtil.USERSUCCESSFULLYLOGGEDIN + userid);

	}

}
