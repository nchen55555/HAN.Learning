package Everything;


import java.io.IOException;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname = request.getParameter("uname"); //gets the username from login.jsp
		String pass = request.getParameter("pass"); //gets the password from login.jsp
		Database b = new Database();		
		HttpSession session = request.getSession();
		User user = b.getUser(uname, pass); //gets the user with the inputed username and password
		if(user != null) { //checks if user is not null
			session.setAttribute("User", user);
			response.sendRedirect("main.jsp"); //redirects to the main page
		}
		else {
			response.sendRedirect("login.jsp"); //sends user page to the login change to put in password again
		}	
	}
	
}
