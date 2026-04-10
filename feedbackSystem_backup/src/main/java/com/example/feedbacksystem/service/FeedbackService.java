package com.example.feedbacksystem.service;

import com.example.feedbacksystem.entity.Feedback;
import com.example.feedbacksystem.entity.User;
import com.example.feedbacksystem.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getUserFeedback(User user) {
        return feedbackRepository.findByUser(user);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // ✅ NEW METHOD (IMPORTANT)
    public Feedback getById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}