package Everything;

public interface Completable {
	
	boolean isCompleted(); //checks if the course or content is completed or not - moves on to the next course or content if so
	
	void reset(); //resets everything back to 0
	
}
