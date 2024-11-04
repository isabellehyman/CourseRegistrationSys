import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable{
	private String courseNames;
	private String id; 
	private int max;
	private int numRegistered;
	private ArrayList <String> names = new ArrayList<String>();
	private String instructor; 
	private int sectionNum;
	private String location;
	
	// default constructor 
	public Course() {
	

	}

	public Course(String courseNames, String id, int max, int numRegistered, ArrayList<String> names, String instructor, int sectionNum, String location ) {
		this.courseNames = courseNames;
		this.id = id;
		this.instructor = instructor; 
		this.location = location;
		this.max = max;
		this.names = names;
		this.numRegistered = numRegistered;
		this.sectionNum = sectionNum;
	}
	// getters and setters 

	public String getCourseNames() {
		return courseNames;
	}


	public void setCourseNames(String courseNames) {
		this.courseNames = courseNames;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	public int getNumRegistered() {
		return numRegistered;
	}


	public void setNumRegistered(int numRegistered) {
		this.numRegistered = numRegistered;
	}


	public ArrayList<String> getNames() {
		return names;
	}


	public void setNames(ArrayList<String> names) {
		this.names = names;
	}


	public String getInstructor() {
		return instructor;
	}


	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}


	public int getSectionNum() {
		return sectionNum;
	}


	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	// DO I NEED THIS METHOD?????
	public ArrayList<String> getStudents(){
		return names; 
	}

	// other methods 

	// is full
	public boolean isFull() {
		return this.max == numRegistered;
	}

	// add student 
	public void addStudent(Student student){
		if(!isFull()){
			names.add(student.getfName()+" "+student.getlName());
			numRegistered++;
		}
		else{
			System.out.println("Course is already full, no more students can be added.");
		}
	}

	// remove student 
	public void removeStudent(Student student){
		String name = student.getfName()+" "+student.getlName();
		names.remove(name);
		numRegistered--;
	}

}


