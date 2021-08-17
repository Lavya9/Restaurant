package com.restaurant.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.entity.Menus;
import com.restaurant.api.entity.ReservationDetails;
import com.restaurant.api.entity.UserDetails;
import com.restaurant.api.service.MenuService;
import com.restaurant.api.service.ReservationService;
import com.restaurant.api.service.UserService;

@RestController
public class RestaurantController {

	@Autowired
	private UserService userService;

	@Autowired
	private ReservationService restaurantService;

	@Autowired
	HttpSession session;

	@Autowired
	Environment environment;

	@Autowired
	MenuService menuService;

	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> userRegistration(@RequestBody UserDetails userDetails) {

		ResponseEntity<?> createUser = userService.createUser(userDetails);

		return createUser;
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> userLogin(@RequestBody UserDetails userDetails, HttpServletRequest request) {

		request.getSession();
		session.setMaxInactiveInterval(1 * 60);

		UserDetails user = (UserDetails) session.getAttribute("userDetails");
		if (user != null) {
			return new ResponseEntity<String>(environment.getProperty("USERALREADYLOGGEDIN"), HttpStatus.CONFLICT);
		}
		// session.invalidate();
		ResponseEntity<?> userLogin = userService.userLogin(userDetails.getUserid(), userDetails.getPassword());

		session.setAttribute("userDetails", userService.userAlreadyLoggedIn(userDetails.getUserid()));
		return userLogin;
	}

	@RequestMapping(value = "/userDetails", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public UserDetails getUserDetails(@RequestParam String userid, @RequestParam String email) {

		UserDetails getUser = userService.getUserDetails(userid, email);

		return getUser;
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> reservation(@RequestBody ReservationDetails reservationDetails,
			HttpServletRequest request) {

		request.getSession();
		session.setMaxInactiveInterval(1 * 60);
		UserDetails user = (UserDetails) session.getAttribute("userDetails");
		String userid = user.getUserid();
		reservationDetails.setUserid(userid);
		ResponseEntity<?> reservation = restaurantService.takeReservation(reservationDetails);

		return reservation;
	}

	@RequestMapping(value = "/reservationDetails", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ReservationDetails> getReservationDetails(@RequestParam String userid) {

		ResponseEntity<ReservationDetails> getReservation = restaurantService.getReservationDetails(userid);

		return getReservation;
	}

	@RequestMapping(value = "/menussave", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Menus saveMenuItems(@RequestBody Menus menus) {

		Menus getMenuItems = menuService.saveMenuItems(menus);
		return getMenuItems;
	}

	@RequestMapping(value = "/menus", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Menus>> getMenuItems() {

		ResponseEntity<List<Menus>> getMenuItems = menuService.getMenuItems();

		return getMenuItems;
	}

	@RequestMapping(value = "/menus/{category}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Menus getMenuCategory(@PathVariable String category) {

		Menus menuCategory = menuService.getMenuCategory(category);
		return menuCategory;
	}

}