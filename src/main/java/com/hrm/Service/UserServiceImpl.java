package com.hrm.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hrm.Dao.RegisterDao;
import com.hrm.Dao.RegisterDaoImp;
import com.hrm.Models.RegisterModel;
import com.hrm.Models.UserLogin;
import com.hrm.util.DbConnection;

public class UserServiceImpl implements UserService {

	@Override
	public int registerUser(RegisterModel model) {
		// TODO Auto-generated method stub
		// service nivigate to dao
		RegisterDao dao = new RegisterDaoImp();
		return dao.registerUser(model);
	}

	@Override
	public List<RegisterModel> finduser(RegisterModel model) {
		// TODO Auto-generated method stub
		RegisterDao dao = new RegisterDaoImp();
		return dao.finduser(model);
	}

	@Override
	public boolean deleteUser(int userId) {
		// TODO Auto-generated method stub
		RegisterDao dao = new RegisterDaoImp();
		return dao.deleteUser(userId);

	}

	@Override
	public RegisterModel findUserById(int userId) {
		RegisterDao registerDao = new RegisterDaoImp();
		return registerDao.findUserById(userId);
	}

	@Override
	public boolean updateUser(RegisterModel user) {
		RegisterDao registerDao = new RegisterDaoImp();
		return registerDao.updateUser(user);

	}

	@Override
	public RegisterModel searchUsers(String searchTerm) {
	    RegisterModel model = null;
	    Connection myConn = null;
	    PreparedStatement myStmt = null;
	    ResultSet myRs = null;

	    try {
	        myConn = DbConnection.getConnection();

	        // Determine if the searchTerm is a numeric ID or a username
	        String sql;
	        if (searchTerm.matches("\\d+")) {
	            // search by ID
	            sql = "SELECT * FROM registeruser WHERE userId = ?";
	            myStmt = myConn.prepareStatement(sql);
	            myStmt.setInt(1, Integer.parseInt(searchTerm));
	        } else {
	            // search by username
	            sql = "SELECT * FROM registeruser WHERE userName = ?";
	            myStmt = myConn.prepareStatement(sql);
	            myStmt.setString(1, searchTerm);
	        }

	        myRs = myStmt.executeQuery();

	        if (myRs.next()) {
	            model = new RegisterModel();
	            model.setUserId(myRs.getInt("userId"));
	            model.setUserName(myRs.getString("userName"));
	            model.setEmail(myRs.getString("email"));
	            model.setAddress(myRs.getString("address"));
	            model.setMobileNo(myRs.getLong("mobileNo"));
	            model.setRegisterDate(myRs.getString("registerDate"));
	            model.setPmailId(myRs.getString("pmailId"));
	            // Add code for topics if necessary
	        }
	    } catch (SQLException | ClassNotFoundException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (myRs != null) myRs.close();
	            if (myStmt != null) myStmt.close();
	            if (myConn != null) myConn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return model;
	}
	
	@Override
	public RegisterModel login(String email, String password, String userName, long mobileNo) {
		RegisterDao registerDao = new RegisterDaoImp();
		return registerDao.login(email,password,userName,mobileNo); 
	}
}
