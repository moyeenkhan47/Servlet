package com.hrm.controller;

import java.io.IOException;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();

        // Get the search term and userId from the request
        String searchTerm = request.getParameter("searchTerm");
        String userIdParam = request.getParameter("userId");

        RegisterModel model = null;

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                model = userService.findUserById(userId);
                if (model != null) {
                    request.setAttribute("model", model);
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                } else {
                    response.getWriter().println("User not found.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid user ID.");
            }
        } else if (searchTerm != null) {
            model = userService.searchUsers(searchTerm);
            if (model != null) {
                request.setAttribute("model", model);
                request.getRequestDispatcher("search.jsp").forward(request, response);
            } else {
                response.getWriter().println("No users found matching the search term.");
            }
        } else {
            response.getWriter().println("Please provide a search term or user ID.");
        }
    }
}
