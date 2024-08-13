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

import com.hrm.Models.RegisterModel;
import com.hrm.Models.Topic;
import com.hrm.Service.UserService;
import com.hrm.Service.UserServiceImpl;

@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String uName = request.getParameter("userName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		long mobileNo = Long.parseLong(request.getParameter("mobileNo"));

		String topic = request.getParameter("topic");
		HashSet<Topic> topics = new HashSet<Topic>();
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
		System.out.println(model);
		// create a object for navigate service
		UserService userService = new UserServiceImpl();
		int resisterValue = userService.registerUser(model);
		//System.out.println(resisterValue);
		response.sendRedirect("login.jsp");
		// request.getRequestDispatcher("register").forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		UserService userService = new UserServiceImpl();
		RegisterModel model = new RegisterModel();
		List<RegisterModel> alluser = (List<RegisterModel>) userService.finduser(model);
		//System.out.println(alluser);
		request.setAttribute("alluser", alluser);
		request.getRequestDispatcher("alluser.jsp").forward(request, response);
	}


}
