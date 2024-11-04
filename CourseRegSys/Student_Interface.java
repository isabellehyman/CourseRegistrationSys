import java.io.Serializable;


public interface Student_Interface extends Serializable {
	public void viewCourses();
	public void viewNotFull();
	public void registerStudent (String courseNames, int sectionNum, String fName, String lName);
	public void withdraw (String courseNames, int sectionNum, String fName, String lName);
	public void viewRegisteredCourses();
}
