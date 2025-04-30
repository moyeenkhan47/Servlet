package com.hrm.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminLogin")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Logger instance
    private static final Logger logger = Logger.getLogger(AdminController.class.getName());

    // Hardcoded credentials for demonstration purposes.
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();

        logger.log(Level.INFO, "Admin login attempt with username: {0}", username);

        if (validateCredentials(username, password)) {
            logger.log(Level.INFO, "Admin login successful for username: {0}", username);

            // Set session attribute to indicate admin is logged in
            HttpSession session = request.getSession();
            session.setAttribute("admin", username);
            session.setMaxInactiveInterval(30 * 60); // Session timeout in seconds (30 minutes)

            response.sendRedirect("register"); // Redirect to another page after login
        } else {
            logger.log(Level.WARNING, "Admin login failed for username: {0}", username);

            // Authentication failed
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
    }

    private boolean validateCredentials(String username, String password) {
        // In a real application, this method should check the credentials against a database or secure store.
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}
