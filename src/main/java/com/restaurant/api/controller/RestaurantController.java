package com.restaurant.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.entity.UserDetails;
import com.restaurant.api.service.UserService;
import com.restaurant.api.util.ConstantUtil;

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
	public ResponseEntity<?> userLogin(@RequestBody UserDetails userDetails, HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1 * 30);
		//String str = (String) session.getAttribute("userDetails");
		UserDetails user = (UserDetails) session.getAttribute("userDetails");
		if (user != null) {
			return new ResponseEntity<String>(ConstantUtil.USERALREADYLOGGEDIN, HttpStatus.CONFLICT);
		}
		// session.invalidate();
		ResponseEntity<?> userLogin = userService.userLogin(userDetails.getUserid(), userDetails.getPassword());

		session.setAttribute("userDetails", userService.userAlreadyLoggedIn(userDetails.getUserid()));
		return userLogin;
	}

}