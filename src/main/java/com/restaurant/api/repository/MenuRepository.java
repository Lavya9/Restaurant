package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.entity.Menus;

public interface MenuRepository extends JpaRepository<Menus, Long> {

	Menus findByCategory(String category);
}
