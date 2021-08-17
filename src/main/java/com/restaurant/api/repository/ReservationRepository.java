package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.entity.ReservationDetails;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, String> {

	ReservationDetails findByUserid(String userid);

}
