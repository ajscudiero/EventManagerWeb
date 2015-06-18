package com.javalavas.db;
import java.sql.*;

public class EventHelper {
	
	//private static String db = "java_lava_db";
	private static String db = "sql380927";
	
	public static void main(String[] args) throws SQLException
	{
		/*
		Connection conn = Connect.getConnection();
		createEvent( conn, "Test Event 2", "2015-06-14 06:00:00", "2015-06-14 8:00:00", "1", "1",
				"Test Description", "1", "path/test/logo", "Austin", "TEST STATUS" );
		ResultSet events = getEventById( conn, "5" );
		while(events.next()){
			String title = events.getString("Title");
			String location = events.getString("Location");
			System.out.println( "Title: " + title + "\nLocation: " + location + "\n" );
		}
		*/
		
	}
	
	public static void createEvent( Connection conn, String title, String startDate, String endDate, String eventTypeId, 
			String description, String owner_id, String logo_path,
			String location, String status ) throws SQLException {
		
			try{
				
				/*
				Statement stmt = conn.createStatement();
				
				String query = "INSERT INTO " + db + ".Event (Title, StartDate, EndDate, Category_ID, Type_ID, Description, Owner_ID, Logo_Path"
						+ ", Location, Status) VALUES ( '" + title + "', '" + startDate + "', '" + endDate + "', '" + categoryId + "', '" + eventTypeId +
						"', '" + description + "', '" + owner_id + "', '" + logo_path + "', '" + location + "', '" + status + "');";
						
				stmt.executeUpdate(query);
				*/
				
				PreparedStatement createEvent = null;
				
				String query = "INSERT INTO " + db + ".Event (Title, StartDate, EndDate, EventType_ID, Description, Owner_ID, Logo_Path"
						+ ", Location, Status) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			   createEvent = conn.prepareStatement( query );
			   
			   createEvent.setString( 1, title);
			   createEvent.setString( 2, startDate);
			   createEvent.setString( 3, endDate);
			   createEvent.setString( 4, eventTypeId);
			   createEvent.setString( 5, description);
			   createEvent.setString( 6, owner_id );
			   createEvent.setString( 7, logo_path);
			   createEvent.setString( 8, location);
			   createEvent.setString( 9, status);
			   createEvent.executeUpdate();
			   
			} catch ( SQLException e ) {
				e.printStackTrace();
			}
	}
	
