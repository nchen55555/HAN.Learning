package Everything;


import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("User");	//gets the current user of the session
		if(user != null) {
			String step1 = request.getParameter("Start"); //the first button 
			String step2 = request.getParameter("Next"); // the next button
			String answer = request.getParameter("answer"); // the answer/input for the free response
			String save = request.getParameter("Close"); // the save button
			String finished = request.getParameter("Finish"); //the finished and ended button
			String restart = request.getParameter("restart"); //the restart and reset button (to each course)
			Database b = new Database();
			Course c = user.getCourse(user.getIndex());
			Content lesson = c.getLesson();
			String ans = ""; // ans is the mutliple choice answer "a" or "b" or "c" or "d"...
			if(lesson instanceof Problem) { //checks if the lesson (content) is an instance of a Problem
				ans = ((Problem)lesson).getAnswer(); //sets ans to the correct answer in Problem
			}
			if(step1 != null) { //cehcks if first button has been clicked
				user.setCourses(b.getCoursePlan()); //sets new courses for the user
				response.sendRedirect("Lesson.jsp"); //redirects to the lesson page
			}
			else if(restart != null) { //restart button clicked?
				c.reset(); //resets to the begining of the course
				response.sendRedirect("Lesson.jsp");
			}
			else if(request.getParameter("multChoice") != null && request.getParameter("multChoice").equals(ans)){ //gets the correct answer (from multiple choice) - ans is the correct answer, so
				//if the user clicks the correct answer, then the statement should not be null
				request.setAttribute("answerCorrect", true);
				RequestDispatcher view = request.getRequestDispatcher("Lesson.jsp");
				view.forward(request, response);					
			}
			else if(request.getParameter("multChoice")!=null) {
				// if the user gets the wrong answer and the page does not redirect, it the answerCorrect is set to false and user must redo
				request.setAttribute("answerCorrect", false);
				//showNext = Boolean.valueOf((Boolean) request.getAttribute("answerCorrect"));; //decides whether or not can show next unless question is answer
				RequestDispatcher view = request.getRequestDispatcher("Lesson.jsp");
				view.forward(request, response);
			}
			else if(answer != null) { //this is for fill in the blank
				String correct = ((Problem)lesson).getAnswer(); //gets the correct answer from Problem
				if(answer.equals(correct)) {
					request.setAttribute("answerCorrect", true);
					RequestDispatcher view = request.getRequestDispatcher("Lesson.jsp");
					view.forward(request, response);
				}
				else {
					request.setAttribute("answerCorrect", false);
					RequestDispatcher view = request.getRequestDispatcher("Lesson.jsp");
					view.forward(request, response);
				}
			}
			else if(finished != null) { //checks if the user is completed with all courses and contents
				//b.updateCourseIndex(user.getIndex(), 0); //sets the user back to index at 0 
				user.setIndex(0); 
				System.out.println(user.getIndex());
				response.sendRedirect("main.jsp"); //redirects to main page for user to start over again
			}
			
			else if(step2 != null ) { //checks if the user has clicked the "next" button
					if(c.isCompleted()){	// checks if the user has completed the course
						user.incrementIndex(); // increments the index
						c = user.getCourse(user.getIndex()); // reassigns c to a new course
						c.reset(); // c resets to the first index
						response.sendRedirect("Lesson.jsp"); // redirects to lessons page
					}
					else if(c.incrementIndex()) {//increments the course index (next content)
						response.sendRedirect("Lesson.jsp");
					}
					else{
						user.incrementIndex();  // increments the user's index (to next course)
						response.sendRedirect("Lesson.jsp");
					}
			}
			else if(save != null) { //checks if the save button has been clicked
				user.save(); //saves the index of the course the user is on
				response.sendRedirect("login.jsp");
			}
			else {
				RequestDispatcher view = request.getRequestDispatcher("Lesson.jsp");
				view.forward(request, response);
			}
		}else {
			response.sendRedirect("login.jsp");
		}
	}
}
