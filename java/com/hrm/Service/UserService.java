package com.hrm.Service;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;

import com.hrm.Models.RegisterModel;
import com.hrm.Models.UserLogin;

public interface UserService {
	List<RegisterModel> finduser(RegisterModel model);

	int registerUser(RegisterModel model);

	boolean deleteUser(int userId);

	RegisterModel findUserById(int userId);

	boolean updateUser(RegisterModel user);

	RegisterModel searchUsers(String searchTerm);

	RegisterModel login(String email, String password, String userName);

	void updateLoginTime(String email, String loginTime);

	void updateLogoutTime(String email, String logoutTime);

}
