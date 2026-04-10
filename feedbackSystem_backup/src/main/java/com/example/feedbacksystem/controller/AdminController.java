package com.example.feedbacksystem.controller;

import com.example.feedbacksystem.entity.Feedback;
import com.example.feedbacksystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("feedbackList", feedbackService.getAllFeedback());
        return "admin";
    }

    // ✅ DELETE
    @GetMapping("/admin/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/admin";
    }

    // ✅ VIEW
    @GetMapping("/admin/view/{id}")
    public String viewFeedback(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackService.getById(id);

        if (feedback == null) {
            return "redirect:/admin";
        }

        model.addAttribute("feedback", feedback);
        return "view";
    }

    // ✅ EDIT
    @GetMapping("/admin/edit/{id}")
    public String editFeedback(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackService.getById(id);

        if (feedback == null) {
            return "redirect:/admin";
        }

        model.addAttribute("feedback", feedback);
        return "edit";
    }
}