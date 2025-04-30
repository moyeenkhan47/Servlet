package com.hrm.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrm.Models.RegisterModel;
import com.hrm.Service.UserService;
import com.hrm.Service.UserServiceImpl;

@WebServlet("/searchUser")
public class SearchController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Logger instance
    private static final Logger logger = Logger.getLogger(SearchController.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();

        // Get the search term and userId from the request
        String searchTerm = request.getParameter("searchTerm");
        String userIdParam = request.getParameter("userId");

        logger.log(Level.INFO, "Search request received. searchTerm: {0}, userId: {1}", new Object[] { searchTerm, userIdParam });

        RegisterModel model = null;

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                logger.log(Level.INFO, "Searching for user by ID: {0}", userId);
                
                model = userService.findUserById(userId);
                if (model != null) {
                    logger.log(Level.INFO, "User found for ID: {0}", userId);
                    request.setAttribute("model", model);
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                } else {
                    logger.log(Level.WARNING, "No user found for ID: {0}", userId);
                    response.getWriter().println("User not found.");
                }
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid user ID format: {0}", userIdParam);
                response.getWriter().println("Invalid user ID.");
            }
        } else if (searchTerm != null) {
            logger.log(Level.INFO, "Searching for users by search term: {0}", searchTerm);
            
            model = userService.searchUsers(searchTerm);
            if (model != null) {
                logger.log(Level.INFO, "Users found matching search term: {0}", searchTerm);
                request.setAttribute("model", model);
                request.getRequestDispatcher("search.jsp").forward(request, response);
            } else {
                logger.log(Level.WARNING, "No users found matching the search term: {0}", searchTerm);
                response.getWriter().println("No users found matching the search term.");
            }
        } else {
            logger.log(Level.WARNING, "No search term or user ID provided.");
            response.getWriter().println("Please provide a search term or user ID.");
        }
    }
}
