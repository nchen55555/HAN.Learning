package Everything;
import java.util.ArrayList;

public class MultChoice extends Problem{
	
	
	public MultChoice (int i) {
		super(i); //goes to the abstract Problem superclass and initializes the object
		
	}
	
	public ArrayList<String>processQuestions() { //overrides the processQuestions method in the abstract class
		ArrayList<String>arr = new ArrayList<String>();
		Database data = super.getDatabase();
		String problem = data.getDescription(super.getId()); //gets the description of the problem from the database 
		//(this includes question and answer choices)
		//Note: each answer and question in the database is separated by a "?"ex. "What is the number?1?4?5?7?"
		int t = problem.indexOf("?"); //gets the first question mark's index
		while(t != -1) {
			String temp = problem.substring(0,t); //temp is equal to the string from the beginning to the ? index (this is the question in the database)
			problem = problem.substring(t+1); //resets problem to new string without temp
			arr.add(temp);//adds temp to the arraylist
			t = problem.indexOf("?");
		}
		return arr;
	}
	
	
	
}
