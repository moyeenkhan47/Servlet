
package com.hrm.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

	public static void sendLogoutEmail(String duration, String subject, String to) {
		final String from = "chaudharymoin21@gmail.com"; // Sender's email ID
		final String password = "uflb fppg hhit mena"; // Password or app-specific password
		final String host = "smtp.gmail.com"; // SMTP server

		// Get system properties
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", host);
		props.setProperty("mail.smtp.port", "587"); // SMTP port
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Get the default Session object.
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try { // Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field
			message.setFrom(new InternetAddress(from));

			// Set To: header field
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Set the actual message
			message.setText("You have successfully logged out.\n\nTotal login duration: " + duration);

			// Send message
			Transport.send(message);
			System.out.println("Logout email sent successfully...");
		} catch (MessagingException mex) {
			System.err.println("Failed to send email: " + mex.getMessage());
			mex.printStackTrace();
		}
	}
}
