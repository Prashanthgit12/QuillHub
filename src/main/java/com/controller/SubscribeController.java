package com.controller;

import com.dto.SubscribeRequest;
import com.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // allow requests from frontend
public class SubscribeController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscribeRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required!");
        }

        try {
            emailService.sendSubscriptionEmail(request.getEmail());
            return ResponseEntity.ok("Subscribed successfully! Check your inbox.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send email. Try again later.");
        }
    }
}