/*
 * package com.hrm.util;
 * 
 * import com.mysql.cj.protocol.Message;
 * 
 * public class TwilioUtil {
 * 
 * // Your Account SID and Auth Token from twilio.com/console public static
 * final String ACCOUNT_SID = "your_account_sid"; // Replace with your Account
 * SID public static final String AUTH_TOKEN = "your_auth_token"; // Replace
 * with your Auth Token public static final String FROM_PHONE =
 * "your_twilio_phone_number"; // Replace with your Twilio phone number
 * 
 * static { TwilioUtil.init(ACCOUNT_SID, AUTH_TOKEN); }
 * 
 * public static void sendOtp(String to, String otp) { Message message =
 * Message.creator( new PhoneNumber(to), // To number new
 * PhoneNumber(FROM_PHONE), // From number "Your OTP for login is: " + otp) //
 * Message content .create();
 * 
 * System.out.println("OTP sent: " + message.getSid()); } }
 */