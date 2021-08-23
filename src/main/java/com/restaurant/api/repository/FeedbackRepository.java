package com.restaurant.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurant.api.entity.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {
}
