package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.restaurant.api.entity.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, String> {

	UserDetails findByUserid(String userid);

	UserDetails findByUseridAndEmail(String userid, String email);

//@Query(value="select * from user_details u where u.userid LIKE %:keyword% or u.email LIKE %:keyword%" ,nativeQuery=true)
//UserDetails findUserByKeyword(@Param("keyword") String keyword);

	@Query(value = "select * from user_details u where u.userid = :keyword or u.email = :keyword", nativeQuery = true)
	UserDetails findUserByKeyword(@Param("keyword") String keyword);

}
