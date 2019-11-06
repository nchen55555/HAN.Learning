package Everything;


import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class registerservlet
 */
public class registerservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerservlet() {
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
		String newUname = request.getParameter("newUname"); //gets the "newUname" found in the register.jsp
		String newPass = request.getParameter("newPass"); //gets the "newPass" found in the register.jsp
		HttpSession session = request.getSession();
		
		if(newUname == null || newPass == null || newUname.equals("")|| newPass.equals("")) //checks to see if the newUname and newPass have stuff inside of it
			response.sendRedirect("register.jsp"); //if so, send it back to the register page
		else {
			Database b = new Database();
			if(b.insertNewUser(newUname, newPass)) { //inserts the new user (returns true if the username and password aren't duplicated false otherwise)
				User user = b.getUser(newUname, newPass); //gets the newly created user
				session.setAttribute("User", user); //sets the session attribute to the user created 
				response.sendRedirect("main.jsp"); //directs to main page
			} else {
				response.sendRedirect("register.jsp"); 
			}
		}
		
	}

}
