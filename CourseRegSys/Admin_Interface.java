import java.util.ArrayList;
import java.io.Serializable;

public interface Admin_Interface extends Serializable {
	public void createCourse(String name, String id, int max, int numRegistered, ArrayList<String> names, String instructor, int sectionNum, String location);
	public void deleteCourse(String id);
	public void editCourse(String id, int sectionNum, int max, int numRegistered, String instructor, int newSection, String location);
	public void displayInfo(String id);
	public void registerStudent(Student student);
	public ArrayList<Course> viewFull();
	public void writeFullCoursesToFile(String fileName);
	public ArrayList<String> viewStudentsInClass(String id);
	public ArrayList<Course> viewRegistered(Student student);
	public void sortByNumber();
}
