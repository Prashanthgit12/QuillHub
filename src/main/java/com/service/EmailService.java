package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSubscriptionEmail(String subscriberEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("prashanth9392557522@gmail.com");
        message.setTo(subscriberEmail);
        message.setSubject("Welcome to QuillHub!");
        message.setText("Thank you for subscribing to QuillHub blogs!\n\nStay tuned for the latest updates.");

        mailSender.send(message);
    }
}