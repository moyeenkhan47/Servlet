package com.hrm.controller;

import java.io.IOException;
import java.util.List;

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

        // Get the search term from the request
        String searchTerm = request.getParameter("searchTerm");

        // Find users based on the search term
        RegisterModel model = userService.searchUsers(searchTerm);

        // Set the user as a request attribute
        request.setAttribute("model", model);

        // Forward to the search JSP page
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}


