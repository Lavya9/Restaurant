package com.restaurant.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.Menus;
import com.restaurant.api.repository.MenuRepository;
import com.restaurant.api.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public Menus saveMenuItems(Menus menus) {
		menus.setCategory(menus.getCategory());
		menus.setDescription(menus.getDescription());
		menus.setId(menus.getId());
		menus.setImage(menus.getImage());
		menus.setPrice(menus.getPrice());
		menus.setTitle(menus.getTitle());

		System.out.println(menus);
		menuRepository.save(menus);
		return menus;
	}

	@Override
	public ResponseEntity<List<Menus>> getMenuItems() {

		List<Menus> listMenus = menuRepository.findAll();

		return new ResponseEntity<List<Menus>>(listMenus, HttpStatus.OK);
	}

	@Override
	public Menus getMenuCategory(String category) {
		Menus menusCategory = menuRepository.findByCategory(category);
		return menusCategory;
	}

}
