package com.hrm.util;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // You can perform actions when the session is created if needed
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // This method is called when the session is invalidated or times out
        HttpSession session = se.getSession();
        Long loginTime = (Long) session.getAttribute("loginTime");
        String userEmail = (String) session.getAttribute("email");

        if (loginTime != null && userEmail != null && !userEmail.isEmpty()) {
            long logoutTime = System.currentTimeMillis();
            long duration = logoutTime - loginTime; // Duration in milliseconds

            // Convert duration to hours, minutes, seconds
            long durationInSeconds = duration / 1000;
            long hours = durationInSeconds / 3600;
            long minutes = (durationInSeconds % 3600) / 60;
            long seconds = durationInSeconds % 60;

            String loginDuration = String.format("%02d hours, %02d minutes, %02d seconds", hours, minutes, seconds);

            // Send email
            EmailUtil.sendLogoutEmail(loginDuration, "Your Login Duration", userEmail);
        }
    }
}
