package com.demo.hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * REST controller exposing Hello World endpoints.
 *
 * <p>Endpoints:
 * <ul>
 *   <li>GET /api/hello          → generic greeting</li>
 *   <li>GET /api/hello/{name}   → personalised greeting</li>
 * </ul>
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    /**
     * Returns a generic "Hello, World!" greeting.
     *
     * @return 200 OK with greeting payload
     */
    @GetMapping("/hello")
    public ResponseEntity<Map<String, String>> hello() {
        return ResponseEntity.ok(buildResponse("World"));
    }

    /**
     * Returns a personalised greeting for the given name.
     *
     * @param name the name to greet
     * @return 200 OK with personalised greeting payload
     */
    @GetMapping("/hello/{name}")
    public ResponseEntity<Map<String, String>> helloName(
            @PathVariable String name) {

        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(buildResponse(name));
    }

    // ── private helpers ──────────────────────────────────────────

    private Map<String, String> buildResponse(String name) {
        return Map.of(
                "message",   "Hello, " + name + "!",
                "timestamp", LocalDateTime.now().toString(),
                "status",    "success"
        );
    }
}
