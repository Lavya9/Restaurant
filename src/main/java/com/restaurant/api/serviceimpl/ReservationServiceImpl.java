package com.restaurant.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.ReservationDetails;
import com.restaurant.api.repository.ReservationRepository;
import com.restaurant.api.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	Environment environment;

	@Override
	public ResponseEntity<?> takeReservation(ReservationDetails reservationDetails) {

		String str = environment.getProperty("RESERVATIONCONFIRMED");

		if (reservationRepository.findByUserid(reservationDetails.getUserid()) != null) {
			return ResponseEntity.ok(environment.getProperty("RESERVATIONUPDATED") + reservationDetails.getUserid());
		}

		/*
		 * if(reservationRepository.findByUserid(reservationDetails.getUserid())==null)
		 * { return ResponseEntity.ok(environment.getProperty("INVALIDUSERID") +
		 * reservationDetails.getUserid()); }
		 */
		// reservationDetails.setUserid(reservationDetails.getUserid());
		reservationDetails.setBookingType(reservationDetails.getBookingType());
		reservationDetails.setName(reservationDetails.getName());
		reservationDetails.setContactNo(reservationDetails.getContactNo());
		reservationDetails.setPeople(reservationDetails.getPeople());
		reservationDetails.setDateAndTime(reservationDetails.getDateAndTime());

		reservationRepository.save(reservationDetails);

		return ResponseEntity.ok(str + reservationDetails.getUserid());
	}

	@Override
	public ResponseEntity<ReservationDetails> getReservationDetails(String userid) {
		ReservationDetails reservationDetails = reservationRepository.findByUserid(userid);

		if (reservationRepository.findByUserid(userid) == null) {
			// return new
			// ResponseEntity<ReservationDetails>(environment.getProperty("INVALIDUSERID"),HttpStatus.NOT_FOUND);
			return new ResponseEntity<ReservationDetails>(HttpStatus.NOT_FOUND); // to pass message here need to do
																					// exception handling
			// return new ResponseEntity<>("Hello World!", HttpStatus.OK);
		}

		return new ResponseEntity<ReservationDetails>(reservationDetails, HttpStatus.OK);
	}

}
