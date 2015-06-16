package com.javalavas.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect 
{
	public static Connection getConnection() throws SQLException {

	    Connection conn = null;
	    
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
		
	    conn = DriverManager.getConnection(
               "jdbc:mysql://eventdb.ctnvlbomlqp9.us-west-2.rds.amazonaws.com",
               "master","javalava");	   
	    System.out.println("Connected to database");
       	    
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		
	    	e.printStackTrace();
		}
	    
	    return conn;
	}
}