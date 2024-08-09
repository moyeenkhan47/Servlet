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

@WebServlet("/updateUser")
public class UpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        UserService userService = new UserServiceImpl();
        String userIdParam = request.getParameter("userId");

        if (userIdParam != null) {
            try {
                int userId = Integer.parseInt(userIdParam);
                RegisterModel model = userService.findUserById(userId);
                System.out.println(model);
                if (model != null) {
                    request.setAttribute("model", model);
                    request.getRequestDispatcher("updateUser.jsp").forward(request, response);
                } else {
                    response.getWriter().println("User not found.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid user ID.");
            }
        } else {
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
                
                boolean success = userService.updateUser(model);
                if (success) {
                    response.sendRedirect("register"); // Redirect to user list after update
                } else {
                    response.getWriter().println("Failed to update user.");
                }
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid user ID.");
            }
        } else {
            response.getWriter().println("User ID is required.");
        }
    }
}
