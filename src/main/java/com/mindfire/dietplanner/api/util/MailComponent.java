package com.mindfire.dietplanner.api.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailComponent {
    
    @Autowired
    private JavaMailSender mailSender;

    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }

    public void sendEmail() throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setTo("subhankardas831@gmail.com");
        helper.setText("How are you?");
        helper.setSubject("Hi");
        
        mailSender.send(message);
    }
}