package Everything;
import java.util.ArrayList;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class User implements Completable{
	private int id; // id of the user to access in the database
	private String username;
	private String password;
	private int index; // index the of the course the user is on
	private Database data; 
	private ArrayList<Course>plan; // an array list of courses that each user has
	public User (String u, String p) {
		username = u;
		password = p;
		data = new Database();
		plan = data.getCoursePlan();//gets the plan from the database
	}
	
	public int getID() {
		return id;
	}
	
	public void setIndex(int i) {
		index = data.updateCourseIndex(id, i);
	}
	
	public String getName() {
		return username;
	}
	public int getIndex() {
		return index;}
	
	public void incrementIndex() {
		index++;	//each time user completes a course, the index increments, and user moves on to next course	
	}
	
	public void save() {
		incrementIndex(); 
		data.updateCourseIndex(id, index); //updates the index of the course that the user is on to the database
	}
	public String toString() {
		return username + " " + password + " " + id;
	}

	public void setCourses(ArrayList<Course> p) {
		plan = p;
	}
	public ArrayList<Course> getCourses() {
		return plan;
	}
	public Course getCourse(int i) {
		return plan.get(i);
	}	
	public void setID(int i) {
		id = i;
	}
	
	public boolean isCompleted() { 
		if(index == plan.size()) { //checks if the index reaches the plan's size
			return true;
		}
		return false;
	}
	public void reset() {
		index = 0; 
	}
	
}
