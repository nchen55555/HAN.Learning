<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body bgcolor = "lightblue">

 <div class = "logincard" >
 
 	<h2 > Register for <br> HAN.Learning Now! </h2>
 	<form action = "registerservlet" method = "post" >
 	<p> Register with a username: </p>
	       <input type="text" name = "newUname"></input> 
	   		<p> Register with a password: </p>
	   		<input type="password" name = "newPass"></input>
	   		<p><br></p>
	   	<button class="buttonInput" type="submit">Register</button>
	   	<p> </p> 
	   	</form>
 </div>
</body>
</html>