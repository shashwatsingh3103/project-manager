package com.springboot.projectmanager.service;

import javax.sound.sampled.AudioFormat.Encoding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class emailserviceimpl implements emailservice {

	
	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public void sendemail(String email, String link) throws Exception {
		
		MimeMessage message= javaMailSender.createMimeMessage();
		MimeMessageHelper helper= new  MimeMessageHelper(message , "utf-8");
		String subject = "join project invation";
		String text = "click link to join project :" +link;
		helper.setSubject(subject);
		helper.setText(text, true);
		helper.setTo(email);
		 try {
			javaMailSender.send(message);
		} catch (Exception e) {
			
			throw new Exception("failed to send email") ;
		}
		
	}

}
