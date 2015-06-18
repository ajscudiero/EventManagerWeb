package com.javalavas.db;
import java.sql.*;
import java.util.Properties;

public class SignIn 
{
	/*static String user = "user";
	static String pass = "pass";*/
	/*public static void main(String[] args) throws SQLException
	{
		Connection myConnection = Connect.getConnection();
		
		logIn(myConnection,user,pass);
		
	}*/
	static String table = "User";
	public static boolean logIn(Connection conn, String username, String password )
	{
		
		try {
		    ResultSet results;
		    Statement statement = conn.createStatement();
		    String query = "SELECT * FROM " + table + " WHERE Username = '" + username + "' "
		    			+ " AND Password = '" + password + "' ;";
		    results = statement.executeQuery(query);
		    if(!results.next())
			{
		    	conn.close();
				return false;
			}
			else
			{
				conn.close();
				return true;
			}
		    
		    
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
	}
}
