package com.hrm.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // You can add custom logic here if needed when a session is created
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        synchronized (se.getSession()) {
            // Check if the email has already been sent
            if (se.getSession().getAttribute("emailSent") == null) {
                long loginTime = (long) se.getSession().getAttribute("loginTime");
                long logoutTime = System.currentTimeMillis();
                long duration = logoutTime - loginTime; // Duration in milliseconds

                // Convert duration to hours, minutes, seconds
                long durationInSeconds = duration / 1000;
                long hours = durationInSeconds / 3600;
                long minutes = (durationInSeconds % 3600) / 60;
                long seconds = durationInSeconds % 60;

                String loginDuration = String.format("%02d hours, %02d minutes, %02d seconds", hours, minutes, seconds);

                // Send email
                String userEmail = (String) se.getSession().getAttribute("email"); // Get user email
                if (userEmail != null) {
                    EmailUtil.sendLogoutEmail(loginDuration, "Your Login Duration", userEmail);
                }

                // Mark email as sent
                se.getSession().setAttribute("emailSent", true);
            }
        }
    }
}
