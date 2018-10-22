import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.EOFException;

//close scanner when exit
public class University{
	private static String name;
	private static ArrayList<Course> courses = new ArrayList<Course>();
	private static ArrayList<Student> students = new ArrayList<Student>();
	private static ArrayList<Professor> profs = new ArrayList<Professor>();
	private static Boolean isQuit = false;
	/*public University(String name){
		this.name = name;
	}*/


	public static Boolean ifQuit(){
		return isQuit;
	}

	public static String getName(){
		return name;
	}

	public static void printStudent(){
		System.out.println("          =========List of students========");
			for (Student s : students){
				System.out.printf("Student name: %s, Matric Number: %s\n", s.getName(), s.getMatric());
		}
		System.out.printf("\n\n\n");
	}

	public static void printCourse(){
		System.out.println("      =========List of course==========");
		for (Course c : courses){
				System.out.println("Course code: "+c.getCode() + ", Course name: "
					+c.getName() + ", Number of tutorial index(s): " + c.getSizeTut() +", Number of lab index(s): " 
					+ c.getSizeLab() +", Coordiantor: " + c.getCoordinator().getName() + ", Vacancy: " + c.getVacancy());
		}
		System.out.printf("\n\n\n");
	}

	public static void printProfessor(){
		System.out.println("          ==========Professors=========");
		for (Professor p : profs){
			System.out.println("Name: "+p.getName() + ", ID: "+p.getID());
		}
	}

	public static int addCourse(){
		Scanner sc = new Scanner(System.in);
		String ccode = "";
		String cname = "";
		try{
			System.out.print("Enter course code: ");
			ccode = sc.next();
			System.out.print("Enter course name: ");
			cname = sc.next();
		} catch(Exception e){
			e.printStackTrace();
		}
		Boolean tut = true;
		Boolean lab = true;
		Professor prof = new Professor();
		int choice1;
		int choice2;
		int vacancy = 0;
		Boolean pflag = false;
		Boolean vflag = false;
		try {
			do {
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
				System.out.print("Enter the coordinator of your choice by ID: ");
				String pid = sc.next();
				for (Professor p: profs){
					if(pid.equals(p.getID())){
						prof = p;
						pflag = true;
						break;
					}
				}
			} catch(InputMismatchException e){
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
			} catch(Exception e){
				System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
			}
			if (!pflag) System.out.println(">>>>>>>>>>No match found!<<<<<<<<<<");
		} while (!pflag);
		do {
			try {
				System.out.print("Enter vacancy for the course: ");
				vacancy = sc.nextInt();
				sc.nextLine();
				vflag = true;
			} catch(InputMismatchException e){
				System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<<");
			} catch (Exception e){
				e.printStackTrace();
			}
		} while (!vflag);
		Course cour = new Course(ccode, cname, tut, lab, prof, vacancy);
		for (Course c:courses){
			if (c.equals(cour)) return 1;
		}
		courses.add(cour);
		return 0;
	}

