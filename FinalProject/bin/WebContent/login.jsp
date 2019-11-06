<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HAN.Learning</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>	
	<h1 class = "welcome" > Welcome to <br> HAN.Learning </h1>
	<div class = "logincard logintext"> 
		<p>  A Learning Platform for Object Oriented Programming in Java </p>
		
<%
//get parameters from the request
String userName = request.getParameter("Username ");
%>

	<form action = "MyServlet" method = "post" >
	      <p> User Name: </p>
	       <input type="text" name = "uname"></input> 
	   		<p> Password:</p>
	   		<input type="password" name = "pass"></input>
	   		<p><br></p>
	   	<button class="buttonInput" type="submit">Login</button>
	   	<p> </p>  
	</form>
	
	<form action = "registerservlet" method = "post" >
		<button class = "buttonInput " name = "register" type = "submit">Register</button>
		
	</form>
	</div>
</body>
</html>