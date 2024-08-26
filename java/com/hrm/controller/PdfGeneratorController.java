package com.hrm.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.hrm.Models.UserSessionInfo;
import com.hrm.util.DbConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/generatePdf")
public class PdfGeneratorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Fetch all user session info from the database
            List<UserSessionInfo> sessionInfoList = getAllSessionInfo();

            if (sessionInfoList.isEmpty()) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No session data found for users");
                return;
            }

            // Generate a single PDF containing all user reports
            ByteArrayOutputStream baos = generateCombinedPdf(sessionInfoList);

            // Send the PDF as an email attachment
            sendEmailWithAttachment("combined_report", baos.toByteArray());

            // Optionally, send the PDF as a response to the client
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=login_logout_report_combined.pdf");
            try (OutputStream out = response.getOutputStream()) {
                baos.writeTo(out);
                out.flush();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Error retrieving session data from database", e);
        }
    }

    public List<UserSessionInfo> getAllSessionInfo() throws SQLException, ClassNotFoundException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        List<UserSessionInfo> sessionInfoList = new ArrayList<>();

        try {
            myConn = DbConnection.getConnection();
            String query = "SELECT userName, loginTime, logoutTime FROM registeruser";
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery(query);

            while (myRs.next()) {
                UserSessionInfo sessionInfo = new UserSessionInfo();
                sessionInfo.setUserName(myRs.getString("userName"));
                sessionInfo.setLoginTime(myRs.getString("loginTime"));
                sessionInfo.setLogoutTime(myRs.getString("logoutTime"));
                sessionInfoList.add(sessionInfo);
            }
        } finally {
            if (myRs != null) myRs.close();
            if (myStmt != null) myStmt.close();
            if (myConn != null) myConn.close();
        }

        return sessionInfoList;
    }

  
    public ByteArrayOutputStream generateCombinedPdf(List<UserSessionInfo> sessionInfoList)
    		throws ServletException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add the current date and title at the top of the page
            String currentDate = new java.text.SimpleDateFormat("dd-MM-yyyy").format(new java.util.Date());
            Paragraph dateParagraph = new Paragraph("Date: " + currentDate);
            dateParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(dateParagraph);

            Paragraph titleParagraph = new Paragraph("Students Daily Login and Logout Time Report");
            titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
            titleParagraph.setSpacingBefore(10); // Add space before the title
            titleParagraph.setSpacingAfter(10);  // Add space after the title
            document.add(titleParagraph);

            // Create a table with 4 columns
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100); // Set table width to 100% of page width

            // Add table headers and center-align them
            table.addCell(createCenterAlignedCell("Serial No."));
            table.addCell(createCenterAlignedCell("User Name"));
            table.addCell(createCenterAlignedCell("Login Time"));
            table.addCell(createCenterAlignedCell("Logout Time"));

            // Add rows to the table with center-aligned cells
            int serialNo = 1;
            for (UserSessionInfo sessionInfo : sessionInfoList) {
                table.addCell(createCenterAlignedCell(String.valueOf(serialNo++)));
                table.addCell(createCenterAlignedCell(sessionInfo.getUserName()));
                table.addCell(createCenterAlignedCell(sessionInfo.getLoginTime()));
                table.addCell(createCenterAlignedCell(sessionInfo.getLogoutTime()));
            }

            // Add the table to the document
            document.add(table);

            document.close();
        } catch (DocumentException e) {
            throw new ServletException("Error generating PDF", e);
        }
        return baos;
    }

    private PdfPCell createCenterAlignedCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        return cell;
    }


    public static void sendEmailWithAttachment(String reportName, byte[] pdfBytes) throws ServletException {
        // Set email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Replace with your SMTP server
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a session with an authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("chaudharymoin21@gmail.com", "uflb fppg hhit mena");
            }
        });

        try {
            // Create a message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("chaudharymoin21@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("moyeenkhan47@gmail.com")); 
            message.setSubject("Combined Login and Logout Report");

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Please find attached the combined login and logout report.");

            // Create the attachment part
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new ByteArrayDataSource(pdfBytes, "application/pdf");
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(reportName + ".pdf");

            // Combine message and attachment into a multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);

            // Set the complete message parts
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();  // Log the error
            throw new ServletException("Error sending email with attachment", e);
        }
    }
}
