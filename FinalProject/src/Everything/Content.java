package Everything;
import java.util.ArrayList;

public class Content {
	protected int id; //id of the content to be accessed in the database
	private String description;
	private Database data;
	private int courseNum; //what number each content associates to in a course 
	public Content (int i) {
		id = i; 
		data = new Database();
		description = data.getDescription(id);//gets the description from the database (includes notes or questions )
		courseNum = data.getContentCorresponding(id); //gets the courseNum the content is associated with
	}
	
	public int getId() {
		return id;
	}
	public Database getDatabase() {
		return data;
	}
	public int getContentCourse() {
		return courseNum;
	}
	
	public String toString() {
		return description + " "  + courseNum;
	}
	public String getContentDescription() {
		return description;
	}
	
}
