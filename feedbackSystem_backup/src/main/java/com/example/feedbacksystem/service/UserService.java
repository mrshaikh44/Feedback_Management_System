package com.example.feedbacksystem.service;

import com.example.feedbacksystem.entity.User;
import com.example.feedbacksystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register User
    public User registerUser(User user) {
        user.setRole("USER"); // default role
        return userRepository.save(user);
    }

    // Login User
    public User loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public User getOrCreateDemoUser() {
        return userRepository.findByUsername("demo")
                .orElseGet(() -> {
                    User user = new User();
                    user.setUsername("demo");
                    user.setEmail("demo@mail.com");
                    user.setPassword("123");
                    user.setRole("USER");
                    return userRepository.save(user);
                });
    }
}
