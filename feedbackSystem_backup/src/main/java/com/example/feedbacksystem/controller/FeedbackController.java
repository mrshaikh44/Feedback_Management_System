package com.example.feedbacksystem.controller;

import com.example.feedbacksystem.entity.Feedback;
import com.example.feedbacksystem.entity.User;
import com.example.feedbacksystem.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Temporary user (since no login session implemented)
    private User tempUser = new User(2L, "test", "test@mail.com", "123", "USER");

    // ✅ Dashboard
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Feedback> list = feedbackService.getUserFeedback(tempUser);
        model.addAttribute("feedbackList", list);
        model.addAttribute("feedback", new Feedback());
        return "dashboard";
    }

    // ✅ Add Feedback (WITH DATE)
    @PostMapping("/add-feedback")
    public String addFeedback(@ModelAttribute Feedback feedback) {
        feedback.setUser(tempUser);

        // ✅ Set current date
        feedback.setDate(LocalDate.now());

        feedbackService.addFeedback(feedback);

        return "redirect:/dashboard";
    }

    // ✅ Delete Feedback
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return "redirect:/dashboard";
    }

    // ✅ Edit Page
    @GetMapping("/edit/{id}")
    public String editFeedback(@PathVariable Long id, Model model) {
        Feedback feedback = feedbackService.getById(id);

        if (feedback == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("feedback", feedback);
        return "edit";
    }

    // ✅ Update Feedback
    @PostMapping("/update")
    public String updateFeedback(@ModelAttribute Feedback feedback) {
        feedback.setUser(tempUser); // keep user
        feedback.setDate(LocalDate.now()); // optional: update date

        feedbackService.addFeedback(feedback);

        return "redirect:/dashboard";
    }
}