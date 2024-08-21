package com.hrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hrm.Models.RegisterModel;
import com.hrm.Models.Topic;
import com.hrm.Service.UserService;
import com.hrm.Service.UserServiceImpl;

@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String uName = request.getParameter("userName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		long mobileNo = 0;
		try {
			mobileNo = Long.parseLong(request.getParameter("mobileNo"));
		} catch (NumberFormatException e) {
			out.println("Invalid mobile number format.");
			return;
		}

		String topic = request.getParameter("topic");
		HashSet<Topic> topics = new HashSet<>();
		topics.add(new Topic(topic));
		String registerDate = request.getParameter("registerDate");
		String pmailId = request.getParameter("pmailId");
		String password = request.getParameter("password");
		

		RegisterModel model = new RegisterModel();
		model.setUserName(uName);
		model.setEmail(email);
		model.setAddress(address);
		model.setMobileNo(mobileNo);
		model.setTopic(topics);
		model.setRegisterDate(registerDate);
		model.setPmailId(pmailId);
		model.setPassword(password);

		HttpSession session = request.getSession(false);
		model.setLoginTime(session.getAttribute("loginTime"));
		model.setLogoutTime(session.getAttribute("logoutTime"));

		// create a object for navigate service
		UserService userService = new UserServiceImpl();
		int registerValue = userService.registerUser(model);
		if (registerValue > 0) {
			response.sendRedirect("login.jsp");
		} else {
			out.println("User registration failed.");
			// Optionally, forward back to registration with an error message
			// request.getRequestDispatcher("register.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		UserService userService = new UserServiceImpl();
		List<RegisterModel> userList = userService.finduser(new RegisterModel());

		request.setAttribute("alluser", userList);
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}
}
