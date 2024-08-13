/*
 * package com.hrm.controller;
 * 
 * import java.io.IOException; import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.HttpServlet;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse; import
 * javax.servlet.http.HttpSession;
 * 
 * @WebServlet("/verifyOtp") public class OtpVerificationController extends
 * HttpServlet { private static final long serialVersionUID = 1L;
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException { String otp =
 * request.getParameter("otp"); HttpSession session = request.getSession(false);
 * // Get the session if it exists
 * 
 * if (session != null) { String sessionOtp = (String)
 * session.getAttribute("otp"); if (otp.equals(sessionOtp)) { // OTP is correct,
 * proceed with login response.sendRedirect("register"); // Redirect to home
 * page or dashboard } else { // OTP is incorrect
 * request.setAttribute("errorMessage", "Invalid OTP");
 * request.getRequestDispatcher("otpVerification.jsp").forward(request,
 * response); } } else { response.sendRedirect("login.jsp"); // Redirect to
 * login page if session is invalid } } }
 */