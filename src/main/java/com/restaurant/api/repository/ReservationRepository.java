package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.api.entity.ReservationDetails;
import com.restaurant.api.entity.UserDetails;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationDetails, String> {

	ReservationDetails findByUserid(String userid);

}
