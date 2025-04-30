package com.hrm.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hrm.Service.UserService;
import com.hrm.Service.UserServiceImpl;

@WebServlet("/deleteUser")
public class DeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Logger instance
    private static final Logger logger = Logger.getLogger(DeleteController.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();

        String userIdParam = request.getParameter("id");
        logger.log(Level.INFO, "Delete request received for user ID: {0}", userIdParam);

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                logger.log(Level.INFO, "Attempting to delete user with ID: {0}", userId);
                
                boolean success = userService.deleteUser(userId);
                if (success) {
                    logger.log(Level.INFO, "User with ID {0} deleted successfully.", userId);
                    response.sendRedirect("register"); // Redirect to the list page after deletion
                } else {
                    logger.log(Level.WARNING, "Failed to delete user with ID {0}.", userId);
                    response.getWriter().println("Failed to delete user.");
                }
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid user ID format: {0}", userIdParam);
                response.getWriter().println("Invalid user ID.");
            }
        } else {
            logger.log(Level.WARNING, "No user ID provided in the request.");
            response.getWriter().println("User ID is required.");
        }
    }
}
