package com.javalavas.db;
import java.sql.*;


public class Update 
{
	static final String db = "java_lava_db";
	static final String table = "User";
	
	
	/*
	public static void main(String args[]) throws SQLException
	{
		Connection myConnection = Connect.getConnection();
		registerUser(myConnection,"neewUser","password", "first", "last", "166 my street ", "suite 69", "city", "state", 60188,
				"6309991111", "6309992222", "6309993333", "company", "branch",
				2, "more info");
		
	}
	*/
	
	public static boolean updateUser(Connection con, String userName, String password, String fName, 
			String lName, String address1, String address2, String city, String state, String zip,
			String phoneHome, String phoneCell, String phoneOffice, String companyName, String branch,
			int foodId, String adtInfo)
	{
		
		
		try {
			
			
								
			 Statement statement = con.createStatement();
			 String query = "UPDATE "  + db + "." + table + "\n" 
					 + "SET Username = " + userName + ", Password = " + password + ", FirstName = "+ fName + ", LastName = " 
					 + lName + ", Address1 = " +  address1 + ", Address2 = " + address2 + ", City = "+ city +
					 ", State = " + state + ", Zip = " + zip + ", PhoneHome = " + phoneHome + ", PhoneCell = " + phoneCell + 
					 ", PhoneOffice = " + phoneOffice + ", CompanyName = " + companyName + ", BranchLocation = " + branch + 
					 ", Food_ID = " + foodId + ", AdditionalInfo = " + adtInfo + 
					 "WHERE " + db + "." + table + ".Username = " + userName;
			 statement.executeUpdate(query);
			 return true;
			
		} catch (SQLException e) {
			System.out.println("An error has ocurred");
			e.printStackTrace();
		}
		return false;
		
	}
}