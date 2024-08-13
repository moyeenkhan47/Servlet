package com.hrm.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrm.util.EmailUtil;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the session if it exists
        if (session != null) {
            long loginTime = (Long) session.getAttribute("loginTime");
            long logoutTime = System.currentTimeMillis();
            long duration = logoutTime - loginTime; // Duration in milliseconds

            // Convert duration to hours, minutes, seconds
            long durationInSeconds = duration / 1000;
            long hours = durationInSeconds / 3600;
            long minutes = (durationInSeconds % 3600) / 60;
            long seconds = durationInSeconds % 60;

            String loginDuration = String.format("%02d hours, %02d minutes, %02d seconds", hours, minutes, seconds);

            // Send email
            String userEmail = (String) session.getAttribute("email"); // Get user email
            EmailUtil.sendLogoutEmail(loginDuration, "Your Login Duration", userEmail);

            session.invalidate(); // Invalidate the session
        }
        response.sendRedirect("login.jsp"); // Redirect to login page
    }
}
