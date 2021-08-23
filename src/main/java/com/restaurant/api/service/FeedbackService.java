package com.restaurant.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.restaurant.api.entity.Feedback;

public interface FeedbackService {
	ResponseEntity<?> saveFeedback(Feedback feedback);

	List<Feedback> saveFeedback(String emailid);

}
