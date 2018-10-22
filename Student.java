import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;


public class Student implements Serializable{
	private String name;
	private String matric;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private Map<Course, Integer> examGrade = new HashMap<Course, Integer>(); //{Course: grade}
	private Map<Course, Map<String, Integer>> courseworkGrade = new HashMap<Course, Map<String, Integer>>(); //{Course: {Coursework: Grade}}
	private static final long serialVersionUID = -3914670736074682579L;

	public Student(String name, String matric){
		this.name = name;
		this.matric = matric;
	}

	public Student(){};

	public String getName(){
		return name;
	}

	public String getMatric(){
		return matric;
	}

	public int addCourse(Course course){
		int check =0;
		check = course.addStudent(this); 
		if (check == 0){
			courses.add(course);
			return 0;
		}
		else return check;
	}

	public int addCourse(Course course, String index){
		int check =0;
		check = course.addStudent(this, index);
		if (check == 0){
			courses.add(course);
			return 0;
		}
		else {
			return check;
		}
	}

	public int addExamMark(Course course){
		Scanner sc = new Scanner(System.in);
		Boolean cflag = false;
		int grade =0;
		for (Course c : courses){
			if (c.equals(course)){
				cflag = true;
				break;
			}
		}
		if (cflag == false){ //student does not registered this course
			return 2;
		}
		if (examGrade.size() >0){
			for (Course c : examGrade.keySet()){//course already added exam grade
				if (c.equals(course)){
					return 3;
				}
			}
		}
		try {
			System.out.print("Enter exam mark for " + course.getCode() + " /100: ");
			grade = sc.nextInt();
		}catch(InputMismatchException e){
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		examGrade.put(course, grade);
		return 0;
	}

	public int addCourseworkMark(Course course){
		Boolean cflag = false;
		for (Course c : courses){
			if (c.equals(course)){
				cflag = true;
				break;
			}
		}
		if (cflag == false) { //student does not registered this course
			return 2;
		}
		if (courseworkGrade.size() > 0){
			for (Course c : courseworkGrade.keySet()){ //course already added grade
				if (c.equals(course)){
					return 3;
				}
			}
		}
		Map<String, Integer> cw = new HashMap<String, Integer>();
		Scanner sc = new Scanner(System.in);
		if (course.getCourseworkWeightage().size() ==0){ //coursework weightage has not been added yet
			return 4;
		}
		for (String c : course.getCourseworkWeightage().keySet()){
			try {
				System.out.printf("Enter mark for %s/100: ", c);
				int mark = sc.nextInt();
				cw.put(c, mark);
			} catch(InputMismatchException e){
				return 1; //invalid input
			} catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		courseworkGrade.put(course, cw);
		}
		return 0;
	}

	public double getMark(Course course){
		Boolean cflag = false;//flag for course
		Boolean ccflag = false;//flag fpr exam grade
		Boolean cccflag = false;//flag for course work grade
		Course ctemp = new Course();
		double result =0;

		for (Course c : courses){//check if student registered for this course or not            
			if (c.equals(course)){
				cflag = true;
				break;
			}
		}
		if (cflag == false){ //student does not register this course
			System.out.println(">>>>>>>>>>>>Student " + getName() + " does not register for this course<<<<<<<<<<<");
			return 0;
		}
		for (Course c : examGrade.keySet()){ //Checking if exam grade has been added
			if (c.equals(course)){	//course already added exam grade
				ccflag =true;
				break;
			}	
		}
		if (ccflag == false){
			System.out.println(">>>>>>>>>>>>Exam grade for this course has not been added<<<<<<<<<<<");
			return 0;
		}
		//Calculating exam grade
		int egrade = examGrade.get(course);
		int exam = course.getExamWeightage();
		if (exam == 0){ //course assesment has not been added yet
			System.out.println(">>>>>>>>>>>>Course assesment weightage for this courses has not been added<<<<<<<<<<");
			return 0;
		}
		result += (egrade/100)*exam;

		for (Course c : courseworkGrade.keySet()){ //Checking if coursework grade has been added
			if (c.equals(course)){	//course already added coursework grade
				cccflag =true;
				break;
			}	
		}
		if (cccflag == false){
			System.out.println(">>>>>>>>>>>>Coursework grade for this course has not been added<<<<<<<<<<<");
			return 0;
		}

		//Calculating coursework grade
		Map <String, Integer> courseworkw = course.getCourseworkWeightage(); //getting weightage for coursework from course
		Map <String, Integer> courseworkg = courseworkGrade.get(course);
		Iterator itw = courseworkw.entrySet().iterator();
			while (itw.hasNext()){
				Map.Entry pair = (Map.Entry) itw.next();
				String component = (String) pair.getKey();
				int cgrade = courseworkg.get(component);
				int w = (int) pair.getValue();
				result += (cgrade/100) *w;
			}
		return result;
	}

	public boolean equals(Object o){
		if (o instanceof Student){
			Student p = (Student)o;
			return (getMatric().equals(p.getMatric()));
		}
		return false;
	}
}