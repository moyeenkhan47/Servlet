package com.hrm.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.hrm.Models.RegisterModel;
import com.hrm.Models.UserLogin;
import com.hrm.util.DbConnection;

public class RegisterDaoImp implements RegisterDao {

	@Override
	public int registerUser(RegisterModel model) {
		// TODO Auto-generated method stub
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int row = 0;
		try {
			myConn = DbConnection.getConnection();
			myStmt = myConn.prepareStatement(
					"INSERT INTO registeruser(userName,email,address,mobileNo,topic,registerDate,pmailId,password) VALUES (?,?,?,?,?,?,?,?)");
			myStmt.setString(1, model.getUserName());
			myStmt.setString(2, model.getEmail());
			myStmt.setString(3, model.getAddress());
			myStmt.setLong(4, model.getMobileNo());
			myStmt.setString(5, "java");
			myStmt.setString(6, model.getRegisterDate());
			myStmt.setString(7, model.getPmailId());
	
			myStmt.setString(8, model.getpassword());
			row = myStmt.executeUpdate();
			if (row > 0) {
				System.out.println("insert records");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	@Override
	public List<RegisterModel> finduser(RegisterModel model) {
		List<RegisterModel> users = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = DbConnection.getConnection();
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("SELECT * FROM registeruser");

			while (myRs.next()) {
				RegisterModel user1 = new RegisterModel();
				user1.setUserId(myRs.getInt("userId"));
				user1.setUserName(myRs.getString("userName"));
				user1.setEmail(myRs.getString("email"));
				user1.setAddress(myRs.getString("address"));
				user1.setMobileNo(myRs.getLong("mobileNo"));
				user1.setRegisterDate(myRs.getString("registerDate"));
				user1.setPmailId(myRs.getString("pmailId"));

				users.add(user1);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (myRs != null)
					myRs.close();
				if (myStmt != null)
					myStmt.close();
				if (myConn != null)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace(); // Handle the exception properly
			}
		}

		return users;
	}

	@Override
	public boolean deleteUser(int userId) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		boolean success = false;

		try {
			myConn = DbConnection.getConnection();
			String sql = "DELETE FROM registeruser WHERE userId = ?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, userId);
			int rowsAffected = myStmt.executeUpdate();

			if (rowsAffected > 0) {
				success = true;
				System.out.println("Deleted user with ID: " + userId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean updateUser(RegisterModel user) {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		boolean updated = false;

		try {
			myConn = DbConnection.getConnection();
			String sql = "UPDATE registeruser SET userName=?, email=?, address=?, mobileNo=?, registerDate=?, pmailId=? WHERE userId=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, user.getUserName());
			myStmt.setString(2, user.getEmail());
			myStmt.setString(3, user.getAddress());
			myStmt.setLong(4, user.getMobileNo());
			myStmt.setString(5, user.getRegisterDate());
			myStmt.setString(6, user.getPmailId());
			myStmt.setInt(7, user.getUserId());

			int rowsAffected = myStmt.executeUpdate();
			updated = (rowsAffected > 0);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (myStmt != null)
					myStmt.close();
				if (myConn != null)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updated;
	}

	@Override
	public RegisterModel findUserById(int userId) {

		RegisterModel model = null;
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = DbConnection.getConnection();
			String sql = "SELECT * FROM registeruser WHERE userId=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, userId);
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
				if (myRs != null)
					myRs.close();
				if (myStmt != null)
					myStmt.close();
				if (myConn != null)
					myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	 @Override
    public RegisterModel login(String email, String password,String userName,long mobileNo) {
		 RegisterModel login = null;
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = DbConnection.getConnection();

            String sql = "SELECT * FROM registeruser WHERE email = ? AND password = ?";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, email);
            myStmt.setString(2, password);
            myRs = myStmt.executeQuery();

            if (myRs.next()) {
                login = new RegisterModel();
                login.setEmail(myRs.getString("email"));
                login.setPassword(myRs.getString("password"));
                login.setUserName(myRs.getString("userName"));
                login.setMobileNo(myRs.getLong("mobileNo"));
               
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
        return login;
    }
}