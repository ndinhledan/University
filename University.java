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
	}

	public static void printCourse(){
		System.out.println("      =========List of course==========");
		for (Course c : courses){
				System.out.println("Course code: "+c.getCode() + ", Course name: "
					+c.getName() + " ,Number of tutorial index(s): " + c.getSizeTut() +" ,Number of lab index(s): " 
					+ c.getSizeLab() +" , Coordiantor: " + c.getCoordinator().getName() + " , Vacancy: " + c.getVacancy());
		}
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
					String matric = sc.next();
					Student stemp = new Student();
					Boolean sflag = false;
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
					printCourse();
					System.out.print("Enter course code: ");						
					String code = sc.next();
					Course ctemp = new Course();
					Boolean cflag = false;
					for (Course c : courses){
						if (c.getCode().equals(code)) ctemp = c;
						cflag = true;
					} 
					if (!cflag){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						break;
					}
					if (ctemp.getSizeTut() ==0 && ctemp.getSizeLab() ==0){
						if (stemp.addCourse(ctemp) == 0){
							System.out.println("Student " +
					  stemp.getName() + " added successfully to" + ctemp.getCode());
						}
					}
					else {
						ctemp.printIndex();
						System.out.print("Enter index to register: ");
						String intmp = sc.next();
						if (stemp.addCourse(ctemp, intmp)==0) {
							System.out.println("Student " +
							stemp.getName() + " added successfully to index " + intmp + " of " + ctemp.getCode());
						}
					}
					break;
				case 6:
					if (courses.size() == 0) {
						System.out.println(">>>>>>>>>>No courses<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					printCourse();
					System.out.println("Enter course code: ");						
					String cwtcode = sc.next();
					Course cwt = new Course();
					for (Course c : courses){
						if (c.getCode().equals(cwtcode)) cwt = c;
					} 
					if (cwt == null){
						System.out.println(">>>>>>>>>>No course found<<<<<<<<<<");
						System.out.println("\n\n\n\n");
						break;
					}
					do {
						check = cwt.addWeightage();
					} while (check !=0);
					System.out.println("\n\n\n\n");
					break;	
				case 13:
					isQuit = true;
					System.out.println("SCARME closing ......");
					System.out.println("\n\n\n\n");
					break;
			}
		} while(choice <12 && choice >0);
	}

	public static void main(String[] args) {
		String profFile = "Professor.dat";
		String stuFile = "Student.dat";
		String courseFile = "Course.dat";
		Scanner sc = new Scanner(System.in);
		try {
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