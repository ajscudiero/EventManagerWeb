package com.javalavas.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.mysql.jdbc.Statement;

public class UserHelper 
{
	static final String tableName = "User";
	public static int getID(Connection con,String username)
	{
		String query = "Select Id From " + tableName +" Where " + tableName + ".Username = '" + username + "';";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet results = stmt.executeQuery(query);
			if(results.next()){
				return results.getInt("ID");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
}