	public static int addStudent(){
		Scanner sc = new Scanner(System.in);
		String smatric;
		String sname;
		try{
			System.out.print("Enter student matric: ");
			smatric = sc.nextLine();
			System.out.print("Enter student name: ");
			sname = sc.nextLine();
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		Student stu = new Student(sname, smatric);
		if (students != null){
			for (Student s:students){
				if (s.equals(stu)) return 1;
			}
		}
		students.add(stu);
		return 0;
	}

	public static void app(String name) throws InputMismatchException{
		Scanner sc = new Scanner(System.in);
		String profFile = "Professor.dat";
		String stuFile = "Student.dat";
		String courseFile = "Course.dat";
		///temporary variables for checking
		int choice;
		String matric;
		Student stemp = new Student();
		Boolean sflag = false;
		String code;
		Course ctemp = new Course();
		Boolean cflag = false;
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
			System.out.println("9.Print course stats/Print course");
			System.out.println("10.Print student transcript/Print student");
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
				case 1:
					int check = addStudent();
					if (check ==1){
						System.out.println(">>>>>>>>>>Student already exist!<<<<<<<<<<");
					}
					else if (check ==0){
						System.out.println(">>>>>>>Add student successfully!<<<<<<<<<");
					}
					printStudent();
					System.out.println("\n\n\n\n");
					break;
				case 2:
					check = addCourse();
					if (check ==1){
						System.out.println(">>>>>>>>>>Course already exist!<<<<<<<<<<");
					}
					else if (check ==0){
						System.out.println(">>>>>>>>>>Add course successfully<<<<<<<<<<");
					}
					printCourse();
					System.out.println("\n\n\n\n");
					break;
				case 3:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					matric = sc.next();
					for (Student s :students){
						if (s.getMatric().equals(matric)) {
							stemp = s;
							sflag = true;
						}
					}
					if (sflag == false){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Student " + stemp.getName() + "\n");
					printCourse();
					System.out.print("Enter course code: ");						
					code = sc.next();
					for (Course c : courses){
						if (c.getCode().equals(code)) {
							ctemp = c;
							cflag = true;
						}	
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					if (!ctemp.includeTut() && !ctemp.includeLab()){ // course with no tut and lab
						check = stemp.addCourse(ctemp); //use the appropriate add course version 
						if (check == 0){ 
							System.out.println(">>>>>>>>>>Student " +
					  stemp.getName() + " added successfully to " + ctemp.getCode() + "<<<<<<<<<<");
						}
						else if (check == 2){
							System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");				
						}
						else if (check == 3){
							System.out.println(">>>>>>>>>>Student " + stemp.getName() +
								" already registered to " + ctemp.getCode() + " <<<<<<<<<<");
						}
					}
					else { // Enter for index
						ctemp.printIndex();
						System.out.print("Enter index to register: ");
						String intmp = sc.next();
						check = stemp.addCourse(ctemp, intmp);
						if (check == 0) {
							System.out.println(">>>>>>>>>>Student " +
							stemp.getName() + " added successfully to index " + intmp + " of " + ctemp.getCode() + " <<<<<<<<<<");
						}
						else if (check == 1){
							System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
						}
						else if (check == 2){
							System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
						}
						else if (check == 3){
							System.out.println(">>>>>>>>>>Student " + stemp.getName() +
								" already registered to " + ctemp.getCode() + " <<<<<<<<<<");
						}
					}
					break;
				case 4:
					System.out.println("\n\n");
					System.out.println("==========================================");
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.print("Enter course code: ");						
					String cwtcode = sc.next();
					Course cwt = new Course();
					cflag = false;
					for (Course c : courses){
						if (c.getCode().equals(cwtcode)){
							cwt = c;
							cflag = true;
							break;
						}
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Course " + cwt.getCode() +" have " + cwt.getVacancy() + " free slots!");
					cwt.printIndex();
					System.out.println("\n\n\n\n");
					break;
				case 5:
					int pchoice =0;
					System.out.println("\n\n");
					System.out.println("==========================================");
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.print("Enter course code: ");						
					cwtcode = sc.next();
					cwt = new Course();
					cflag = false;
					for (Course c : courses){
						if (c.getCode().equals(cwtcode)){
							cwt = c;
							cflag = true;
							break;
						}
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					if (!cwt.includeTut() && !cwt.includeLab()){
						cwt.printStudent();
						System.out.println("\n\n\n");
						break;
					}
					try {
						System.out.println("1.Print students by lecture");
						System.out.println("2.Print students by tutorial/lab");
						System.out.print("Enter choice: ");
						pchoice = sc.nextInt();
					} catch(InputMismatchException e){
						System.out.println(">>>>>>>>>>Invalid Input!<<<<<<<<<");
						break;
					} catch(Exception e){
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<");
						break;
					}
					switch (pchoice){
						case 1:
							cwt.printStudent();
							break;
						case 2:
							cwt.printIndex();
							System.out.print("Enter index to print students: ");
							String indextmp = sc.next();
							cwt.printStudentIndex(indextmp);
							break;
						default:
						System.out.println(">>>>>>>>>>Invalid choice!<<<<<<<<<<");
						break;
					}
					System.out.println("\n\n\n");
					break;
				case 6:
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					printCourse();
					System.out.print("Enter course code: ");						
					cwtcode = sc.next();
					cwt = new Course();
					cflag = false;
					for (Course c : courses){
						if (c.getCode().equals(cwtcode)){
							cwt = c;
							cflag = true;
							break;
						}
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					do {
						check = cwt.addWeightage();
						if (check ==2) System.out.println(">>>>>>>>>>Coursework do not add up to 100 percent<<<<<<<<<<<");
					} while (check !=0);
					System.out.println(">>>>>>>>>>Course assesment components weightage added successfully!<<<<<<<<<<");
					System.out.println("\n\n\n\n");
					break;	
				case 7:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					matric = sc.next();
					sflag = false;
					for (Student s :students){
						if (s.getMatric().equals(matric)) {
							stemp = s;
							sflag = true;
						}
					}
					if (sflag == false){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Student " + stemp.getName() + "\n");
					printCourse();
					System.out.print("Enter course code: ");						
					code = sc.next();
					ctemp = new Course();
					cflag = false;
					for (Course c : courses){
						if (c.getCode().equals(code)) {
							ctemp = c;
							cflag = true;
						}	
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					check = stemp.addCourseworkMark(ctemp);
					if (check == 0){
						System.out.println(">>>>>>>>>>Coursework marks of student " + stemp.getName() 
							+ " added successfully for course " 
							+ ctemp.getCode() + "<<<<<<<<<<");
					}
					else if (check == 1){
						System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
					}
					else if (check == 2){
						System.out.println(">>>>>>>>>>Student " + stemp.getName() + " not registered to course " 
							+ ctemp.getCode() + "<<<<<<<<<<");
					}
					else if (check == 3){
						System.out.println(">>>>>>>>>>Coursework marks of student " + stemp.getName() + " already added to course " +
							ctemp.getCode());
					}
					else if (check == 4){
						System.out.println(">>>>>>>>>>Coursework weightage for course "+ ctemp.getCode() 
							+ " has not been added<<<<<<<<<<");
					}
					else if (check == -1){
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
					}
					System.out.println("\n\n\n");
					break;
				case 8:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					matric = sc.next();
					sflag = false;
					for (Student s :students){
						if (s.getMatric().equals(matric)) {
							stemp = s;
							sflag = true;
						}
					}
					if (sflag == false){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Student " + stemp.getName() + "\n");
					printCourse();
					System.out.print("Enter course code: ");						
					code = sc.next();
					ctemp = new Course();
					cflag = false;
					for (Course c : courses){
						if (c.getCode().equals(code)) {
							ctemp = c;
							cflag = true;
							break;
						}	
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					check = stemp.addExamMark(ctemp);
					if (check == 0){
						System.out.println(">>>>>>>>>>Exam marks of student " + stemp.getName() 
							+ " added successfully for course " 
							+ ctemp.getCode() + "<<<<<<<<<<");
					}
					else if (check == 1){
						System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
					}
					else if (check == 2){
						System.out.println(">>>>>>>>>>Student " + stemp.getName() + " not registered to course " 
							+ ctemp.getCode() + "<<<<<<<<<<");
					}
					else if (check == 3){
						System.out.println(">>>>>>>>>>Exam marks of student " + stemp.getName() + " already added to course " +
							ctemp.getCode() + "<<<<<<<<<<");
					}
					else if (check == -1){
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
					}
					System.out.println("\n\n\n");
					break;
				case 9:
					printCourse();
					break;
				case 10:
					printStudent();
					break;
				case 11:
					try {
						System.out.println("Saving data ......");
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
				case 12:
					try {
						System.out.println("Saving data ......");
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
					isQuit = true;
					System.out.println("SCRAME closing ......");
					System.out.println("\n\n\n\n");
					break;
			
				case 13:
					isQuit = true;
					System.out.println("SCRAME closing ......");
					System.out.println("\n\n\n\n");
					break;
				default:
					break;
			}
		} while(choice <12 && choice >0);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);		
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