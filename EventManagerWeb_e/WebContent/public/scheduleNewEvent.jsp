
<!DOCTYPE HTML>
<html>

<head>
<script type="text/javascript" src="components/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="components/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="components/bootstrap/css/bootstrap.css"type="text/css"/>
<link rel="shortcut icon" href="favicon.ico">
<style>
.nonBoldLabel{
font-weight:normal;
}
</style>
<%
//allow access only if session exists
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
%>
<title>Schedule New Event</title>
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

		<div class="sign_up">
			<!----------start form----------->
			<form class="sign" method="POST" action="http://localhost:8080/EventManagerWeb_e/RegisterEventServlet">
				<div class="formtitle">Schedule New Event</div>
				<!----------start top_section----------->				
				<div class="top_section">
				
				<div class="section">
						<div class="input-sign details">
							<input type="text"  placeholder="Event Name" name="eventName" required /> 
						</div>
						<div class="input-sign details1">
						<label class="nonBoldLabel">Date:</label>
							<input type="date" placeholder="Date" name ="date" required />
						</div>
						<div class="clear"></div>
					<div class="section">
						<div class="input details">
						<label class="nonBoldLabel">Start Time:</label>
							<input type="time"  placeholder="Start Time" name="startTime" required/>
							
						</div>
						<div class="input details1">
							<label class="nonBoldLabel">End Time:</label>
							<input type="time"  placeholder="End Time" name="endTime" required/>
						</div>
						<div class="clear"> </div>
					</div>
					
						<div class="clear"> </div>
					</div>
				</div>

				<div class="bottom-section">
					<div class="section">
						<div class="input-sign details">
							<input type="text"  placeholder="Location" name="location" required/>
						</div>
						<div class="clear"> </div>
					</div>
					
					<div class="section">
						<div class="input-sign details">
							<input type="text"  placeholder="Additional Information" name="additionalInfo" required/>
						</div>
						<div class="clear"> </div>
					</div>
					
					<div class="section-country">
					<select name="meetingType"  class="frm-field required">
		            	<option value="null"> Type</option>         
		            	<option value="meeting">Meeting</option>
		         	</select>
					</div>
					<div class="submit">
						<input class="bluebutton submitbotton" type="submit" value="Submit" />
					</div>
				</div>
			</form>
		</div>
		
		
</body>
</html>
