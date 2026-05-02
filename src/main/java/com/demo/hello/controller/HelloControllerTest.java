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

/**
 * Slice tests for {@link HelloController}.
 * Uses @WebMvcTest — loads only the web layer (fast, no full context).
 * JaCoCo instruments these runs and produces the XML report consumed by SonarCloud.
 */
@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // ── /api/hello ────────────────────────────────────────────────

    @Test
    @DisplayName("GET /api/hello → 200 with 'Hello, World!' message")
    void helloReturnsWorldGreeting() throws Exception {
        mockMvc.perform(get("/api/hello")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message", is("Hello, World!")))
                .andExpect(jsonPath("$.status",  is("success")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    // ── /api/hello/{name} ─────────────────────────────────────────

    @Test
    @DisplayName("GET /api/hello/Chinmaya → 200 with personalised greeting")
    void helloNameReturnsPersonalisedGreeting() throws Exception {
        mockMvc.perform(get("/api/hello/Chinmaya")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello, Chinmaya!")))
                .andExpect(jsonPath("$.status",  is("success")));
    }

    @Test
    @DisplayName("GET /api/hello/DevOps → 200 with personalised greeting")
    void helloDevOpsGreeting() throws Exception {
        mockMvc.perform(get("/api/hello/DevOps")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Hello, DevOps!")));
    }
}
