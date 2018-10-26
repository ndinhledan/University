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

	public static void printCourse(Student student){
		System.out.println("      =========List of course==========");
		for (Course c : student.getCourse()){
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
			for (Course c : courses){
				if (c.getCode().equals(ccode)){
					return 1; //course exist already
				}
			}
			System.out.print("Enter course name: ");
			cname = sc.nextLine();
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
			try { // vacancy negative number
				System.out.print("Enter vacancy for the course: ");
				vacancy = sc.nextInt();
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
		Course cour = new Course(ccode, cname, tut, lab, prof, vacancy);
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
			if (smatric.length() !=9){
				return 2;//invalid matric
			}
			if (students != null){
				for (Student s : students){
					if (s.getMatric().equals(smatric)) return 1;
				}
			}
			System.out.print("Enter student name: ");
			sname = sc.nextLine();
		} catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		Student stu = new Student(sname, smatric);
		students.add(stu);
		return 0;
	}

	public static void app(String name) throws InputMismatchException{
		Scanner sc = new Scanner(System.in);
		String profFile = "Professor.dat";
		String stuFile = "Student.dat";
		String courseFile = "Course.dat";
		int choice;
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
				case 3:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course!<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					String matric3 = sc.next();
					Boolean sflag3 = false;
					Student stemp3 = new Student();
					for (Student s :students){
						if (s.getMatric().equals(matric3)) {
							stemp3 = s;
							sflag3 = true;
						}
					}
					if (sflag3 == false){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Adding student " + stemp3.getName() + " to a course\n");
					printCourse();
					System.out.print("Enter course code: ");						
					String code3 = sc.next();
					Course ctemp3 = new Course();
					Boolean cflag3 = false;
					for (Course c : courses){
						if (c.getCode().equals(code3)) {
							ctemp3 = c;
							cflag3 = true;
						}	
					} 
					if (!cflag3){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					if (!ctemp3.includeTut() && !ctemp3.includeLab()){ // course with no tut and lab
						int check3 = stemp3.addCourse(ctemp3); //use the appropriate add course version 
						if (check3 == 0){ 
							System.out.println(">>>>>>>>>>Student " +
					  stemp3.getName() + " added successfully to " + ctemp3.getCode() + "<<<<<<<<<<");
						}
						else if (check3 == 2){
							System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");				
						}
						else if (check3 == 3){
							System.out.println(">>>>>>>>>>Student " + stemp3.getName() +
								" already registered to " + ctemp3.getCode() + " <<<<<<<<<<");
						}
					}
					else { // Enter for index
						ctemp3.printIndex();
						System.out.print("Enter index to register: ");
						String intmp3 = sc.next();
						int check3 = stemp3.addCourse(ctemp3, intmp3);
						if (check3 == 0) {
							System.out.println(">>>>>>>>>>Student " +
							stemp3.getName() + " added successfully to index " + intmp3 + " of " + ctemp3.getCode() + " <<<<<<<<<<");
						}
						else if (check3 == 1){
							System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
						}
						else if (check3 == 2){
							System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
						}
						else if (check3 == 3){
							System.out.println(">>>>>>>>>>Student " + stemp3.getName() +
								" already registered to " + ctemp3.getCode() + " <<<<<<<<<<");
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
					String code4 = sc.next();
					Course ctemp4 = new Course();
					Boolean cflag4 = false;
					for (Course c : courses){
						if (c.getCode().equals(code4)){
							ctemp4 = c;
							cflag4 = true;
							break;
						}
					} 
					if (!cflag4){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Course " + ctemp4.getCode() +" have " + ctemp4.getVacancy() + " free slots!");
					ctemp4.printIndex();
					System.out.println("\n\n\n\n");
					break;
				case 5:
					int choice5 =0;
					System.out.println("\n\n");
					System.out.println("==========================================");
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.print("Enter course code: ");						
					String code5 = sc.next();
					Course ctemp5 = new Course();
					Boolean cflag5 = false;
					for (Course c : courses){
						if (c.getCode().equals(code5)){
							ctemp5 = c;
							cflag5 = true;
							break;
						}
					} 
					if (!cflag5){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					if (!ctemp5.includeTut() && !ctemp5.includeLab()){
						ctemp5.printStudent();
						System.out.println("\n\n\n");
						break;
					}
					try {
						System.out.println("1.Print students by lecture");
						System.out.println("2.Print students by tutorial/lab");
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
						case 1:
							ctemp5.printStudent();
							break;
						case 2:
							ctemp5.printIndex();
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
				case 6:
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					printCourse();
					System.out.print("Enter course code: ");						
					String code6 = sc.next();
					Course ctemp6 = new Course();
					Boolean cflag6 = false;
					int check6;
					for (Course c : courses){
						if (c.getCode().equals(code6)){
							ctemp6 = c;
							cflag6 = true;
							break;
						}
					} 
					if (!cflag6){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					do {
						check6 = ctemp6.addWeightage();
						if (check6 == 2) System.out.println(">>>>>>>>>>Coursework do not add up to 100 percent<<<<<<<<<<<");
					} while (check6 !=0 && check6 != 3);
					if (check6 == 0) System.out.println(">>>>>>>>>>Course assesment components weightage added successfully!<<<<<<<<<<");
					if (check6 == 3) System.out.println(">>>>>>>>>>Coursework weightage for "+ code6 +" has been added already<<<<<<<<<<");
						
					System.out.println("\n\n\n\n");
					break;	
				case 7:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					String matric7 = sc.next();
					Student stemp7 = new Student();
					Boolean sflag7 = false;
					for (Student s :students){
						if (s.getMatric().equals(matric7)) {
							stemp7 = s;
							sflag7 = true;
						}
					}
					if (sflag7 == false){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Student " + stemp7.getName() + "\n");
					printCourse(stemp7);
					System.out.print("Enter course code: ");						
					String code7 = sc.next();
					Course ctemp7 = new Course();
					Boolean cflag7 = false;
					for (Course c : courses){
						if (c.getCode().equals(code7)) {
							ctemp7 = c;
							cflag7 = true;
						}	
					} 
					if (!cflag7){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					int check7 = stemp7.addCourseworkMark(ctemp7);
					if (check7 == 0){
						System.out.println(">>>>>>>>>>Coursework marks of student " + stemp7.getName() 
							+ " added successfully for course " 
							+ ctemp7.getCode() + "<<<<<<<<<<");
					}
					else if (check7 == 1){
						System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
					}
					else if (check7 == 2){
						System.out.println(">>>>>>>>>>Student " + stemp7.getName() + " not registered to course " 
							+ ctemp7.getCode() + "<<<<<<<<<<");
					}
					else if (check7 == 3){
						System.out.println(">>>>>>>>>>Coursework marks of student " + stemp7.getName() + " already added to course " +
							ctemp7.getCode());
					}
					else if (check7 == 4){
						System.out.println(">>>>>>>>>>Coursework weightage for course "+ ctemp7.getCode() 
							+ " has not been added<<<<<<<<<<");
					}
					else if (check7 == -1){
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
					String matric8 = sc.next();
					Student stemp8 = new Student();
					Boolean sflag8 = false;
					for (Student s :students){
						if (s.getMatric().equals(matric8)) {
							stemp8 = s;
							sflag8 = true;
						}
					}
					if (!sflag8){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					System.out.println("Student " + stemp8.getName() + "\n");
					printCourse(stemp8);
					System.out.print("Enter course code: ");						
					String code8 = sc.next();
					Course ctemp8 = new Course();
					Boolean cflag8 = false;
					for (Course c : courses){
						if (c.getCode().equals(code8)) {
							ctemp8 = c;
							cflag8 = true;
							break;
						}	
					} 
					if (!cflag8){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					int check8 = stemp8.addExamMark(ctemp8);
					if (check8 == 0){
						System.out.println(">>>>>>>>>>Exam marks of student " + stemp8.getName() 
							+ " added successfully for course " 
							+ ctemp8.getCode() + "<<<<<<<<<<");
					}
					else if (check8 == 1){
						System.out.println(">>>>>>>>>>>Invalid Input!<<<<<<<<<<<");
					}
					else if (check8 == 2){
						System.out.println(">>>>>>>>>>Student " + stemp8.getName() + " not registered to course " 
							+ ctemp8.getCode() + "<<<<<<<<<<");
					}
					else if (check8 == 3){
						System.out.println(">>>>>>>>>>Exam marks of student " + stemp8.getName() + " already added to course " +
							ctemp8.getCode() + "<<<<<<<<<<");
					}
					else if (check8 == -1){
						System.out.println(">>>>>>>>>>Error!<<<<<<<<<<<<");
					}
					System.out.println("\n\n\n");
					break;
				case 9:
					if (courses.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter course code: ");						
					String code9 = sc.next();
					Course ctemp9 = new Course();
					Boolean cflag9 = false;
					for (Course c : courses){
						if (c.getCode().equals(code9)) {
							ctemp9 = c;
							cflag9 = true;
							break;
						}	
					} 
					if (!cflag9){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					ctemp9.printStats();
					System.out.println("\n\n\n\n");
					break;
				case 10:
					if (students.size() == 0){
						System.out.println(">>>>>>>>>>No course<<<<<<<<<<");
						break;
					}
					System.out.print("Enter student by matric: ");
					String matric10 = sc.next();
					Boolean sflag10 = false;
					Student stemp10 = new Student();
					for (Student s :students){
						if (s.getMatric().equals(matric10)) {
							stemp10 = s;
							sflag10 = true;
						}
					}
					if (sflag10 == false){
						System.out.println(">>>>>>>>>>No student found!!<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					stemp10.printTranscript();
					System.out.println("\n\n\n\n");
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