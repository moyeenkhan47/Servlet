/*
 * package com.hrm.util;
 * 
 * import java.util.Properties; import javax.mail.*; import
 * javax.mail.internet.*;
 * 
 * public class EmailUtil {
 * 
 * public static void sendLogoutEmail(String duration, String subject, String
 * to) { final String from = "chaudharymoin21@gmail.com"; // Sender's email ID
 * final String password = "uflb fppg hhit mena"; // Password or app-specific
 * password final String host = "smtp.gmail.com"; // SMTP server
 * 
 * // Get system properties Properties props = new Properties();
 * props.setProperty("mail.smtp.host", host);
 * props.setProperty("mail.smtp.port", "587"); // SMTP port
 * props.setProperty("mail.smtp.auth", "true");
 * props.setProperty("mail.smtp.starttls.enable", "true");
 * 
 * // Get the default Session object. Session session =
 * Session.getInstance(props, new Authenticator() { protected
 * PasswordAuthentication getPasswordAuthentication() { return new
 * PasswordAuthentication(from, password); } });
 * 
 * try { // Create a default MimeMessage object. MimeMessage message = new
 * MimeMessage(session);
 * 
 * // Set From: header field message.setFrom(new InternetAddress(from));
 * 
 * // Set To: header field message.addRecipient(Message.RecipientType.TO, new
 * InternetAddress(to));
 * 
 * // Set Subject: header field message.setSubject(subject);
 * 
 * // Set the actual message
 * message.setText("You have successfully logged out.\n\nTotal login duration: "
 * + duration);
 * 
 * // Send message Transport.send(message);
 * System.out.println("Logout email sent successfully..."); } catch
 * (MessagingException mex) { System.err.println("Failed to send email: " +
 * mex.getMessage()); mex.printStackTrace(); } } }
 */package com.hrm.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.ByteArrayOutputStream;
import java.util.Properties;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class EmailUtil {

    public static void sendPdfReport(String to) throws Exception {
        final String from = "chaudharymoin21@gmail.com"; // Sender's email ID
        final String password = "uflb fppg hhit mena"; // Password or app-specific password
        final String host = "smtp.gmail.com"; // SMTP server

        // Generate PDF
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, baos);
            document.open();
            document.add(new Paragraph("Sample PDF Report"));
            document.close();
        } catch (DocumentException e) {
            throw new Exception("Error generating PDF", e);
        }

        // Set up properties and session
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Create and send email
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject("Login/Logout Report");

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Please find attached the PDF report.");

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.setContent(baos.toByteArray(), "application/pdf");
        attachmentBodyPart.setFileName("login_logout_report.pdf");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachmentBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        System.out.println("PDF report sent successfully.");
    }
}
