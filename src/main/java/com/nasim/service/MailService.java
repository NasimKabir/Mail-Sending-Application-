package com.nasim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.nasim.model.Mail;
@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(Mail mail) throws MailException{
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		
		mailMessage.setFrom("mdkabir334@gmail.com");
		mailMessage.setTo(mail.getTo());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getBody());
		
		javaMailSender.send(mailMessage);
	}
}
