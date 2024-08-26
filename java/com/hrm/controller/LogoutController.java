package com.hrm.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrm.Service.UserService;
import com.hrm.Service.UserServiceImpl;
import com.hrm.util.EmailUtil;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LogoutController.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Get the session if it exists
        if (session != null) {
            // Log the session ID and the fact that logout was called
            LOGGER.info("Logout called for session: " + session.getId());

            synchronized (session) {
                // Check if the email has already been sent
                if (session.getAttribute("emailSent") == null) {
                    // Retrieve login time
                	
                	long loginTimeInMili= (long) session.getAttribute("loginTimeInMili");
                    
     
                    // Calculate duration
                    long logoutTimeMillis = System.currentTimeMillis();
                    long duration = logoutTimeMillis - loginTimeInMili; // Duration in milliseconds

                    // Convert duration to hours, minutes, seconds
                    long durationInSeconds = duration / 1000;
                    long hours = durationInSeconds / 3600;
                    long minutes = (durationInSeconds % 3600) / 60;
                    long seconds = durationInSeconds % 60;

                    String loginDuration = String.format("%02d hours, %02d minutes, %02d seconds", hours, minutes, seconds);

                    // Send email
                    String userEmail = (String) session.getAttribute("email");
                    System.out.println(userEmail);
                    if (userEmail != null) {
                        EmailUtil.sendLogoutEmail(loginDuration, "Your Login Duration", userEmail);
                        // Mark email as sent
                        session.setAttribute("emailSent", true);
                    } else {
                        LOGGER.warning("User email not found in session, unable to send logout email.");
                    }
                } else {
                    LOGGER.info("Email already sent for session: " + session.getId());
                }

                // Update logout time in database
                String email = (String) session.getAttribute("email");
                if (email != null) {
                    UserService userService = new UserServiceImpl();
                    
                    // Get the current time and format it as "HH:mm"
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                    String logoutTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
                    
                    session.setAttribute("logoutTime", logoutTime);
                    
                    // Update logout time in database
                    userService.updateLogoutTime(email, logoutTime);
                } else {
                    LOGGER.warning("User email not found in session, unable to update logout time.");
                }
            }

            session.invalidate(); // Invalidate the session
        }
        response.sendRedirect("index.jsp"); // Redirect to login page
    }
}
