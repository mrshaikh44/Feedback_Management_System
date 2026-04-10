package com.example.feedbacksystem.repository;

import com.example.feedbacksystem.entity.Feedback;
import com.example.feedbacksystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByUser(User user);
}