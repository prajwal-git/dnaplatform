package com.dna.backend.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


/**
 * @author Nischal Dahal
 * @since 2022/02/23
 * 
 **/


@Component
public class EmailSenderService {

	@Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String body) {
      
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("mailsentfromspringboot@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(body);
        emailSender.send(message);
       
    }
}
	

