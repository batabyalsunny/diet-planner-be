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

	public boolean sendMailWithHtml(String mailTo, String subject, String text) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setTo(mailTo);
			helper.setSubject(subject);
			message.setContent(text, "text/html");

			mailSender.send(message);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

}