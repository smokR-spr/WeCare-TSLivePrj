package com.data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
	//To get DB connection
	public static Connection getCon() throws Exception  {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/wecare", "root", "root");
	}
}
