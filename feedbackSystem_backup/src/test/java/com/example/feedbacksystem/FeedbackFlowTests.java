package com.example.feedbacksystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FeedbackFlowTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void loginPageLoads() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void feedbackCanBeAdded() throws Exception {
        mockMvc.perform(post("/add-feedback")
                        .param("content", "Test feedback"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }
}
