package com.hrm.controller;

import java.io.IOException;
import java.util.Random;
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
		String uName = request.getParameter("userName");
		String mobileNoStr = request.getParameter("mobileNo");
		

		UserService userService = new UserServiceImpl();
		long mobileNo = Long.parseLong(mobileNoStr);
		RegisterModel login = userService.login(email, password, uName, mobileNo);
		System.out.println();
		if (login != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", login.getUserName());
			session.setAttribute("email", login.getEmail());
			//session.setAttribute("mobileNo", login.getMobileNo());
			session.setAttribute("login", login);
			session.setMaxInactiveInterval(900); // Set session timeout to 900 seconds (15 minutes)
			session.setAttribute("loginTime", System.currentTimeMillis());

			/*
			 * // Generate OTP String otp = generateOTP(); session.setAttribute("otp", otp);
			 * 
			 * // Send OTP via SMS Long mobileNumber = String.valueOf(mobileNo); // Assume mobile
			 * number is part of RegisterModel TwilioUtil.sendOtp(mobileNo, otp);
			 */
			response.sendRedirect("register"); // Redirect to OTP verification page
		} else {
			request.setAttribute("errorMessage", "Invalid username or email");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/*
	 * private String generateOTP() { Random random = new Random(); int otp = 100000
	 * + random.nextInt(900000); // Generate 6-digit OTP return String.valueOf(otp);
	 * }
	 */
}
