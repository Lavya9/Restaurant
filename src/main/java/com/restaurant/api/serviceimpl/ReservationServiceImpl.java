package com.restaurant.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.ReservationDetails;
import com.restaurant.api.repository.ReservationRepository;
import com.restaurant.api.service.ReservationService;
import com.restaurant.api.util.ConstantUtil;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	Environment environment;

	@Override
	public ResponseEntity<?> takeReservation(ReservationDetails reservationDetails) {

		String str = environment.getProperty(ConstantUtil.RESERVATIONCONFIRMED);

		if (reservationRepository.findByUserid(reservationDetails.getUserid()) != null) {
			reservationDetails = reservationRepository.findByUserid(reservationDetails.getUserid());// if this and below
																									// not than booking
																									// id shown as null
			reservationDetails.setBookingId(reservationDetails.getBookingId());
			System.out.println(reservationDetails);
			return ResponseEntity
					.ok(environment.getProperty(ConstantUtil.RESERVATIONUPDATED) + reservationDetails.getBookingId());
		}

		reservationDetails.setBookingId(reservationDetails.getUserid() + reservationDetails.getContactNo());

		reservationRepository.save(reservationDetails);

		return ResponseEntity.ok(str + reservationDetails.getBookingId());
	}

	@Override
	public ResponseEntity<ReservationDetails> getReservationDetails(String userid) {
		ReservationDetails reservationDetails = reservationRepository.findByUserid(userid);

		if (reservationRepository.findByUserid(userid) == null) {

			return new ResponseEntity<ReservationDetails>(HttpStatus.NOT_FOUND); // to pass message here need to do

		}

		return new ResponseEntity<ReservationDetails>(reservationDetails, HttpStatus.OK);
	}

}
