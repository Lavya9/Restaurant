package com.restaurant.api.serviceimpl;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.UserDetails;
import com.restaurant.api.repository.ReservationRepository;
import com.restaurant.api.repository.UserRepository;
import com.restaurant.api.service.UserService;
import com.restaurant.api.util.ConstantUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	Environment environment;

	public ResponseEntity<?> createUser(UserDetails userDetails) {

		userDetails.setFirstName(userDetails.getFirstName());
		userDetails.setLastName(userDetails.getLastName());
		userDetails.setContactNo(userDetails.getContactNo());
		userDetails.setEmail(userDetails.getEmail());
		userDetails.setPassword(userDetails.getPassword());
		String firstName = (userDetails.getFirstName()).substring(0, 2);
		String lastName = (userDetails.getLastName()).substring(0, 2);
		String contactNo = (userDetails.getContactNo()).substring(6, 10);
		String userid = firstName.toUpperCase() + lastName.toUpperCase() + contactNo;
		userDetails.setUserid(userid);

		if ((userRepository.findByUserid(userDetails.getUserid()) != null)) {
			return new ResponseEntity<String>(environment.getProperty(ConstantUtil.USERALREADYEXIST),
					HttpStatus.CONFLICT);
		}

		String encodedString = Base64.getEncoder().withoutPadding()
				.encodeToString((userDetails.getPassword()).getBytes());
		userDetails.setPassword(encodedString);
		userRepository.save(userDetails);

		return ResponseEntity
				.ok(environment.getProperty(ConstantUtil.USERCREATEDSUCCESSFULLY) + userDetails.getUserid());
	}

	@Override
	public ResponseEntity<?> userLogin(String userid, String password) {

		UserDetails userDetails = userRepository.findByUserid(userid);
		if (userDetails == null) {
			return new ResponseEntity<String>(environment.getProperty(ConstantUtil.USERNOTEXIST) + userid,
					HttpStatus.NOT_FOUND);
		}

		byte[] decodedBytes = Base64.getDecoder().decode(userDetails.getPassword());
		String decodedString = new String(decodedBytes);

		if (!decodedString.equals(password)) {
			return new ResponseEntity<String>(environment.getProperty(ConstantUtil.INVALIDPASSWORD) + userid,
					HttpStatus.UNAUTHORIZED);

		}

		if (!userid.equals(userDetails.getUserid())) {
			return new ResponseEntity<String>(environment.getProperty("INVALIDUSERID") + userid,
					HttpStatus.UNAUTHORIZED);

		}

		return ResponseEntity.ok(environment.getProperty(ConstantUtil.USERLOGGEDIN) + userid);

	}

	public UserDetails userAlreadyLoggedIn(String userid) {

		UserDetails userDetails = userRepository.findByUserid(userid);
		return userDetails;
	}

	@Override
	public UserDetails getUserDetails(String userid, String email) {

		UserDetails userDetails = userRepository.findByUseridAndEmail(userid, email);
		return userDetails;
	}

	@Override
	public UserDetails getUserDetailsOne(String emailid) {
		UserDetails userDetails = userRepository.findUserByKeyword(emailid);
		return userDetails;
	}

}
