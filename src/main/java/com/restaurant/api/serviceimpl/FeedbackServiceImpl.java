package com.restaurant.api.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restaurant.api.entity.Feedback;
import com.restaurant.api.repository.FeedbackRepository;
import com.restaurant.api.service.FeedbackService;
import com.restaurant.api.util.ConstantUtil;

@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	FeedbackRepository feedbackRepsoitory;
	
	@Autowired
	Environment environment;
	
	@Override
	public ResponseEntity<?> saveFeedback(Feedback feedback) {
		
		feedbackRepsoitory.save(feedback);
		return ResponseEntity.ok(environment.getProperty(ConstantUtil.FEEDBACKSUBMITTED));
	}

	@Override
	public List<Feedback> saveFeedback(String emailid) {
		List<Feedback> getFeedback=feedbackRepsoitory.findAll();
		return getFeedback;
	}

	
}
