package com.demo.hello.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/hello → 200 with Hello, World!")
    void helloReturnsWorldGreeting() throws Exception {
        mockMvc.perform(get("/api/hello")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello, World!")))
                .andExpect(jsonPath("$.status",  is("success")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    @DisplayName("GET /api/hello/Chinmaya → personalised greeting")
    void helloNameReturnsPersonalisedGreeting() throws Exception {
        mockMvc.perform(get("/api/hello/Chinmaya")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello, Chinmaya!")));
    }

    @Test
    @DisplayName("GET /api/hello/DevOps → personalised greeting")
    void helloDevOpsGreeting() throws Exception {
        mockMvc.perform(get("/api/hello/DevOps")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello, DevOps!")));
    }
}