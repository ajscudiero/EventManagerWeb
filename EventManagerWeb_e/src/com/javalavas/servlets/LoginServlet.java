package com.javalavas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javalavas.db.Connect;
import com.javalavas.db.SignIn;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	    
		System.out.println("doPost login called");
		// Set the response message's MIME type
	      response.setContentType("text/html; charset=UTF-8");
	      // Allocate a output writer to write the response message into the network socket
	      PrintWriter out = response.getWriter();
	 
	      // Write the response message, in an HTML page
	      try {
	         out.println("<!DOCTYPE html>");
	         out.println("<html><head>");
	         out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
	         out.println("<title>Login Servlet</title></head>");
	         out.println("<body>");
	 
	         // Retrieve the value of the query parameter "username" (from text field)
	         String username = request.getParameter("user");
	        
	         // Retrieve the value of the query parameter "password" (from password field)
	         String password = request.getParameter("pass");
	         
	         boolean userExists = false;
	        
	         
	         try{
	        	 java.sql.Connection conn = Connect.getConnection();
	        	 userExists = SignIn.logIn(conn, username, password);
	         } catch (SQLException e) {
	        	 e.printStackTrace();
	         }
	         
	         if(userExists == true )
	         {
	        	 HttpSession session = request.getSession();
		            session.setAttribute("user", username);
		            //setting session to expiry in 30 mins
		            session.setMaxInactiveInterval(30*60);
		            Cookie userName = new Cookie("user", username);;
		            response.addCookie(userName);
		            //Get the encoded URL string
		            String encodedURL = response.encodeRedirectURL("http://localhost:8080/EventManagerWeb_e/public/myEvents.jsp");
		            response.sendRedirect(encodedURL);
	        
	         }
	         else{
	        	 response.sendRedirect("http://localhost:8080/EventManagerWeb_e/public/index.html");
	         }
	         
	         out.println( "<h2>Username: " + username + "</h2>");
	         out.println( "<h2>Password: " + password + "</h2>");
	         out.println("</body></html>");
	         
	      } finally {
	         out.close();  // Always close the output writer
	      }
	      
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
