package com.javalavas.db;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class Mailer {
	
	
	public void sendAmdinEmail(String toAddress, String subject, String messageText){
		
		final String username = "eventamdinhelper@gmail.com";
        final String password = "eventgod";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eventamdinhelper@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toAddress));
            message.setSubject(subject);
            
            message.setText(messageText);

            Transport.send(message);

            System.out.println("Mail sent succesfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
		
        
		
	}

}
