package Everything;
import java.util.ArrayList;

public class Course implements Completable {
	private int id; //id used in the database to get the Course
	public final String name;
	private int index; //index of the lesson in the array list
	private Database data;
	private int currentCourseData; // a number that associates with the Course (ex. "classes and objects" = 0, "interfaces" = 4)
	// currentCourseData is found inside the Database and used to document a new course
	private ArrayList <Content> lesson;
	
	public Course (int i, String n) {
		id = i;
		name = n;
		index = 0;
		data = new Database();
		switch(name){ //the name of the course associates to its currentCourseData (this is used in the database)
		case "Classes and Objects": 
			currentCourseData = 0;
			break;
		case "Inheritance":
			currentCourseData = 1;
			break;
		case "Polymorphism":
			currentCourseData = 2;
			break;
		case "Abstract Classes":
			currentCourseData = 3;
			break;
		case "Interfaces":
			currentCourseData = 4;
			break;
		}
		lesson = data.getContentNotes(currentCourseData); //gets the lesson at the currentCourseData from the database
	}
	public void setContentLessons(ArrayList<Content> temp) {
		lesson = temp;
	}
	
	public boolean incrementIndex() {
		if(index < lesson.size()) {
			index++;
			return true;
		}
		index = 0;
		return false;
	}
	public Content getLesson() {
		return lesson.get(index);
	}
	public ArrayList<Content> getAllContents(){
		return lesson;
	}
	public int getIndex() {
		return index;
	}	
	public void reset() {
		index = 0;
	}
	
	public String getName() {
		return name;
	}
	public boolean isCompleted() { //checks if the course is completed, to get a new course
		if(index == lesson.size() - 1) {
			return true;
		}
		return false;
	}
	
	public String toString() {	
		return  name;
	}
	
	
}
