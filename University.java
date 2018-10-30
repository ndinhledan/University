import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.EOFException;

//close scanner when exit
public class University{
	private static String name;
	private static List<Course> courses = new ArrayList<Course>(); //list of courses of university
	private static List<Student> students = new ArrayList<Student>();// list of students of university
	private static List<Professor> profs = new ArrayList<Professor>();// list of professor of university
	private static Boolean isQuit = false; //boolean for controlling the app

	/*
		*
		*method to
	*/
	public static Boolean ifQuit(){
		return isQuit;
	}

	public static String getName(){
		return name;
	}

	/*
		*
		*method for printing all students in university
		*
	*/
	public static void printStudent(){
		System.out.println("          =========List of students========");
			for (Student s : students){
				System.out.printf("Student name: %s, Matric Number: %s\n", s.getName(), s.getMatric());
		}
		System.out.printf("\n\n\n");
	}

	/*
		*
		*method to check if an object existed in a list or not
		*
	*/

	public static Boolean exist(Object obj, List list){
		for (Object o : list){
			if (obj.equals(o)) return true;
		}
		return false;
	}

	/*
		*
		*method to check if a student exist already or not
		@param matric: String, used to determine student
		*
	*/

	public static Boolean existStudent(String matric){
		for (Student s : students){
			if (matric.equals(s.getMatric())) return true;
		}
		return false;
	}

	/*
		*
		*method to find student inside the list
		@param matric: String, used to determine student
		@return: Student
		*return the found student
		*if student not found return null
	*/

	public static Student findStudent(String matric){
		for (Student s : students){
			if (matric.equals(s.getMatric())) return s;
		}
		return null;
	}

	/*
		*
		*method to check if a course existed already or not
		@param code: String, used to determine course
		*
	*/

	public static Boolean existCourse(String code){
		for (Course c : courses){
			if (code.equals(c.getCode())) return true;
		}
		return false;
	}

	/*
		*
		*method to find Course inside the list
		@param code: String, used to determine Course
		@return: Course
		*return the found Course
		*if Course not found return null
	*/

	public static Course findCourse(String code){
		for (Course c : courses){
			if (code.equals(c.getCode())) return c;
		}
		return null;
	}

	/*
		*
		*method to check if a professor existed or not
		@param ID: String, used to determine professor
		*
	*/

	public static Boolean existProfessor(String id){
		for (Professor p : profs){
			if (id.equals(p.getID())) return true;
		}
		return false;
	}

	/*
		*
		*method to find Professor inside the list
		@param id: String, used to determine Professor
		@return: Professor
		*return the found Professor
		*if Professor not found return null
	*/

	public static Professor findProfessor(String id){
		for (Professor p : profs){
			if (id.equals(p.getID())) return p;
		}
		return null;
	}

	/*
		*
		*method for printing all courses in university
		*
	*/

	public static void printCourse(){
		System.out.println("      =========List of course==========");
		for (Course c : courses){
				System.out.println("Course code: "+c.getCode() + ", Course name: "
					+c.getName() + ", Number of tutorial index(s): " + c.getSizeTut() +", Number of lab index(s): " 
					+ c.getSizeLab() +", Coordiantor: " + c.getCoordinator().getName() + ", Vacancy: " + c.getVacancy());
		}
		System.out.printf("\n\n\n");
	}

	/*
		*
		*method for printing all course registered to a student
		*@param student: for getting courses
		*
	*/

	public static void printCourse(Student student){
		System.out.println("      =========List of course==========");
		for (Course c : student.getCourse()){
				System.out.println("Course code: "+c.getCode() + ", Course name: "
					+c.getName() + ", Number of tutorial index(s): " + c.getSizeTut() +", Number of lab index(s): " 
					+ c.getSizeLab() +", Coordiantor: " + c.getCoordinator().getName() + ", Vacancy: " + c.getVacancy());
		}
		System.out.printf("\n\n\n");

	}

