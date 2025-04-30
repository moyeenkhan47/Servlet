package com.hrm.filter;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserAuthFilter implements Filter {

    private static final Logger logger = Logger.getLogger(UserAuthFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session.getAttribute("userName") != null) {
            chain.doFilter(request, response);
           
        } else {
            logger.log(Level.INFO, "Session expired or user not logged in.");
            request.getRequestDispatcher("/sessionExpired.jsp").forward(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Any initialization code if necessary
        logger.log(Level.INFO, "UserAuthFilter initialized.");
    }

    @Override
    public void destroy() {
        // Any cleanup code if necessary
        logger.log(Level.INFO, "UserAuthFilter destroyed.");
    }
}
