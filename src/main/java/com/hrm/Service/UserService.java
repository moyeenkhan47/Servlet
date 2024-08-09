package com.hrm.Service;

import java.util.List;

import com.hrm.Models.RegisterModel;

public interface UserService {
	List<RegisterModel> finduser(RegisterModel model);

	int registerUser(RegisterModel model);

	boolean deleteUser(int userId);

	RegisterModel findUserById(int userId);

	boolean updateUser(RegisterModel user);

	RegisterModel searchUsers(String searchTerm);
}
