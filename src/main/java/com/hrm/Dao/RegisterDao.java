package com.hrm.Dao;

import java.util.List;

import com.hrm.Models.RegisterModel;



public interface RegisterDao {

	int registerUser(RegisterModel model);
	List<RegisterModel> finduser(RegisterModel user);
	boolean deleteUser(int userId);
	boolean updateUser(RegisterModel user);
	RegisterModel findUserById(int userId);
	
}