	public static void deleteEvent( Connection conn, String eventId ){
		
		try{
			PreparedStatement deleteUser = null;
			String query = "DELETE FROM " + db + ".Event WHERE ID=?";
			deleteUser = conn.prepareStatement( query );
			deleteUser.setString( 1, eventId );
			deleteUser.executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
	
	//TODO: test this query 
	public static void updateEvent( Connection conn, String id,  String title, String startDate, String endDate, String eventTypeId, 
			String description, String ownerId, String logo_path,
			String location, String status ) {
		
		try{
			
			/*
			Statement stmt = conn.createStatement();
		
			String query = "UPDATE " + db + ".Event "
					+ "SET Title='" + title + "', StartDate='" + startDate + "', EndDate='" + endDate + "', Category_ID='" + categoryId
					+ "', Type_ID='" + eventTypeId + "', Description='" + description + "', Owner_ID='" + ownerId + "', Logo_Path='" + 
					logo_path + "', Location='" + location + "', Status='" + status + "' " +
					"WHERE java_lava_db.Event.ID='" + id + "';";
		    stmt.executeUpdate(query);
		    */
			
			PreparedStatement updateEvent = null;
			
			String query = "UPDATE " + db + ".Event "
					+ "SET Title=?, StartDate=?, EndDate=?, EventType_ID=?, Description=?, Owner_ID=?, Logo_Path=?, Location=?, Status=? "
					+ "WHERE " + db + ".Event.ID=?;";
			
			updateEvent = conn.prepareStatement( query );
			updateEvent.setString( 1, title );
			updateEvent.setString( 2, startDate );
			updateEvent.setString( 3, endDate );
			updateEvent.setString( 4, eventTypeId );
			updateEvent.setString( 5, description );
			updateEvent.setString( 6, ownerId );
			updateEvent.setString( 7, logo_path );
			updateEvent.setString( 8, location );
			updateEvent.setString( 9, status );
			updateEvent.setString( 10, id );
			updateEvent.executeUpdate();
		   
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		 
	}
	
	//TODO: test this method when connected to MySQL DB
	public static void registerForEvent( Connection conn, String eventId, String userId ){
			
		try{
			
			/*
			Statement stmt = conn.createStatement();
			
			
			String query = "INSERT INTO " + db + ".Registration (Event_ID, User_ID) VALUES ( '" + eventId + "', '" + userId + "');";
		    stmt.executeUpdate(query);
		    */
			
			PreparedStatement registerForEvent = null;
			String query = "INSERT INTO " + db + ".Registration (Event_ID, User_ID) VALUES (?, ?);";
			
			registerForEvent = conn.prepareStatement( query );
			registerForEvent.setString( 1, eventId );
			registerForEvent.setString( 2, userId );
			registerForEvent.executeUpdate();
		   
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
	}
			
	
	public static ResultSet getRegisteredEvents( Connection conn, String userId ){
		
	ResultSet rs = null;
		
		try{
			/*
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "INNER JOIN Registrations ON java_lava_db.Registrations.Event_Id=java_lava_db.Event.ID "
					+ "WHERE java_lava_db.Registrations.User_Id=" + userId +";";
			rs = stmt.executeQuery(query);
			*/
			
			PreparedStatement getRegisteredEvents = null;
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "INNER JOIN Registrations ON " + db + ".Registrations.Event_Id=" + db + ".Event.ID "
					+ "WHERE " + db + ".Registrations.User_Id=?;";
			
			getRegisteredEvents = conn.prepareStatement( query );
			getRegisteredEvents.setString( 1, userId );
			getRegisteredEvents.executeQuery();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	//TODO: Test method when connected to db
	public static ResultSet getOwnedEvents( Connection conn, String userId ){
		
		ResultSet rs = null;
		
		try{
			
			/*
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "WHERE java_lava_db.Event.Owner_Id='" + userId + "');";
			rs = stmt.executeQuery(query);
			*/
			
			PreparedStatement getOwnedEvents = null;
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "WHERE " + db + ".Event.Owner_Id=?);";
			
			getOwnedEvents = conn.prepareStatement( query );
			getOwnedEvents.setString( 1, userId );
			getOwnedEvents.executeQuery();
				
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	}

	public static ResultSet getAllEvents( Connection conn ){
		
		ResultSet rs = null;
		
		try{
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM " + db + ".Event";
			rs = stmt.executeQuery(query);
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
	
	public static ResultSet getEventById( Connection conn, String id ){
		
		ResultSet rs = null;
		
		try{
			/*
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "WHERE " + db + ".Event.ID=" + id + ";";
			rs = stmt.executeQuery(query);
			*/
			
			PreparedStatement getEventById = null;
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "WHERE " + db + ".Event.ID=?;";
			
			getEventById = conn.prepareStatement( query );
			getEventById.setString( 1, id );
			getEventById.executeQuery();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	public static ResultSet getEventsInRange( Connection conn, String startDateTime, String endDateTime ){
		
	ResultSet rs = null;
		
		try{
			/*
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "WHERE " + db + ".Event.StartDate BETWEEN " + startDateTime + " AND " + endDateTime + ";";
			rs = stmt.executeQuery(query);
			*/
			
			PreparedStatement getEventsInRange = null;
			String query = "SELECT * "
					+ "FROM " + db + ".Event "
					+ "WHERE " + db + ".Event.StartDate BETWEEN ? AND ?;";
			
			getEventsInRange = conn.prepareStatement( query );
			getEventsInRange.setString( 1, startDateTime );
			getEventsInRange.setString( 2, endDateTime );
			getEventsInRange.executeQuery();
			
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		
		return rs;
	
	}
	
	
	
	
}