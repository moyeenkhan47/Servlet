package com.hrm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;

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

	// Logger instance
	private static final Logger logger = Logger.getLogger(UserController.class.getName());

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
			logger.log(Level.WARNING, "Invalid mobile number format: {0}", request.getParameter("mobileNo"));
			out.println("Invalid mobile number format.");
			return;
		}

		String topic = request.getParameter("topic");
		HashSet<Topic> topics = new HashSet<>();
		topics.add(new Topic(topic));
		String registerDate = request.getParameter("registerDate");
		String pmailId = request.getParameter("pmailId");
		String password = request.getParameter("password");

		// Log registration details
		logger.log(Level.INFO, "Registering user: {0}", uName);

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
		if (session != null) {
			model.setLoginTime((String) session.getAttribute("loginTime"));
			model.setLogoutTime((String) session.getAttribute("logoutTime"));
		}

		UserService userService = new UserServiceImpl();
		int registerValue = userService.registerUser(model);
		if (registerValue > 0) {
			logger.log(Level.INFO, "User registered successfully: {0}", uName);
			response.sendRedirect("login.jsp");
		} else {
			logger.log(Level.WARNING, "User registration failed: {0}", uName);
			out.println("User registration failed.");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		UserService userService = new UserServiceImpl();
		logger.log(Level.INFO, "Fetching all registered users.");

		List<RegisterModel> userList = userService.finduser(new RegisterModel()).stream()
				.filter(Objects::nonNull)
				.sorted(Comparator.comparing(RegisterModel::getUserId).reversed())
				.toList();

		logger.log(Level.INFO, "Number of users fetched: {0}", userList.size());

		request.setAttribute("alluser", userList);
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}
}
