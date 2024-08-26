package com.hrm.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrm.Models.RegisterModel;
import com.hrm.Service.UserService;
import com.hrm.Service.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserService userService = new UserServiceImpl();
        String userName = null;
		RegisterModel login = userService.login(email, password,userName);

        if (login != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userName", login.getUserName());
            session.setAttribute("email", login.getEmail());
            session.setAttribute("login", login);
            //session.setMaxInactiveInterval(900); // Set session timeout to 900 seconds (15 minutes)

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String loginTime = simpleDateFormat.format(new Date(System.currentTimeMillis()));
            session.setAttribute("loginTime", loginTime);
            Long loginTimeInMiliSecond=System.currentTimeMillis();
            session.setAttribute("loginTimeInMili", loginTimeInMiliSecond);

            userService.updateLoginTime(email, loginTime); // Update login time in the database

            response.sendRedirect("login");
        } else {
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        UserService userService = new UserServiceImpl();
        List<RegisterModel> userList = userService.finduser(new RegisterModel());

        request.setAttribute("alluser", userList);
        request.getRequestDispatcher("userdetails.jsp").forward(request, response);
    }
}
