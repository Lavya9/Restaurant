package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.api.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

	// ResponseEntity<?> save(UserDetails userDetails);

	UserDetails findByUserid(String userid);

}
