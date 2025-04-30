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

@WebServlet("/updateUser")
public class UpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Logger instance
    private static final Logger logger = Logger.getLogger(UpdateController.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();
        String userIdParam = request.getParameter("userId");

        logger.log(Level.INFO, "Update request received for user ID: {0}", userIdParam);

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                logger.log(Level.INFO, "Fetching user details for ID: {0}", userId);
                
                RegisterModel model = userService.findUserById(userId);
                if (model != null) {
                    logger.log(Level.INFO, "User found for ID: {0}", userId);
                    request.setAttribute("model", model);
                    request.getRequestDispatcher("updateUser.jsp").forward(request, response);
                } else {
                    logger.log(Level.WARNING, "No user found for ID: {0}", userId);
                    response.getWriter().println("User not found.");
                }
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid user ID format: {0}", userIdParam);
                response.getWriter().println("Invalid user ID.");
            }
        } else {
            logger.log(Level.WARNING, "No user ID provided in request.");
            response.getWriter().println("User ID is required.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();
        
        String userIdParam = request.getParameter("userId");
        String uName = request.getParameter("userName");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        long mobileNo = Long.parseLong(request.getParameter("mobileNo"));
        String registerDate = request.getParameter("registerDate");
        String pmailId = request.getParameter("pmailId");

        logger.log(Level.INFO, "Update process started for user ID: {0}", userIdParam);

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);

                RegisterModel model = new RegisterModel();
                model.setUserId(userId);
                model.setUserName(uName);
                model.setEmail(email);
                model.setAddress(address);
                model.setMobileNo(mobileNo);
                model.setRegisterDate(registerDate);
                model.setPmailId(pmailId);

                logger.log(Level.INFO, "Updating user details for ID: {0}", userId);
                
                boolean success = userService.updateUser(model);
                if (success) {
                    logger.log(Level.INFO, "User updated successfully for ID: {0}", userId);
                    response.sendRedirect("register"); // Redirect to user list after update
                } else {
                    logger.log(Level.WARNING, "Failed to update user with ID: {0}", userId);
                    response.getWriter().println("Failed to update user.");
                }
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid user ID format: {0}", userIdParam);
                response.getWriter().println("Invalid user ID.");
            }
        } else {
            logger.log(Level.WARNING, "User ID is required but not provided in request.");
            response.getWriter().println("User ID is required.");
        }
    }
}
