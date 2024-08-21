package com.hrm.Dao;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import com.hrm.Models.RegisterModel;

public interface RegisterDao {

	int registerUser(RegisterModel model);

	List<RegisterModel> finduser(RegisterModel user);

	boolean deleteUser(int userId);

	boolean updateUser(RegisterModel user);

	RegisterModel findUserById(int userId);

	RegisterModel login(String email, String password, String userName);

	void updateLoginTime(String email, String loginTime);

	void updateLogoutTime(String email, String logoutTime);


}
