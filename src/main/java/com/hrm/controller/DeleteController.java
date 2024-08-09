package com.hrm.controller;

import java.io.IOException;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();
        
        String userIdParam = request.getParameter("id");

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                boolean success = userService.deleteUser(userId);
                if (success) {
                    response.sendRedirect("register"); // Redirect to list page after deletion
                } else {
                    response.getWriter().println("Failed to delete user.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid user ID.");
            }
        } else {
            response.getWriter().println("User ID is required.");
        }
    }
}
