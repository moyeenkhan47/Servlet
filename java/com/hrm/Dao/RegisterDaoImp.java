package com.hrm.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hrm.Models.RegisterModel;
import com.hrm.util.DbConnection;

public class RegisterDaoImp implements RegisterDao {

    // User registration
    @Override
    public int registerUser(RegisterModel model) {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        int row = 0;

        try {
            myConn = DbConnection.getConnection();
            String sql = "INSERT INTO registeruser(userName, email, address, mobileNo, topic, registerDate, pmailId, password) VALUES (?,?,?,?,?,?,?,?)";
            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, model.getUserName());
            myStmt.setString(2, model.getEmail());
            myStmt.setString(3, model.getAddress());
            myStmt.setLong(4, model.getMobileNo());
            myStmt.setString(5, "java");  // Assuming "java" is the topic, this should ideally come from the model
            myStmt.setString(6, model.getRegisterDate());
            myStmt.setString(7, model.getPmailId());
            myStmt.setString(8, model.getPassword());

            row = myStmt.executeUpdate();

            if (row > 0) {
                System.out.println("Inserted records successfully");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(myConn, myStmt, null);
        }

        return row;
    }

    // Find all users
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
                user1.setLoginTime(myRs.getString("logintime"));
                users.add(user1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeResources(myConn, myStmt, myRs);
        }

        return users;
    }

    // Delete user by ID
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
        } finally {
            closeResources(myConn, myStmt, null);
        }

        return success;
    }

    // Update user by ID
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(myConn, myStmt, null);
        }

        return updated;
    }

    // Find user by ID
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
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(myConn, myStmt, myRs);
        }
        return model;
    }

    // User login using email and password
    @Override
    public RegisterModel login(String email, String password,String userName) {
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
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(myConn, myStmt, myRs);
        }
        return login;
    }

    // Update login time
    @Override
    public void updateLoginTime(String email, String loginTime) {
        Connection myConn = null;
        PreparedStatement myStmt = null;

        try {
            myConn = DbConnection.getConnection();
            String query = "UPDATE registeruser SET loginTime = ? WHERE email = ?";
            myStmt = myConn.prepareStatement(query);
            myStmt.setString(1, loginTime);
            myStmt.setString(2, email);
            myStmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(myConn, myStmt, null);
        }
    }
    
   
   

	@Override
	public void updateLogoutTime(String email, String logoutTime) {
		// TODO Auto-generated method stub
		 Connection myConn = null;
	        PreparedStatement myStmt = null;

	        try {
	            myConn = DbConnection.getConnection();
	            String query = "UPDATE registeruser SET logoutTime = ? WHERE email = ?";
	            myStmt = myConn.prepareStatement(query);
	            myStmt.setString(1, logoutTime);
	            myStmt.setString(2, email);
	            myStmt.executeUpdate();
	        } catch (ClassNotFoundException | SQLException e) {
	            e.printStackTrace();
	        } finally {
	            closeResources(myConn, myStmt, null);
	        }	
	}
	 // Utility method to close resources
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
}
