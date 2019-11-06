package Everything;
import java.util.ArrayList;

public abstract class Problem extends Content{
	
	protected String cA; // correct answer
	
	public Problem (int i) {
		super (i); //gets the constructor from the super class Content constructor
		cA = super.getDatabase().getAnswer(i); //initializes the answer to the database's answer
	}
	
	public abstract ArrayList<String> processQuestions(); //abstract method processQuestions 
	//that will return an arraylist of the question (and if multiple choice 
	//then the choices) and the answer(s)
	
	public String getAnswer() {
		return cA;
	}
	
}
