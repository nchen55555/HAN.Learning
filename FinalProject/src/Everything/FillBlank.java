package Everything;
import java.util.ArrayList;
public class FillBlank extends Problem{

	
	public FillBlank (int i) {
		super(i);	
	}
	
	public ArrayList<String> processQuestions(){ //overrides the Problem abstract method processQuestions
		ArrayList<String>arr = new ArrayList<String>();
		String problem = super.getContentDescription(); //the problem is taken from the content
		arr.add(problem); //adds the problem
		return arr;
	}
	
}
