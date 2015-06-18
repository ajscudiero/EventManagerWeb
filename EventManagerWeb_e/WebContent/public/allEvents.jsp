

<!DOCTYPE HTML>
<%@page import="com.sun.xml.internal.txw2.Document"%><html>
<%@page import="java.io.IOException" %>
<%@page import="java.io.PrintWriter" %>
<%@page import="java.sql.Connection" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.Statement" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="javax.servlet.ServletException" %>
<%@page import="javax.servlet.http.HttpServlet" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="javax.servlet.http.HttpServletResponse" %>
<%@page import="com.javalavas.db.Connect" %><html>
<head>
<link rel="shortcut icon" href="favicon.ico">
<script type="text/javascript" src="components/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="components/bootstrap/js/bootstrap.js"></script>
<title>All Events</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script src="components/jquery-2.1.4.min.js"></script> 
    <script> 
    $(function(){
      $("#includedContent").load("navBar.html"); 
    });
    </script> 	
</head>
							
<body>	

	<div id="includedContent"></div>

				<table class="table table-striped">
<%  
String user = null;
if(session.getAttribute("user") == null){
    response.sendRedirect("index.html");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
    if(cookie.getName().equals("user")) userName = cookie.getValue();
    if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
    sessionID = session.getId();
}
Class.forName("com.mysql.jdbc.Driver");

Connection con = Connect.getConnection();
System.out.println("Connection created");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery("select * from Event");%>

<table align="center" border="1" class="table table-striped">

  <tr>
    <td>ID</td>
    <td>Title</td>
    <td>StartDate</td>
    <td>EndDate</td>
    <td>Event Type ID</td>
    <td>Description</td>
    <td>Owner ID</td>
    <td>Location</td>
    <td>Status</td>

  </tr>
   <% while (rs.next()) {%>
  <tr>
   <td><%=rs.getString("ID")%></td>
    <td ><%=rs.getString("Title") %></td>
    <td><%=rs.getString("StartDate") %></td>
    <td><%=rs.getString("EndDate") %></td>
    <td><%= rs.getString("EventType_ID") %></td>
    <td><%= rs.getString("Description")%></td>
    <td><%= rs.getString("Owner_ID")%></td>
    <td><%=rs.getString("Location") %></td>
    <td><%=rs.getString("Status") %></td>

  </tr>
 <%}%>
		
</body>
</html>
