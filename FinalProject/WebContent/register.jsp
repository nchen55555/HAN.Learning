<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="css/LoginAndRegister.css">
</head>
<center> <p> <b> 
Register!
</b> </p> </center>


 <div class = "logincard" ><center>
 	<form action = "registerservlet" method = "post" >
 	<p> Register with a username: </p>
	       <input type="text" name = "newUname"></input> 
	   		<p> Register with a password: </p>
	   		<input type="password" name = "newPass"></input>
	   		<p><br></p>
	   	<button class="buttonInput" type="submit"> Register </button>
	   	<p> </p> 
	   	<!-- Once register is clicked, the form data for the username and password is saved in the Database.
	This allows the User to be able to save your spot in the platform and re-enter with saved data.
	The Servlet will direct the user to continue on to the main page.  -->
	   	
	   	</form>
</center> </div>
</body>
</html>