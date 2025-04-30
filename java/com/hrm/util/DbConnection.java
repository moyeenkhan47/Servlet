package com.hrm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection connection=null;
	
			Class.forName("com.mysql.cj.jdbc.Driver");  
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/myhiber","root","Abdul@123");  
			 
		return connection;
		
	}
	
}
