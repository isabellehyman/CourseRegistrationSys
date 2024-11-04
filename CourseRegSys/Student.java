
import java.util.ArrayList;

public class Student extends User implements Student_Interface {

    // define an empty array list for the classes a particular student is registered in 
    private ArrayList<Course> registered = new ArrayList<Course>();
    private ArrayList<Course> courses; 

    // constructor 
    public Student(String username, String password, String fName, String lName){
        super(username, password, fName, lName);
    }

    // set course array list to be filled with courses 
    public void setCourses (ArrayList <Course> courses){
        this.courses = courses;
    }

    // define methods 

    // view courses as a student 
    @Override
    public void viewCourses() {
        System.out.println("Listing all courses: ");
        System.out.println();
        for(int i = 0; i < courses.size(); i++){
            System.out.println(courses.get(i).getCourseNames());
        }
        System.out.println();

    }

    // view all courses that aren't full 
    public void viewNotFull() {
        System.out.println("Listing all courses that aren't full: ");
        System.out.println();
        for(int i = 0; i < courses.size(); i++){
            if(!courses.get(i).isFull()){
                System.out.println(courses.get(i).getCourseNames());
            }
        }
    }

    // register for course 
    public void registerStudent(String courseNames, int sectionNum, String fName, String lName) {
        boolean courseFound = false;
        for(int i = 0; i < courses.size(); i++){
            if (courses.get(i).getCourseNames().equalsIgnoreCase(courseNames)&&courses.get(i).getSectionNum() == sectionNum){
                Course courseRegistering = courses.get(i);
                // fails if course is full 
                if(courseRegistering.isFull()){
                    System.out.println("Course is full. Cannot register");
                }
                // fails if student is already registered
                else if(isRegistered(courseRegistering.getId())){
                    System.out.println();
                    System.out.println("You're already registered in this course.");
                }
                // success! 
                else{
                    courseRegistering.addStudent(new Student(this.getUsername(), this.getPassword(), this.getfName(), this.getlName()));
                    registered.add(courseRegistering);
                    System.out.println("You are now successfully registered in "+ courseNames); 
                }

                courseFound = true;
                break;
            }
        
        }
        // fails if course name doesn't exist 
        if (courseFound==false){
            System.out.println("Course not found.");
        }
    }

    // withdraw from course 
    public void withdraw(String courseNames, int sectionNum, String fName, String lName) {
        boolean courseFound = false;
        for(int i = 0; i < courses.size(); i++){
            if (courses.get(i).getCourseNames().equalsIgnoreCase(courseNames)&&courses.get(i).getSectionNum() == sectionNum){
                Course courseWithdrawing = courses.get(i);
                
                // fails if student isn't registered in the specific course
                if(!isRegistered(courseWithdrawing.getId())){
                    System.out.println("You aren't enrolled in this course.");
                }
                // success! 
                else{
                    courseWithdrawing.removeStudent(new Student(lName, courseNames, fName, lName));
                    registered.remove(courseWithdrawing);
                    System.out.println("You are now successfully withdrawn from "+ courseNames); 
                }

                courseFound = true;
                break;

            }
           
        }
        // fails if course name doesn't exist 
        if(courseFound ==false){
            System.out.println("The course you are trying to register in doesn't exist.");
        
        }
    }

    // view registered courses
    public void viewRegisteredCourses(){
        if (registered.size()==0){
            System.out.println("You are not reigstered in any courses");
        }
        else{
            System.out.println("You are registered in: ");
            System.out.println();
            for(Course registeredCourses: registered){
                System.out.print(registeredCourses.getCourseNames()+", ");
            }
            System.out.println();
        }
    }

    // create a method to see if a student is already registered for a class 
    public boolean isRegistered(String id){
        for (int i = 0; i < registered.size(); i++){
            if (registered.get(i).getId().equals(id)){
                return true;
            }
        }
        return false;
    }


    public static ArrayList<String> getCorrespondingLastNames(ArrayList<String> firstNames, ArrayList<Student> students) {
        ArrayList<String> lastNames = new ArrayList<>();
        for (String firstName : firstNames) {
            for (Student student : students) {
                if (student.getfName().equals(firstName)) {
                    lastNames.add(student.getlName());
                    break; // Move to the next first name
                }
            }
        }
        return lastNames;
    }
}
