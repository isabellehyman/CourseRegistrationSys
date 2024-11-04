import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;



public class Admin extends User implements Admin_Interface {
	
	private ArrayList<Course> courses; 
	private ArrayList<Student> studentsInSchool = new ArrayList<Student>();

	// constructor 
	public Admin(String username, String password, String fName, String lName) {
		super(username, password, fName, lName);
	}


	public void setCourses (ArrayList <Course> courses){
        this.courses = courses;
    }


	public void createCourse(String name, String id, int max, int numRegistered, ArrayList<String> names, String instructor, int sectionNum, String location) {
		Course created = new Course(name, id, max, numRegistered, names, instructor, sectionNum, location);
		courses.add(created);
	}

	public void deleteCourse(String id) {
		boolean courseFound = false;
		for(int i = 0; i < courses.size(); i++){
			if(courses.get(i).getId().equalsIgnoreCase(id)){
				courses.remove(courses.get(i));
				courseFound = true; 
			}
		}

		if(courseFound == false){
			System.out.println("Course not found");
		}
		else{
			System.out.println("Course deleted.");

		}

	}

	public void editCourse(String id, int sectionNum, int max, int numRegistered, String instructor, int newSectionNum, String location) {
		for(int i = 0; i < courses.size(); i++){
			if(courses.get(i).getId().equalsIgnoreCase(id)&& courses.get(i).getSectionNum()==sectionNum){
				courses.get(i).setMax(max);
				courses.get(i).setNumRegistered(numRegistered);
				courses.get(i).setInstructor(instructor);
				courses.get(i).setSectionNum(sectionNum);
				courses.get(i).setLocation(location);
			}
		}
	}
	public void displayInfo(String id) {
		boolean courseFound = false;
		for(int i = 0; i < courses.size(); i++){
			if(courses.get(i).getId().equalsIgnoreCase(id)){
				System.out.println("Course name: "+ courses.get(i).getCourseNames());
				System.out.println("Course ID: "+ courses.get(i).getId());
				System.out.println("Course instructor: "+ courses.get(i).getInstructor());
				System.out.println("Course capacity: "+ courses.get(i).getMax());
				System.out.println("Number of students in course: "+ courses.get(i).getNumRegistered());
				System.out.println("Section number: "+ courses.get(i).getSectionNum());
				System.out.println("Course location: "+ courses.get(i).getLocation());
				System.out.println();

				courseFound = true; 
			}


		}

		if(courseFound == false){
			System.out.println("Could not find this course in the system");

		}



	}

	  // view courses as an Admin, this overrides the viewCourses defined in the user class
	  @Override  
	  public void viewCourses() {
        System.out.println("Listing all courses: ");
        for(int i = 0; i < courses.size(); i++){
            System.out.println(courses.get(i).getCourseNames());
			System.out.println();
			System.out.println("\tCourse ID: "+courses.get(i).getId());
			System.out.println("\tNumber Registered: "+courses.get(i).getNumRegistered());
			System.out.println("\tMaximum Capacity: "+courses.get(i).getMax());
			System.out.println();


        }
    }

	public void registerStudent(Student student) {
		for(int i = 0; i < studentsInSchool.size(); i++){
			if(studentsInSchool.get(i).getfName().equalsIgnoreCase(student.getfName())&&studentsInSchool.get(i).getlName().equalsIgnoreCase(student.getlName())){
				System.out.println("The student you are trying to add is already in the system.");
			}
			else{
				studentsInSchool.add(student);
				System.out.println("Student has been added to the system.");
			}
			
		}

		if(studentsInSchool.size()==0){
			studentsInSchool.add(student);
			System.out.println();
			System.out.println("Student has been added to the system.");
		}


	
	}
	public ArrayList<Course> viewFull() {
		// create an arraylist for full courses 
		ArrayList<Course> fullCourses = new ArrayList<Course>();
		for(int i = 0; i < courses.size(); i++){
			if(courses.get(i).isFull()){
				fullCourses.add(courses.get(i));
			}
		}

		if (fullCourses.size() == 0) {
			System.out.println("There are no full courses.");
		}
		return fullCourses;
	}
	public void writeFullCoursesToFile(String fileName) {
		// use object output stream to write objects to a file 
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write("Full courses:\n ");
			for(Course course:courses){
				if(course.isFull()){
					writer.write("Course name: " + course.getCourseNames()+" "+course.getId()+"\n Section number: " +course.getSectionNum());
					writer.newLine();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
        
	public ArrayList<String> viewStudentsInClass(String id) {
		ArrayList<String> studentsInClass = new ArrayList<>();
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getId().equalsIgnoreCase(id)) {
				for(String student:courses.get(i).getStudents()){
					studentsInClass.add(student);
				}
				//break; // Exit the loop once the correct course is found
			}
		}
		if (studentsInClass.size() == 0) {
			System.out.println("There are 0 students currently enrolled in this class. ");
		}
		return studentsInClass;
	}
	// list the courses that a student is enrolled in 
	public ArrayList<Course> viewRegistered(Student student) {
		ArrayList<Course> studentsCourses = new ArrayList<Course>();
		String studentFullName = student.getfName() + " " + student.getlName();
	
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getNames().contains(studentFullName)) {
				studentsCourses.add(courses.get(i));
			}
		}
	
		return studentsCourses;
	}
	public void sortByNumber() {
		
		courses.sort(Comparator.comparingInt(Course::getNumRegistered));
		
		
	}
	
}
