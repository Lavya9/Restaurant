package com.restaurant.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.restaurant.api.entity.Menus;

public interface MenuService {

	Menus saveMenuItems(Menus menus);

	ResponseEntity<List<Menus>> getMenuItems();

	Menus getMenuCategory(String category);

}
