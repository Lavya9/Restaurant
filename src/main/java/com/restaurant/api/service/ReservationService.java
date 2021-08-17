package com.restaurant.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.ReservationDetails;

@Service
public interface ReservationService {
	ResponseEntity<?> takeReservation(ReservationDetails reservationDetails);

	ResponseEntity<ReservationDetails> getReservationDetails(String userid);
}