	/*
		*
		*method for printing all professors in university
		*
	*/

	public static void printProfessor(){
		System.out.println("          ==========Professors=========");
		for (Professor p : profs){
			System.out.println("Name: "+p.getName() + ", ID: "+p.getID());
		}
	}

	/*
		*
		*method for adding new course into courses
		*
	*/

	public static int addCourse(){
		Scanner sc = new Scanner(System.in);
		String ccode = "";
		String cname = "";
		Boolean tut = true;
		Boolean lab = true;
		Professor prof = new Professor();
		int choice1;
		int choice2;
		int vacancy = 0;
		Boolean vflag = false;

		try{
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter course code: ");
			ccode = sc.next();//take in course code
			/*
				*check if course exist
			*/
			if (courses != null){
				if (existCourse(ccode)) return 1;//course existed already
			}
			sc.nextLine(); //clearing input buffer;
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter course name: ");
			cname = sc.nextLine();//take in course name
		} catch(Exception e){
			e.printStackTrace();
		}

		try {
			do {
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.printf("Does %s have tutorial?\n", ccode);
				System.out.println("1.Yes");
				System.out.println("2.No");
				System.out.print("Choice: ");
				choice1 = sc.nextInt();
				switch(choice1){
					case 1:
						tut = true;
						break;
					case 2:
						tut = false;
						break;
					default:
						System.out.println("Invalid choice!");
						break;
				}
			} while (choice1 <1 || choice1 >2);
			do {
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.printf("Does %s have lab?\n", ccode);
				System.out.println("1.Yes");
				System.out.println("2.No");
				System.out.print("Choice: ");
				choice2 = sc.nextInt();
				switch(choice2){
					case 1:
						lab = true;
						break;
					case 2:
						lab = false;
						break;
					default:
						System.out.println(">>>>>>>>>>Invalid choice!<<<<<<<<<<");
						break;
				}
			} while (choice2 <1 || choice2 >2);
		} catch(InputMismatchException e){
			System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<");
			return -1;
		} catch(Exception e){
			System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			return -1;
		}
		printProfessor();
		System.out.println("Choose the course coordiantor");
		do{
			try{ 
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.print("Enter the coordinator of your choice by ID: ");
				String pid = sc.next(); //take in professor id
				prof = findProfessor(pid);
			} catch(InputMismatchException e){
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
			} catch(Exception e){
				System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			}
			if (prof == null) System.out.println(">>>>>>>>>>No match found!<<<<<<<<<<");
		} while (prof == null);
		System.out.println("Professor " + prof.getName());
		do {
			try { // vacancy negative number
				System.out.println("\n\n");
				System.out.println("==========================================");
				System.out.print("Enter vacancy for the course: ");
				vacancy = sc.nextInt();//take in vacancy
				if (vacancy < 1){
					throw new InputMismatchException("Negative vacancy");
				}
				vflag = true;
			} catch(InputMismatchException e){
				sc.nextLine();
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
			} catch (Exception e){
				e.printStackTrace();
			}
		} while (!vflag);
		Course cour = new Course(ccode, cname, tut, lab, prof, vacancy); //create new course
		courses.add(cour); // add to courses
		return 0;
	}

	/*
		*
		*method for adding new student to students
		*
	*/

