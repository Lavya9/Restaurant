package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.restaurant.api.entity.Menus;
import com.restaurant.api.entity.UserDetails;

public interface MenuRepository extends JpaRepository<Menus, Long> {

	Menus findByCategory(String category);

	@Query(value = "select * from menus m where m.id = :keyword or m.category = :keyword", nativeQuery = true)
	Menus findMenusByKeyword(@Param("keyword") String keyword);
}
