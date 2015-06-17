package com.javalavas.servlets;
import com.javalavas.db.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javalavas.db.Connect;
import com.javalavas.db.Register;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	 
	      // Write the response message, in an HTML page
	      try {
	         out.println("<!DOCTYPE html>");
	         out.println("<html><head>");
	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	         out.println("<title>Echo Servlet</title></head>");
	         out.println("<body><h2>You have enter</h2>");
	 
	         // Retrieve the value of the query parameter "username" (from text field)
	         String username = request.getParameter("username");
	         String password = request.getParameter("password");
	         String firstName = request.getParameter("firstName");
	         String lastName = request.getParameter("lastName");
	         String city = request.getParameter("city");
	         String state = request.getParameter("state");
	         String homePhone = request.getParameter("homePhone");
	         String cellPhone = request.getParameter("cellPhone");
	         String companyName = request.getParameter("companyName");
	         String branch = request.getParameter("branch");
	         String address1 = request.getParameter("address1");
	         String address2 = request.getParameter("address2");
	         int foodId = 1;
	         try {
				Connection conn = Connect.getConnection();
				boolean flag = Update.updateUser(conn, username, password, firstName, lastName, address1, address2, city, state, "", homePhone, cellPhone, "", companyName, branch,	foodId, "");
				 if(flag)
		        	 out.println("Info Update Success!");
		         else
		        	 out.println("Info Update Failed!");
		        	 
	         } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        	 
	         out.println("</body></html>");
	      } finally {
	         out.close();  // Always close the output writer
	      }
	   }

	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


private static String htmlFilter(String message) {
    if (message == null) return null;
    int len = message.length();
    StringBuffer result = new StringBuffer(len + 20);
    char aChar;

    for (int i = 0; i < len; ++i) {
       aChar = message.charAt(i);
       switch (aChar) {
           case '<': result.append("&lt;"); break;
           case '>': result.append("&gt;"); break;
           case '&': result.append("&amp;"); break;
           case '"': result.append("&quot;"); break;
           default: result.append(aChar);
       }
    }
    return (result.toString());
}
}