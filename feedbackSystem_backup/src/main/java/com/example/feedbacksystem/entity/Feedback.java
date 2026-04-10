package com.example.feedbacksystem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Feedback() {}

    public Feedback(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}