	public static int addStudent(){
		Scanner sc = new Scanner(System.in);
		String smatric;
		String sname;
		try{
			System.out.println("\n\n");
			System.out.println("==========================================");
			System.out.print("Enter student matric: ");
			smatric = sc.next(); //take in matric
			if (smatric.length() !=9){
				return 2;//invalid matric (length of matric =9)
			}
			if (students != null){ //check for duplicate student
				if (existStudent(smatric)) return 1; //duplicate student
			}
			sc.nextLine();//clearing input buffer
			System.out.println("\n\n");
			System.out.println("==========================================");	
			System.out.print("Enter student name: ");
			sname = sc.nextLine();//take in student name
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		Student stu = new Student(sname, smatric);//create new student
		students.add(stu);//add to students
		return 0;
	}

	/*
		*
		*University app
		*
	*/

	public static void app(String name) throws InputMismatchException{
		Scanner sc = new Scanner(System.in);
		/*
			*data files
		*/
		String profFile = "Professor.dat"; 
		String stuFile = "Student.dat";
		String courseFile = "Course.dat";
		int choice;//variable for switch
		do {
			System.out.printf("========Welcome to the %s University SCRAME=======\n", name);
			System.out.println("1.Add a student");
			System.out.println("2.Add a course");
			System.out.println("3.Register student for a course");
			System.out.println("4.Check available slot in a class");
			System.out.println("5.Print student list by lecture, tutorial, lab for a course");
			System.out.println("6.Enter course assesment components weightage");
			System.out.println("7.Enter coursework mark - inclusize of its components");
			System.out.println("8.Enter exam mark");
			System.out.println("9.Print course stats");
			System.out.println("10.Print student transcript");
			System.out.println("11.Save");
			System.out.println("12.Save & Exit");
			System.out.println("13.Exit without saving");
			System.out.print("Enter choice: ");
			try {
				choice = sc.nextInt();
			} catch (InputMismatchException e){
				throw new InputMismatchException(">>>>>>>>>>Invalid Input!<<<<<<<<<<<");
			} catch (Exception e){
				throw e;
			}
			switch (choice){

				///////////////////////////////////////////////////
				///Add a student
				////////////////////////////////////////////////////
				case 1:
					int check1 = addStudent();
					if (check1 ==1){
						System.out.println(">>>>>>>>>>Student already exist!<<<<<<<<<<");
					}
					else if (check1 ==0){
						System.out.println(">>>>>>>Add student successfully!<<<<<<<<<");
					}
					else if (check1 ==2){
						System.out.println(">>>>>>>>>>Invalid Matric!<<<<<<<<<<");
					}
					printStudent();
					System.out.println("\n\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Add a course
				////////////////////////////////////////////////////
				case 2:
					int check2 = addCourse();
					if (check2 ==1){
						System.out.println(">>>>>>>>>>Course already exist!<<<<<<<<<<");
					}
					else if (check2 ==0){
						System.out.println(">>>>>>>>>>Add course successfully!<<<<<<<<<<");
					}
					printCourse();
					System.out.println("\n\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Register a student
				////////////////////////////////////////////////////
				case 3: 
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course!<<<<<<<<<<");
						break;
					}
					System.out.println("\n\n");
					System.out.println("==========================================");
					System.out.print("Enter student by matric: ");
					String matric3 = sc.next();//take in matric
					Student stemp3 = new Student();
					
					/*
						*check if student exist
					*/

					stemp3 = findStudent(matric3);
					if (stemp3 == null){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					
					System.out.println("Adding student " + stemp3.getName() + " to a course\n");
					printCourse();
					System.out.println("\n\n");
					System.out.println("==========================================");
					System.out.print("Enter course code: ");						
					String code3 = sc.next();//take in course code
					Course ctemp3 = new Course();
					Boolean cflag3 = false;
					
					/*
						*check if course exist
					*/

					ctemp3 = findCourse(code3);
					if (ctemp3 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}	

					if (!ctemp3.includeTut() && !ctemp3.includeLab()){ // course with no tut and lab
						int check3 = stemp3.addCourse(ctemp3); //register a course to student without index 
						if (check3 == 0){ //success
							System.out.println(">>>>>>>>>>Student " +
					  stemp3.getName() + " added successfully to " + ctemp3.getCode() + "<<<<<<<<<<");
						}
						else if (check3 == 2){//no vacancy
							System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");				
						}
						else if (check3 == 3){//student already registered
							System.out.println(">>>>>>>>>>Student " + stemp3.getName() +
								" already registered to " + ctemp3.getCode() + " <<<<<<<<<<");
						}
						else if (check3 == 4){//cannot register student
							System.out.println(">>>>>>>>>>Course has not added coursework weightage!<<<<<<<<<");
							System.out.println(">>>>>>>>>>Please add coursework weightage for this course first!<<<<<<<<<");
						}
					}
					/*
						*course with tut and lab
						*register index
					*/
					else {
						ctemp3.printIndex();
						System.out.println("\n\n");
						System.out.println("==========================================");
						System.out.print("Enter index to register: ");
						String intmp3 = sc.next();//take in index
						int check3 = stemp3.addCourse(ctemp3, intmp3);//register course to student with index
						if (check3 == 0) {//success
							System.out.println(">>>>>>>>>>Student " +
							stemp3.getName() + " added successfully to index " + intmp3 + " of " + ctemp3.getCode() + " <<<<<<<<<<");
						}
						else if (check3 == 1){//wrong index
							System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
						}
						else if (check3 == 2){//no vacancy 
							System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
						}
						else if (check3 == 3){//student already registered
							System.out.println(">>>>>>>>>>Student " + stemp3.getName() +
								" already registered to " + ctemp3.getCode() + " <<<<<<<<<<");
						}
						else if (check3 == 4){//cannot add course
							System.out.println(">>>>>>>>>>Course has not added coursework weightage!<<<<<<<<<");
							System.out.println(">>>>>>>>>>Please add coursework weightage for this course first!<<<<<<<<<");
						}
					}
					System.out.println("\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Check available slots
				////////////////////////////////////////////////////
				case 4:
					System.out.println("\n\n");
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("\n\n");
					System.out.println("==========================================");
					System.out.print("Enter course code: ");						
					String code4 = sc.next();//take in course code
					Course ctemp4 = new Course();
					/*
						*check if course exist
					*/

					ctemp4 = findCourse(code4);
					if (ctemp4 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Course " + ctemp4.getCode() +" have " + ctemp4.getVacancy() + " free slots!");
					ctemp4.printIndex();//print indexes and their respective vacancy
					System.out.println("\n\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Print student list
				////////////////////////////////////////////////////
				case 5:
					int choice5 =0;
					System.out.println("\n\n");
					if (courses.size() == 0){ //check if there is any course exist
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("\n\n");
					System.out.println("==========================================");
					System.out.print("Enter course code: ");						
					String code5 = sc.next();//take in course code
					Course ctemp5 = new Course();
					
					/*
						*check if course exist
					*/

					ctemp5 = findCourse(code5);
					if (ctemp5 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}

					if (!ctemp5.includeTut() && !ctemp5.includeLab()){//course printed doesn't have tut and lab
						ctemp5.printStudent();//printing student
						System.out.println("\n\n\n");
						break;
					}
					try {
						System.out.println("1.Print students by lecture");
						System.out.println("2.Print students by tutorial/lab");
						System.out.println("==========================================");
						System.out.print("Enter choice: ");
						choice5 = sc.nextInt();
					} catch(InputMismatchException e){
						System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<");
						break;
					} catch(Exception e){
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
						break;
					}
					switch (choice5){
						case 1://print all student not specific to index
							ctemp5.printStudent();
							break;
						case 2://print student according to index
							ctemp5.printIndex();
							System.out.println("\n\n");
							System.out.println("==========================================");
							System.out.print("Enter index to print students: ");
							String indextmp5 = sc.next();
							ctemp5.printStudent(indextmp5);
							break;
						default:
						System.out.println(">>>>>>>>>>Invalid choice!<<<<<<<<<<");
						break;
					}
					System.out.println("\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Enter coursework weightage
				////////////////////////////////////////////////////
				case 6: 
					if (courses.size() == 0){//check if any course exist
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					printCourse();
					System.out.println("\n\n");
					System.out.println("==========================================");		
					System.out.print("Enter course code: ");						
					String code6 = sc.next();//take in course code
					Course ctemp6 = new Course();
					int check6;

					/* 
						*check if course exist
					*/

					ctemp6 = findCourse(code6);
					if (ctemp6 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}

					do {
						check6 = ctemp6.addWeightage();//adding weightage
						if (check6 == 2) System.out.println(">>>>>>>>>>Coursework do not add up to 100 percent<<<<<<<<<<<");
					} while (check6 !=0 && check6 != 3 && check6 != 4);
					if (check6 == 0) {
						System.out.println(">>>>>>>>>>Course assesment components weightage added successfully!<<<<<<<<<<");
					}
					if (check6 == 3) System.out.println(">>>>>>>>>>Coursework weightage for "+ code6 +" is not updated!<<<<<<<<<<");
					if (check6 == 4) System.out.println(">>>>>>>>>>Coursework wieghtage for " + code6 +" cannot be updated because there are students registered already");
						
					System.out.println("\n\n\n\n");
					break;	

				///////////////////////////////////////////////////
				///Enter coursework marks
				////////////////////////////////////////////////////	
				case 7:
					if (courses.size() == 0){//check if any course exist
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.println("\n\n");
					System.out.println("==========================================");
					System.out.print("Enter student by matric: ");
					String matric7 = sc.next();//take in student matric
					Student stemp7 = new Student();
					Boolean sflag7 = false;

					/*
						*check if student exist
					*/
					stemp7 = findStudent(matric7);
					if (stemp7 == null){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}

					System.out.println("Student " + stemp7.getName() + "\n");
					printCourse(stemp7);//print courses registered to student

					System.out.println("\n\n");
					System.out.println("==========================================");
					System.out.print("Enter course code: ");						
					String code7 = sc.next();//take in course code
					Course ctemp7 = new Course();

					/*
						*check if course exist
					*/

					ctemp7 = findCourse(code7);
					if (ctemp7 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n");
						break;
					}

					int check7 = stemp7.addCourseworkMark(ctemp7);//adding coursework mark
					if (check7 == 0){ //success
						System.out.println(">>>>>>>>>>Coursework marks of student " + stemp7.getName() 
							+ " added successfully for course " 
							+ ctemp7.getCode() + "<<<<<<<<<<");
						ctemp7.updateStudent(stemp7);//updating student record inside course
					}
					else if (check7 == 1){
						System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
					}
					else if (check7 == 2){ //student not registered to course
						System.out.println(">>>>>>>>>>Student " + stemp7.getName() + " not registered to course " 
							+ ctemp7.getCode() + "<<<<<<<<<<");
					}
					else if (check7 == 3){ // mark already added
						System.out.println(">>>>>>>>>>Coursework marks of student " + stemp7.getName() + " already added to course " +
							ctemp7.getCode());
					}
					else if (check7 == 4){ //no coursework weightage 
						System.out.println(">>>>>>>>>>Coursework weightage for course "+ ctemp7.getCode() 
							+ " has not been added<<<<<<<<<<");
					}
					else if (check7 == -1){//unknown errir
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
					}
					System.out.println("\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Enter exam mark
				////////////////////////////////////////////////////
				case 8:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					String matric8 = sc.next();//take in matric
					Student stemp8 = new Student();

					/*
						*check if student exist
					*/
					stemp8 = findStudent(matric8);
					if (stemp8 == null){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Student " + stemp8.getName() + "\n");
					printCourse(stemp8);//printing course registered to this tudent
					System.out.print("Enter course code: ");						
					String code8 = sc.next();
					Course ctemp8 = new Course();

					/*
						*check if course exist
					*/

					ctemp8 = findCourse(code8);
					if (ctemp8 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n");
						break;
					}
					int check8 = stemp8.addExamMark(ctemp8);//adding exam mark
					if (check8 == 0){ //success
						System.out.println(">>>>>>>>>>Exam marks of student " + stemp8.getName() 
							+ " added successfully for course " 
							+ ctemp8.getCode() + "<<<<<<<<<<");
						ctemp8.updateStudent(stemp8); //updating student record inside course
					}
					else if (check8 == 1){
						System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
					}
					else if (check8 == 2){//student not regitered to course
						System.out.println(">>>>>>>>>>Student " + stemp8.getName() + " not registered to course " 
							+ ctemp8.getCode() + "<<<<<<<<<<");
					}
					else if (check8 == 3){//exam mark already added
						System.out.println(">>>>>>>>>>Exam marks of student " + stemp8.getName() + " already added to course " +
							ctemp8.getCode() + "<<<<<<<<<<");
					}
					else if (check8 == -1){//unknown error
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
					}
					System.out.println("\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Print course stats
				////////////////////////////////////////////////////	
				case 9:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter course code: ");						
					String code9 = sc.next();//take in course code

					/* 
						*check if course exist
					*/

					Course ctemp9 = findCourse(code9);
					if (ctemp9 == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n");
						break;
					}
					ctemp9.printStats();//printing stats
					System.out.println("\n\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Print student transcript
				////////////////////////////////////////////////////
				case 10:
					if (students.size() == 0){
						System.out.println(">>>>>>>>>>No student<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					String matric10 = sc.next();//take in student matric
					
					/*
						*check if student exist
					*/
					Student stemp10 = findStudent(matric10);
					if (stemp10 == null){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					stemp10.printTranscript();//printing student transcript
					System.out.println("\n\n\n\n");
					break;

				///////////////////////////////////////////////////
				///Save
				////////////////////////////////////////////////////
				case 11:
					try {
						System.out.println("Saving data ......");
						/*
							*
							*writing data to files
							*
						*/
						SerializeDB.writeSerializedObject(profFile, profs);
						SerializeDB.writeSerializedObject(stuFile, students);
						SerializeDB.writeSerializedObject(courseFile, courses);
						System.out.println("Data saved successfully");

					}catch(IOException e){
						System.out.println(">>>>>>>>>>File Error<<<<<<<<<<");
					}catch (Exception e){
						e.printStackTrace();
					}
					break;

				///////////////////////////////////////////////////
				///Save and exit
				////////////////////////////////////////////////////
				case 12:
					try {
						System.out.println("Saving data ......");
						/*
							*
							*writing data to files
							*
						*/
						SerializeDB.writeSerializedObject(profFile, profs);
						SerializeDB.writeSerializedObject(stuFile, students);
						SerializeDB.writeSerializedObject(courseFile, courses);
						System.out.println("Data saved successfully");

					}catch(IOException e){
						System.out.println(">>>>>>>>>>File Error<<<<<<<<<<");
					}catch (Exception e){
						e.printStackTrace();
					}
					System.out.println("\n\n\n\n");
					isQuit = true;//stopping program
					System.out.println("SCRAME closing ......");
					System.out.println("\n\n\n\n");
					break;
			
				case 13://exit without saving
					isQuit = true;//stopping program
					System.out.println("SCRAME closing ......");
					System.out.println("\n\n\n\n");
					break;
				default:
					break;
			}
		} while(choice <12 && choice >0);
	}

	public static void init(){
		String profFile = "Professor.dat";
		String stuFile = "Student.dat";
		String courseFile = "Course.dat";
		try { // reading in data from files
			System.out.println("Reading data......");
			profs = (ArrayList<Professor>) SerializeDB.readSerializedObject(profFile);
			students = (ArrayList<Student>) SerializeDB.readSerializedObject(stuFile);
			courses = (ArrayList<Course>) SerializeDB.readSerializedObject(courseFile);
			System.out.println("Data read succssfullly");
		} catch (IOException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();	
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("==========================================================================");
		System.out.println("\n\n\n");
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		University.init();	
		while (!University.ifQuit()){
			try{
				University.app("NTU");
			}
			catch(InputMismatchException e){
				System.out.println(e.getMessage());
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		sc.close();
	}
}