package com.sharad.util;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	
	@Autowired
	   public JavaMailSender  mailSender;
	
	    public boolean sendEmail(File file) {
	    	boolean status = false;
	    	try {
	    		// logic to send email with attachment
	    		MimeMessage message = mailSender.createMimeMessage();
	    		MimeMessageHelper helpher = new MimeMessageHelper(message, true);
	    		
	    		helpher.setTo("sharadk4545@gmail.com");
	    		helpher.setSubject("Citizen Plan Report");
	    		helpher.setText("<h3>Please find the attached report of Citizen Plan<h3>",true);
	    		helpher.addAttachment(file.getName(), file);
	    		mailSender.send(message);
	    		status = true;
	    		
	    		
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
			return status;
	    }

}
