<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  
   %>
   <%@ page import="javax.servlet.http.HttpSession" %>
   <%@ page import="Everything.*" %>
   <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/LoginAndRegister.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Used to link this HTML page to an external CSS file -->
<title>Main Dashboard</title>
</head>
<body>
	<%
	User u = (User) session.getAttribute("User"); //gets the User using the session right now
	if(u != null){ //checks if the user is logged in (if not redirects to login.jsp page)
		Course c = u.getCourse(u.getIndex()); //gets the course
		Content lesson = c.getLesson(); //gets the content
		boolean question = false; //boolean that checks if the content is a question or not
		boolean multChoice = false; //checks if its multiple choice (or fill in the blank)
		if(lesson instanceof Problem){ //if lesson is an instance of problem (works because problem is a content)
			question = true; 
			if(lesson instanceof MultChoice){ //mult choice is a problem which is a content
				multChoice = true;
			}
		}
		String s = lesson.getContentDescription(); //the description/question (if just notes, it will be an image url to the notes)
		if (question){
			if(request.getAttribute("answerCorrect") != null ) {
				boolean answerCorrect = Boolean.valueOf((Boolean) request.getAttribute("answerCorrect"));
				if(answerCorrect == false) {
					%> <center><h4> Wrong Answer</h4></center> <%
				}
				else{
					%> <center><h3> Correct! Click Next</h3> </center>
					<div class = question><center>
				 	<form action = "MainServlet" method = "post" > 
			       <button type = "submit" name = "Next" >Next</button>
			   	  	<p> </p>  
					</form></center>	
					<!--  If the user answers the question correctly and clicks next, the servlet directs the user to the next lesson -->
			   	</div>
					<%
				}
			}
			if(!multChoice){
	%>
				<div class = question><center>
				 <form action = "MainServlet" method = "post" >
			      <p> <%= s %> </p>
			       <input type="text" name = "answer"></input> 
			       <button type = "submit" >Submit</button>
			   	  <p> </p>  
				</form></center>	
			   </div>
	<% 
			}
			else{ 
				ArrayList<String>temp = ((Problem)lesson).processQuestions(); //gets the question and the answer choices 
				String m = null;
				String a = null;
				String b = null;
				String cR = null;
				String d = null;
				for(String str: temp){ //for each loop that traverses the array list and assigns each variable to a different question choice
					if(m == null)
						m = str;
					else if(a == null)
						a = str;
					else if(b == null)
						b = str;
					else if(cR == null)
						cR = str;
					else 
						d = str;
				}
			 %> 
			 <div class = question><center>
			  <form action = "MainServlet" method = "post" >
				<p>Question:  <%= m %> <br>
				<input type="radio" name="multChoice" value = "a"> <%= a %>
				<input type="radio" name="multChoice"  value = "b"><%= b %>
				<input type="radio" name="multChoice" value = "c"><%= cR %>
				<input type="radio" name="multChoice" value = "d"><%= d %>
	       		<button type = "submit" >Submit</button>
				</p> </form></center><!-- Mutliple Choice - each value is associated with what is received from the servlet -->
				<br>
			</div>
			<%}
		}
		else{	
			%>
			<div ><center>
			<b> <%= c.getName() %></b></center></div> 
			<center><img class = image src="<%= s %>" width="700" height="500"></center><%
			if(!((u.getIndex() == u.getCourses().size() - 1) && (c.getIndex() == c.getAllContents().size() - 1))){ //checks if the user is still 
				//within bounds inside all the courses
			%>	
				  <div class = question><center>
				 <form action = "MainServlet" method = "post" > 
			       <button type = "submit" name = "Next" >Next</button>
			   	  <p> </p>  
				</form></center>	
			   	</div>	
			   	<!-- If the course index is equal to the course length, the servlet will redirect the user to the next course -->	
			<% }
			else{%>
				<center><form action = "MainServlet" method = "post">
					<button type="submit" name = "Finish">Finished!</button>
				</form></center>
			<%} %>  					
			<%
		}
		if(!((u.getIndex() == u.getCourses().size() - 1) && (c.getIndex() == c.getAllContents().size() - 1))){			
			if(c.isCompleted()){	//checks if the course is completed (at last index)and allows the user to save and close
				%>
				<center><form action = "MainServlet" method = "post"> 
				   	<button type="submit" name = "Close">Save and Close</button>
				   	<p> </p>  
				</form></center>
			 
				<% 		
			}
			%>
			<center><form action = "MainServlet" method = "post">
				<button type="submit" name = "restart">Start at the Beginning of this Course Again?</button>
			</form></center>
			<!-- If the user clicks Start at the Beginning of this Course Again, the servlet directs the user 
			to the beginning of the course so he or she can restart the course if he or she wants to  -->
			
			<%
		}
	}
	else 
		response.sendRedirect("login.jsp");
	
		%>
			
</body>
</html>