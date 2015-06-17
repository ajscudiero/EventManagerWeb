package com.javalavas.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalavas.db.Connect;
import com.javalavas.db.EventHelper;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class RegisterEventServlet
 */
@WebServlet("/RegisterEventServlet")
public class RegisterEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request,response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost called.");
		
		//store form info
		String eventName = request.getParameter("eventName");
		String date = request.getParameter("date");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String location = request.getParameter("location");
		String additionalInfo = request.getParameter("additionalInfo");
		String meetingType = request.getParameter("meetingType");
	
		//register event in db
		try{
			java.sql.Connection conn = Connect.getConnection();
			EventHelper.createEvent( conn, eventName, date + " " + startTime, date + " " + endTime, "1", "1", additionalInfo, "1", "/path", location, "PENDING");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   
		//redirect
		String site = "http://localhost:8080/EventManagerWeb_e/public/myEvents.html" ;
		response.sendRedirect(site);
		
	}

}
