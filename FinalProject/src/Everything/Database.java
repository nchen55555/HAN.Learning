package Everything;
import java.sql.*;
import java.util.ArrayList;

public class Database {
	private Connection conn;
	public Database() {
		connect(); //connects to the database
	}
	
	private void connect(){ //method to connect to the database
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");	
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HAN;user=Admin;password=computerscience;");
		} catch( Exception e) {
			e.printStackTrace();
		}
	}
	
	public int test() { //tests to see if connection is complete
		int test = 0;
		PreparedStatement pstmt = null;
		try {
		  String SQL = "SELECT 1"; 
		  pstmt = conn.prepareStatement(SQL);
		  ResultSet resultSet = pstmt.executeQuery();
		  if(resultSet.next())
			  test = resultSet.getInt(1);
		} catch (SQLException e) {
			System.out.println("There was an error in test: " + e);
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
			} catch(SQLException e) {
			}
		}
		return test;
	}
	
	public boolean insertNewUser(String name, String password) { //inserts a new user with name and password (from register servlet) into the database
		boolean inserted = false;
		PreparedStatement pstmt = null;
		try {
			if(getUser(name, password) == null) { //checks if there isn't any other user with the same name and password
			  String SQL = "INSERT INTO Student (Name, Password) VALUES (?, ?)"; // query sent to the database that inserts a student with name and password
			  pstmt = conn.prepareStatement(SQL);
			  pstmt.setString(1,  name);
			  pstmt.setString(2, password);
			  pstmt.execute();
			  inserted = true; //inserted becomes true (will return inserted)
			}
			else {
				System.out.println("Username and Password already taken");
			}
			} catch (SQLException e) {
				System.out.println("There was an error in insert: " + e);
			} finally { 
				try {
					if(pstmt != null)
						pstmt.close();
				} catch(SQLException e) {
				}
			}
		return inserted;
	}
	
	public User getUser(String name, String password) { //gets the user with the specified name and password
		PreparedStatement pstmt = null;
		ResultSet result = null;
		User u = null;
		try {
			String SQL = "SELECT UserID, CourseIndex FROM Student WHERE (Name = ? AND Password = ?)"; //query that selects the user
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			pstmt.setString(1,  name);
			pstmt.setString(2,  password);
			result = pstmt.executeQuery();
			if(result.next()) { //gets the result (if it has the specified user)
				u = new User(name, password); //creates a user with that name and password
				u.setID(result.getInt("UserID")); //sets the id of the user with the database information
				u.setIndex(result.getInt("CourseIndex")); //set the index of which the user was previously on (which course it was on)
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in getting the User: " + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return u;
	}
	
	public int updateCourseIndex(int iD, int index) { //updates the course index (next time the user wants to learn again, can come back to 
		//the course they were on
		PreparedStatement pstmt = null;
		try {
			String SQL = "UPDATE Student SET CourseIndex = ? WHERE UserID = ?"; //updates to new index
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			pstmt.setInt(1, index);
			pstmt.setInt(2, iD);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println("There was an error in updating course index:" + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return index; // returns the new updated index
	}
	
	public ArrayList<Course> getCoursePlan(){ //gets an array list of courses
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Course> plan = new ArrayList<Course>();
		try {
			String SQL = "SELECT CourseID, Name FROM Course "; //selects the courseid from the course
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			result = pstmt.executeQuery();
			while(result.next()) {
				plan.add(new Course(result.getInt("CourseID"), result.getString("Name"))); //adds a new course with that course id with that name
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in getting course plan: " + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return plan;
	}
	
	public ArrayList<Content>getContentNotes(int courseNUM){  // parameter courseNUM is the number associated with each course ex. "Classes 
		// and Objects" = 0
		PreparedStatement pstmt = null;
		ResultSet result = null;
		ArrayList<Content> plan = new ArrayList<Content>();
		try {
			String SQL = "SELECT ClientId, Notes, Answer, FillBlank FROM Content WHERE (CourseNum = ?)"; //selects the "ClientId" (ContentId), Notes, Answer(s), 
			//FillBlank (whether or not it is a fill blank question, mult choice, or neither)
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			pstmt.setInt(1,  courseNUM);
			result = pstmt.executeQuery();
			while(result.next()) { //while result still has more columns
				String a = result.getString("Answer"); //gets the answer
				if(a == null) //checks if it is a question or just a note (if answer is null, then it is a note)
					plan.add(new Content(result.getInt("ClientId"))); //adds Content (notes)
				else {
					String b = result.getString("FillBlank"); // checks if it is a mult choice or fillblank
					if(b.equals("T")) //"T" means it is a fillBlank ("T" = true)
						plan.add(new FillBlank(result.getInt("ClientId"))); //adds a fillblank 
					else 
						plan.add(new MultChoice(result.getInt("ClientId"))); //adds a multChoice
				}
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in getting content notes: " + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return plan;
	}
	
	public String getDescription(int i) {//gets the description at i (id)
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String d = "";
		try {
			String SQL = "SELECT Notes FROM Content WHERE (ClientId = ?)"; //selects notes from ClientId ("ContentId")
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			pstmt.setInt(1, i);
			result = pstmt.executeQuery();
			if(result.next()) {
				d = result.getString("Notes"); //sets d to the description notes (the description is actually a src link to the image of
				// the notes
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in getting desciption: " + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return d;
	}
	
	public int getContentCorresponding(int id) { //gets the courseNum of the content ("Interfaces" = 4, "Abstract Classes" = 3...)
		PreparedStatement pstmt = null;
		ResultSet result = null;
		int integer = 0;
		try {
			String SQL = "SELECT CourseNum FROM Content WHERE (ClientId = ?)"; //selects the number from content at that id
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			pstmt.setInt(1, id);
			result = pstmt.executeQuery();
			if(result.next()) {
				integer = result.getInt("CourseNum");
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in getting corresponding content: " + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return integer;
	}
	
	public String getAnswer(int id) { //gets the answer to the question
		PreparedStatement pstmt = null;
		ResultSet result = null;
		String d = "";
		try {
			String SQL = "SELECT Answer FROM Content WHERE (ClientId = ?)"; //selects answer from the database at that id
			pstmt = conn.prepareStatement(SQL);//gets the info from database
			pstmt.setInt(1, id);
			result = pstmt.executeQuery();
			if(result.next()) {
				d = result.getString("Answer");
			}
			
		} catch (SQLException e) {
			System.out.println("There was an error in getting Answer: " + e);
		} finally { //finally always do this
			try {
				if(pstmt !=null)
					pstmt.close();
			} catch(SQLException e) {
				}	
			}
		return d;
	}
	
}
