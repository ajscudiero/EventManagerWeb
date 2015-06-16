package com.javalavas.db;
import java.sql.*;

public class EventHelper {
	
	public static void main(String[] args) throws SQLException
	{

		Connection conn = Connect.getConnection();
		//createEvent( conn, "Test Event 2", "2015-06-14 06:00:00", "2015-06-14 8:00:00", "1", "1",
		//		"Test Description", "1", "path/test/logo", "Austin", "TEST STATUS" );
		ResultSet events = getEventById( conn, "5" );
		while(events.next()){
			String title = events.getString("Title");
			String location = events.getString("Location");
			System.out.println( "Title: " + title + "\nLocation: " + location + "\n" );
		}
		
	}
	
	public static void createEvent( Connection conn, String title, String startDate, String endDate, String categoryId, String eventTypeId, 
			String description, String owner_id, String logo_path,
			String location, String status ) throws SQLException {
		
			try{
				Statement stmt = conn.createStatement();
				
				String query = "INSERT INTO java_lava_db.Event (Title, StartDate, EndDate, Category_ID, Type_ID, Description, Owner_ID, Logo_Path"
						+ ", Location, Status) VALUES ( '" + title + "', '" + startDate + "', '" + endDate + "', '" + categoryId + "', '" + eventTypeId +
						"', '" + description + "', '" + owner_id + "', '" + logo_path + "', '" + location + "', '" + status + "');";
			    stmt.executeUpdate(query);
			   
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	}
	
	//TODO: test this query 
	public static void updateEvent( Connection conn, String id,  String title, String startDate, String endDate, String categoryId, String eventTypeId, 
			String description, String owner_id, String logo_path,
			String location, String status ) {
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "UPDATE java_lava_db.Event "
					+ "SET Title='" + title + "', StartDate='" + startDate + "', EndDate='" + endDate + "', Category_ID='" + categoryId
					+ "', Type_ID='" + eventTypeId + "', Description='" + description + "', Owner_ID='" + owner_id + "', Logo_Path='" + 
					logo_path + "', Location='" + location + "', Status='" + status + "' " +
					"WHERE java_lava_db.Event.ID='" + id + "';";
		    stmt.executeUpdate(query);
		   
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
	}
	
	//TODO: test this method when connected to MySQL DB
	public static void registerForEvent( Connection conn, String eventId, String userId ){
			
		try{
			Statement stmt = conn.createStatement();
			
			String query = "INSERT INTO java_lava_db.Registration (Event_ID, User_ID) VALUES ( '" + eventId + "', '" + userId + "');";
		    stmt.executeUpdate(query);
		   
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
			
	
	public static ResultSet getRegisteredEvents( Connection conn, String userId ){
		
	ResultSet rs = null;
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM java_lava_db.Event "
					+ "INNER JOIN Registrations ON java_lava_db.Registrations.Event_Id=java_lava_db.Event.ID "
					+ "WHERE java_lava_db.Registrations.User_Id=" + userId +";";
			rs = stmt.executeQuery(query);
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//TODO: Test method when connected to db
	public static ResultSet getOwnedEvents( Connection conn, String userId ){
		
		ResultSet rs = null;
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM java_lava_db.Event "
					+ "WHERE java_lava_db.Event.Owner_Id='" + userId + "');";
			rs = stmt.executeQuery(query);
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public static ResultSet getAllEvents( Connection conn ){
		
		ResultSet rs = null;
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM java_lava_db.Event";
			rs = stmt.executeQuery(query);
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public static ResultSet getEventById( Connection conn, String id ){
		
		ResultSet rs = null;
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM java_lava_db.Event "
					+ "WHERE java_lava_db.Event.ID=" + id + ";";
			rs = stmt.executeQuery(query);
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	public static ResultSet getEventsInRange( Connection conn, String startDateTime, String endDateTime ){
		
	ResultSet rs = null;
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM java_lava_db.Event "
					+ "WHERE java_lava_db.Event.StartDate BETWEEN " + startDateTime + " AND " + endDateTime + ";";
			rs = stmt.executeQuery(query);
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	
	}
	
	
	
	
}