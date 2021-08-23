package com.restaurant.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.api.entity.Feedback;
import com.restaurant.api.entity.Gallery;
import com.restaurant.api.entity.Menus;
import com.restaurant.api.entity.ReservationDetails;
import com.restaurant.api.entity.UserDetails;
import com.restaurant.api.service.FeedbackService;
import com.restaurant.api.service.GalleryService;
import com.restaurant.api.service.MenuService;
import com.restaurant.api.service.ReservationService;
import com.restaurant.api.service.UserService;
import com.restaurant.api.util.ConstantUtil;

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

	@Autowired
	 FeedbackService feedbackService;

	@Autowired
	 GalleryService galleryService;

	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> userRegistration(@Valid @RequestBody UserDetails userDetails) {

		ResponseEntity<?> createUser = userService.createUser(userDetails);

		return createUser;
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> userLogin(@RequestBody UserDetails userDetails, HttpServletRequest request) {

		request.getSession();
		session.setMaxInactiveInterval(1 * 60);

		UserDetails user = (UserDetails) session.getAttribute("userDetails");
		if (user != null) {
			return new ResponseEntity<String>(environment.getProperty(ConstantUtil.USERALREADYLOGGEDIN),
					HttpStatus.CONFLICT);
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

	@RequestMapping(value = "/userDetails/{emailid}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public UserDetails getUserDetailsOne(@PathVariable String emailid) {

		UserDetails getUser = userService.getUserDetailsOne(emailid);

		return getUser;
	}

	@RequestMapping(value = "/reservation", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> reservation(@RequestBody ReservationDetails reservationDetails,
			HttpServletRequest request) {

		// request.getSession();
		// session.setMaxInactiveInterval(1 * 60);
		UserDetails user = (UserDetails) session.getAttribute("userDetails");
		if (user == null) {
			return new ResponseEntity<String>(environment.getProperty(ConstantUtil.USERNOTLOGGEDIN),
					HttpStatus.UNAUTHORIZED);
		}
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

	@RequestMapping(value = "/menus", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Menus saveMenuItems(@RequestBody Menus menus) {

		Menus getMenuItems = menuService.saveMenuItems(menus);
		return getMenuItems;
	}

	@RequestMapping(value = "/menus", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Menus>> getMenuItems() {

		ResponseEntity<List<Menus>> getMenuItems = menuService.getMenuItems();

		return getMenuItems;
	}

	@Cacheable(value = "cacheMenusInfo", key = "#categoryid")
	@RequestMapping(value = "/menus/{categoryid}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public Menus getMenuCategoryId(@PathVariable String categoryid) {
		System.out.println("Getting user with ID " + categoryid);
		Menus menuCategory = menuService.getMenuCategory(categoryid);
		return menuCategory;
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> saveFeedback(@RequestParam String message) {

		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		if(userDetails==null) {
			return new ResponseEntity<String>(environment.getProperty(ConstantUtil.USERNOTLOGGEDIN),
					HttpStatus.UNAUTHORIZED);
		}
		String name =userDetails.getFirstName()+" "+userDetails.getLastName();
		String email =userDetails.getEmail();
		Feedback feedback = new Feedback();
		feedback.setEmail(email);
		feedback.setName(name);
		feedback.setMessage(message);
		ResponseEntity<?> saveFeedback = feedbackService.saveFeedback(feedback);

		return saveFeedback;
	}
	
	@RequestMapping(value = "/feedback/{emailid}", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public List<Feedback> getFeedback(@PathVariable String emailid) {

		List<Feedback>  getFeedback = feedbackService.saveFeedback(emailid);

		return getFeedback;
	}
	
	@RequestMapping(value = "/gallery", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
	public List<Gallery> getGalleryImages() {

		List<Gallery>  getGalleryImages = galleryService.getGalleryImages();

		return getGalleryImages;
	}
}