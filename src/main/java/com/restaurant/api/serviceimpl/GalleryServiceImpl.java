package com.restaurant.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.Gallery;
import com.restaurant.api.repository.GalleryRepository;
import com.restaurant.api.service.GalleryService;

@Service
public class GalleryServiceImpl implements GalleryService{

	
	@Autowired
	GalleryRepository galleryRepository;
	@Override
	public List<Gallery> getGalleryImages() {
		List<Gallery> getGalleryImages = galleryRepository.findAll();
		
		return getGalleryImages;
	}

}
