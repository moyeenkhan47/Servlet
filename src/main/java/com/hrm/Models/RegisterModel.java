package com.hrm.Models;

import java.util.Set;

public class RegisterModel {

	
	private int userId;
	private String userName; 
	private String email;  
	private String address;
	private long mobileNo;
	private Set<Topic> topic;
	private String registerDate;  
	private String pmailId;
	
	public RegisterModel() {
		super();
	}
	public RegisterModel(int userId, String userName, String email, String address, long mobileNo, Set<Topic> topic,
			String registerDate, String pmailId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.address = address;
		this.mobileNo = mobileNo;
		this.topic = topic;
		this.registerDate = registerDate;
		this.pmailId = pmailId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Set<Topic> getTopic() {
		return topic;
	}
	public void setTopic(Set<Topic> topic) {
		this.topic = topic;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public String getPmailId() {
		return pmailId;
	}
	public void setPmailId(String pmailId) {
		this.pmailId = pmailId;
	}
	@Override
	public String toString() {
		return "RegisterModel [userId=" + userId + ", userName=" + userName + ", email=" + email + ", address="
				+ address + ", mobileNo=" + mobileNo + ", topic=" + topic + ", registerDate=" + registerDate
				+ ", pmailId=" + pmailId + "]";
	}
	
	
	
	
}
