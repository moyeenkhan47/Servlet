package com.hrm.Dao;

import java.util.List;

import com.hrm.Models.RegisterModel;
import com.hrm.Models.UserLogin;

public interface RegisterDao {

	int registerUser(RegisterModel model);

	List<RegisterModel> finduser(RegisterModel user);

	boolean deleteUser(int userId);

	boolean updateUser(RegisterModel user);

	RegisterModel findUserById(int userId);

	RegisterModel login(String email, String password,String userName,long mobileNo);

}
