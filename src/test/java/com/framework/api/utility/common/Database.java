package com.framework.api.utility.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	Connection conn;

	public void oracleConnection(String url, String username, String password)
			throws ClassNotFoundException, SQLException {
		// load Driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// create connection with oracle
		conn = DriverManager.getConnection(url, username, password);
	}

	public void executeQuery(String sql) throws SQLException {
		// create statement object
		//sql examples : select * from Employee where emp_id=? and emp_name=?
		PreparedStatement prpdstmt = conn.prepareStatement(sql); 
		
		//enter dynamic values in sql
//		prpdstmt.setInt(1, 123);
//		prpdstmt.setString(2, "Brijyot");
		
		// execute query
		ResultSet result=prpdstmt.executeQuery();
		while(result.next()) {
			System.out.println(result.getString(1) + result.getInt(2));
		}
	}

}
