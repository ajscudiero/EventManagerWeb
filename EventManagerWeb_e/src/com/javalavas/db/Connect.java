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
               "jdbc:mysql://sql3.freemysqlhosting.net/sql380927",
               "sql380927","gX4*jY9*");	   
	    System.out.println("Connected to database");
       	    
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		
	    	e.printStackTrace();
		}
	    
	    return conn;
	}
	
	public static Connection getAmazonConnection() throws SQLException {

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