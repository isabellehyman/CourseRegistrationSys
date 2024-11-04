import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class RegistrationSys {

	public static Scanner input = new Scanner (System.in);
	// define ArrayList 
	public static ArrayList <Course> courses = new ArrayList <Course>();
	public static Admin admin = new Admin("Admin", "Admin001", "Admin", "User");

	public static void main (String[] args){
		// name of file to open
		String fileName = "CourseList.csv";
		
		// reference one line at a time 
		String line = null;
				
		// define ArrayList 
		//ArrayList <Course> courses = new ArrayList <Course>();
		
		try {
			// file reader reads text files in the default encoding 
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader (fileReader);
			// skip the first line 
			bufferedReader.readLine();

			while((line = bufferedReader.readLine())!=null) {

				//split by commas
				// create an array of strings 
				String[] data = line.split(",");
				
				Course course = new Course(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), new ArrayList<String> (), data[5],Integer.parseInt(data[6]), data[7]);
				courses.add(course);
			}
			
			bufferedReader.close();
		}
		
		// exception handling 
		catch(FileNotFoundException ex) {
			System.out.println("File not found");
			
		}

		catch (IOException ex) {
			System.out.println("IO Exception");
		}
		getInfo();
		while(true){
			// Start with 3 options 
			System.out.println();
			System.out.println("Welcome to the Course Registration System!");
			System.out.println("Select an option(1-3): ");
			System.out.println("1. Student login");
			System.out.println("2. Admin login");
			System.out.println("3. Exit");
			System.out.print("Enter your choice: ");
			int option = input.nextInt();
			input.nextLine();

			if(option == 1){
				studentLogin();

			}
			else if(option == 2){
				adminLogin();
			}

			else if(option == 3){
				//saveData();
				System.out.println("Goodbye!");
				saveInfo();
				break;
			}
			else{
				System.out.println("Invalid option");
				System.out.println();
			}
		}


	// STUDENT
		
	}

	// student login 
	private static void studentLogin(){
		System.out.println();
		System.out.println("Student Login...");
		System.out.print("Enter your first name: ");
		String fName = input.nextLine();
		System.out.print("Enter your last name: ");
		String lName = input.nextLine();
		System.out.print("Enter your username: ");
		String username = input.nextLine();
		System.out.print("Enter your password: ");
		String password = input.nextLine();
		System.out.println();
		System.out.println("Logging in student...");

		Student student = new Student(username, password, fName, lName);

		// Student menu 
		while(true){
			System.out.println();

			System.out.println("Student menu: ");
			System.out.println("1. View all courses");
			System.out.println("2. View all courses that have space");
			System.out.println("3. Register for a course");
			System.out.println("4. Withdraw from a course");
			System.out.println("5. View all enrolled courses");
			System.out.println("6. Exit");
			System.out.print("Enter your choice: ");


			int option = input.nextInt();
			input.nextLine();
			System.out.println();

			student.setCourses(courses);
			if(option ==1){
				student.viewCourses();

			}
			else if(option ==2){
				student.viewNotFull();

			}
			else if(option ==3){
				System.out.println("Register for a course: ");
				System.out.println();
				System.out.print("Course name: ");
				String courseName = input.nextLine();
				System.out.print("Section number: ");
				int sectionNum = input.nextInt();
				input.nextLine();
				System.out.println();
				student.registerStudent(courseName, sectionNum,fName, lName);
				
			}
			else if(option ==4){
				System.out.print("Withdraw from a course: ");
				System.out.println();
				System.out.print("Course name: ");
				String courseName = input.nextLine();
				System.out.print("Section number: ");
				int sectionNum = input.nextInt();
				input.nextLine();
				System.out.println();
				student.withdraw(courseName, sectionNum, fName, lName);
				
			}
			else if(option ==5){
				student.viewRegisteredCourses();
			}
			else if(option ==6){
				System.out.println("Logging student out...");
				saveInfo();
				break;
			}
			else{
				System.out.println("Invalid option. ");
			}
		}
	}

	// Admin login 

	private static void adminLogin(){
		
		while(true){
			System.out.print("Enter admin username: ");
			String username = input.next();
			System.out.print("Enter admin Password: ");
			String pw = input.next();

			if(username.equals("Admin")&&pw.equals("Admin001")){
				System.out.println("Logging you in...");
				break;
			}
			else{
				System.out.println("Invalid username or password, please try again. ");
			}
		}
	

		admin.setCourses(courses);

		// admin menu 
		while(true){
			System.out.println();
			System.out.println("Admin menu: ");
			System.out.println("1. Create course  ");
			System.out.println("2. Delete course");
			System.out.println("3. Edit course");
			System.out.println("4. Display course info");
			System.out.println("5. Register a student");
			System.out.println("6. View all courses");
			System.out.println("7. View all full courses");
			System.out.println("8. Write full courses to a file");
			System.out.println("9. View students in a class");
			System.out.println("10. View all classes a student is in");
			System.out.println("11. Sort courses by number of students registered ");
			System.out.println("12. Exit");
			System.out.print("Enter your choice: ");
			int option = input.nextInt();
			input.nextLine();
			System.out.println();



			if (option == 1){
				System.out.println("Creating a new course: ");
				System.out.println("Enter a course name: ");
				String courseName = input.nextLine();
				System.out.println("Enter course ID: ");
				String id = input.nextLine();
				System.out.println("Enter the max capacity of course: ");
				int max = input.nextInt();
				input.nextLine();
				System.out.println("Enter the number of students registered in this course: ");
				int numRegistered = input.nextInt();
				input.nextLine();
				System.out.println("Enter the course instructor: ");
				String instructor = input.nextLine();
				System.out.println("Enter the section number: ");
				int sectionNum = input.nextInt();
				input.nextLine();
				System.out.println("Enter the course location: ");
				String location = input.nextLine();

				admin.createCourse(courseName, id, max, numRegistered,new ArrayList<>(), instructor, sectionNum, location);
				System.out.println("Course created.");
			}

			else if(option == 2){
				System.out.println("Deleting a course: ");
				System.out.println("Enter the course ID to delete: ");
				String id = input.nextLine();
				admin.deleteCourse(id);
			}
		

			else if(option == 3){
				// initialize variables 
				int newMax = 0;
				String newInstructor = "";
				int newSectionNum = 0;
				int newNumRegistered = 0;
				String newLocation = "";


				System.out.println("Course editor... ");
				System.out.print("Enter a course ID to edit: ");
				String id = input.nextLine();
				System.out.print("Enter the section number of the course: ");
				int sectionNum = input.nextInt();
				
				boolean courseFound = false;
				while(courseFound == false){
					for(int i = 0; i < courses.size(); i++){
						if(courses.get(i).getId().equalsIgnoreCase(id)&&courses.get(i).getSectionNum()==sectionNum){
							 newMax = courses.get(i).getMax();
							 newInstructor = courses.get(i).getInstructor();
							 newSectionNum = courses.get(i).getSectionNum();
							 newNumRegistered = courses.get(i).getNumRegistered();
							 newLocation = courses.get(i).getLocation();
	
							 courseFound = true;
						}
					}

					if(courseFound == false){
						System.out.println("The course you are trying to edit could not be found.");

					}

				}

				int choice = 0;
				while(choice!=6){
					System.out.println("What would you like to edit? ");
					System.out.println("1. Capacity\n2. Number registered\n3. Instructor\n4. Section number\n5. Location\n6. Quit editing");
					choice = input.nextInt();
					if(choice == 1){
						System.out.print("Enter the new capacity for this course: ");
						input.nextLine();

						 newMax = input.nextInt();
					}
					else if(choice == 2){
						System.out.print("Enter the new number of students registered: ");
						input.nextLine();

						 newNumRegistered = input.nextInt();

					}

					else if(choice == 3){
						System.out.print("Enter the name of the new instructor: ");
						 input.nextLine();
						 newInstructor = input.nextLine();
						 

					}

					else if(choice == 4){
						System.out.print("Enter the new section number: ");
						input.nextLine();

						 newSectionNum = input.nextInt();
					
		
					}
					else if(choice == 5){
						System.out.print("Enter the new location: ");
						input.nextLine();

						 newLocation = input.nextLine();
					}
					else if(choice == 6){
						System.out.println("Done editing, returning to main menu...");
		
					}

					else{
						System.out.println("Invalid option.");
					}

				}
				
				if (courseFound == true){
					admin.editCourse( id, sectionNum,  newMax,  newNumRegistered,  newInstructor,  newSectionNum,  newLocation);

				}
			}
			else if(option == 4){
				System.out.println("Displaying course info: ");
				System.out.println();
				System.out.print("Enter the course ID to display: ");
				String id = input.nextLine();
				admin.displayInfo(id);
			}
			// FIX THIS
			else if(option == 5){
				System.out.println();
				System.out.println("Enter student's first name to register: ");
				String fName = input.next();
				System.out.println("Enter student's last name to register: ");
				String lName = input.next();
				System.out.println("Enter student's username to register: ");
				String user = input.next();
				System.out.println("Enter student's password to register: ");
				String password = input.next();
				
				Student studentToRegister = new Student(user, password, fName, lName);
				
			


				admin.registerStudent(studentToRegister);
				System.out.println();

			}

			else if(option == 6){
				admin.viewCourses();
			}

			
			else if(option == 7){

				if(admin.viewFull().size()==0){
					continue;
				}
				else{
					for(int i = 0; i<admin.viewFull().size(); i++){
						System.out.println("Course name: " + admin.viewFull().get(i).getCourseNames()+"\nSection number: "+admin.viewFull().get(i).getSectionNum());
						System.out.println();
					}
				}

			}

			else if(option == 8){
				System.out.println("Writing full courses to a file...");
				admin.writeFullCoursesToFile("FullCourses.csv");
			}
			else if(option == 9){
				System.out.println("Enter the ID of the course you would like to view the students in: ");
				String id = input.nextLine();
				System.out.println("Printing the names of the students enrolled in this course... ");
				ArrayList<String> students = admin.viewStudentsInClass(id);

				for(String student:students){
					System.out.println(student);
				}
				
			}
			else if(option == 10){
				System.out.println("Viewing the courses of a student...");
				System.out.print("Enter student's first name: ");
				String fName = input.next();
				System.out.print("Enter student's last name: ");
				String lName = input.next();
				System.out.print("Enter student's username: ");
				String user = input.next();
				System.out.print("Enter student's password: ");
				String pass = input.next();
				Student student = new Student(user, pass, fName, lName);
				ArrayList<Course> studentCourses = admin.viewRegistered(student);
				if (studentCourses.size() == 0) {
					System.out.println("This student is not currently registered in any classes.");
				} else {
					System.out.println("This student is registered in: ");
					for (int i = 0; i < studentCourses.size(); i++) {
						System.out.println(studentCourses.get(i).getCourseNames() + " ");
					}
				}
			}
			else if(option == 11){
				admin.sortByNumber();
				System.out.println("Course list is now sorted from least to most students enrolled.");
			}
			else if(option == 12){
				System.out.println("Logging admin out...");
				saveInfo();
				break;
			}
			else{
				System.out.println("Invalid option. ");
			}
		}
	}

	// implement serialization 
	private static void saveInfo(){
		try{
			FileOutputStream fos = new FileOutputStream("Data.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(courses);
			oos.close();
			fos.close();
			System.out.println("Serialization complete");
		} 
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}


	//  deserialization 

	private static void getInfo(){

		try{
			FileInputStream fis = new FileInputStream("Data.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			courses = (ArrayList<Course>) ois.readObject(); 
			ois.close();
			fis.close();

		}

		catch(IOException ioe){
			ioe.printStackTrace();
		}

		catch(ClassNotFoundException cnfe){
			System.out.println("Class not found");
			cnfe.printStackTrace();
		}
	}
}

