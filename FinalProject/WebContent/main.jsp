<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="javax.servlet.http.HttpSession" %>
   <%@ page import="Everything.*" %>
   <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel = "stylesheet" type = "text/css" href = "css/LoginAndRegister.css">
<!-- Used to link this HTML page to an external CSS file -->
<title>HAN Leaning Main Page</title>
<%
if(session.getAttribute("User") != null){
	User u = (User)session.getAttribute("User");
%>

</head>
<body>
<b>HAN Learning</b>
<hr>
<div><p class = "information">
Our Plan:
<%
ArrayList<Course> plan = u.getCourses();
for(int i = 0; i < plan.size(); i++){
	String display = plan.get(i).getName(); //displays the names of the courses (that the user needs to complete)
	%> <br> <%= display %> <br><br> <%
}
	
%>
</p> </div>
<center>

<% 
String name = u.getName();

%>

<p> Welcome <%= name %>! </p>

<form action = "MainServlet" method = "post">
	   	<button type="submit" name = "Start">Continue to Classes</button>
	   	<p> </p>  
	</form>
	<!-- Once “Continue to Classes” is clicked, the servlet will direct the user to the first lesson. -->
	
<%}
else{
	response.sendRedirect("login.jsp");
}
%>
</center>
</body>
</html>