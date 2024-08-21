package com.hrm.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/generatePdf")
public class PdfGeneratorController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Retrieve login and logout times from session
            String loginTime = (String) session.getAttribute("loginTime");
            String logoutTime = (String) session.getAttribute("logoutTime");

            if (loginTime == null || logoutTime == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Login or logout time not found in session");
                return;
            }

            // Create PDF
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                PdfWriter.getInstance(document, baos);
                document.open();
                document.add(new Paragraph("Login Time: " + loginTime));
                document.add(new Paragraph("Logout Time: " + logoutTime));
                document.close();
            } catch (DocumentException e) {
                throw new ServletException("Error generating PDF", e);
            }

            // Send the PDF as an attachment
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=login_logout_report.pdf");
            OutputStream out = response.getOutputStream();
            baos.writeTo(out);
            out.flush();
            out.close();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No session found");
        }
    }
}
