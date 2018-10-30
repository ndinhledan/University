import java.util.List;
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
	private List<Course> courses = new ArrayList<Course>();
	private Map<String, Integer> examGrade = new HashMap<String, Integer>(); //{Course code: grade} //course 
	private Map<String, Map<String, Integer>> courseworkGrade = new HashMap<String, Map<String, Integer>>(); //{Course code: {Coursework: Grade}} // course -> String course
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

	/*
		*
		*method for registering this student to a course without tut and lab
		*add course to courses arraylist
		@param course: course to which this student is registering to
		@return an exit value
		0: success
		2: no vacancy
		3: student already registered for this course
		4: course cannot be registered because it has not added coursework weightage
		*
	*/

	public int addCourse(Course course){
		int check =0;
		/*
			*
			*calling add student of course
			*add course to student and add student to course
			*
		*/
		check = course.addStudent(this); 
		if (check == 0){
			courses.add(course);
			return 0;
		}
		else return check;
	}

	/*
		*
		*method for registering this student to a course with tut and lab
		*add course to courses arraylist
		@param course: course to which this student is registering
		@param index: index to which this student is registering
		@return an exit value:
		0: success
		1: wrong index passed
		2: index has no vacancy
		3: student already registered for this course
		4: course cannot be registered because it has not added coursework weightage
		*
	*/

	public int addCourse(Course course, String index){
		int check =0;
		/*
			*
			*calling addstudent of this course
			*add this student to the course and add course to this student
			*
		*/
		check = course.addStudent(this, index);
		if (check == 0){
			courses.add(course);
			return 0;
		}
		else {
			return check;
		}
	}

	/*
		*
		@return List<Course>
		*
	*/

	public List<Course> getCourse(){
		return courses;
	}

	/*
		*
		*method to check if this student has registered this course or not
		@param: String code of course
		*
	*/

	public Boolean existCourse(String code){
		for (Course c : courses){
			if (code.equals(c.getCode())) return true;
		}
		return false;
	}

	/*
		*
		*method to find course registered to a student
		@param: String code of course
		@return Course found
		@return null if not found
		*
	*/

	public Course findCourse(String code){
		for (Course c : courses){
			if (code.equals(c.getCode())) return c;
		}
		return null;
	}

	/*
		*
		*method for adding exam mark to this student
		*
	*/
	public int addExamMark(Course course){
		Scanner sc = new Scanner(System.in);
		int grade =0;

		if (!existCourse(course.getCode())) return 2; //student doesnt register this course
		
		if (examGrade.size() >0){
			for (String s : examGrade.keySet()){//course already added exam grade
				if (s.equals(course.getCode())){
					return 3;
				}
			}
		}
		try {
			System.out.print("Enter exam mark for " + course.getCode() + " /100: ");
			grade = sc.nextInt();
			if (grade < 1 || grade > 100){
				throw new InputMismatchException();
			}
		}catch(InputMismatchException e){
			return 1;
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		examGrade.put(course.getCode(), grade);
		return 0;
	}

	/*
		*
		*method for adding coursework mark to this student
		@return int exit value
		0: success
		1: Invalid Input
		2: Student does not register for this course yet
		3: grade of this course for this student has been added already
		4: course weightage for this course has not been added
		*
	*/

	public int addCourseworkMark(Course course){
		if (!existCourse(course.getCode())) return 2; //student does not register this coufse

		if (courseworkGrade.size() > 0){
			for (String s : courseworkGrade.keySet()){ //course already added grade
				if (s.equals(course.getCode())){
					return 3;
				}
			}
		}

		Map<String, Integer> cw = new HashMap<String, Integer>(); //new dictionary to store {component coursework: mark}
		Scanner sc = new Scanner(System.in);
		if (course.getCourseworkWeightage().size() ==0){ //coursework weightage has not been added yet
			return 4;
		}
		for (String c : course.getCourseworkWeightage().keySet()){ //get component name e.g. Essay
			try {
				System.out.printf("Enter mark for %s/100: ", c);
				int mark = sc.nextInt();
				if (mark < 1 || mark > 100){
					throw new InputMismatchException(">>>>>>>>>>Component marks cannot be negative or larger than 100!<<<<<<<<<<\n\n\n");
				}
				cw.put(c, mark);
			} catch(InputMismatchException e){
				System.out.println(e.getMessage());
				return 1; //invalid input
			} catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		courseworkGrade.put(course.getCode(), cw);
		}
		return 0;
	}
	
	/*	
		*
		*Method for getting mark of this student
		*@param course: getting mark of this course for this student
		*@return double value mark of this student
		*@return 0 if error
		*
	*/

 
	public double getMark(Course course){
		Course ctemp = new Course();
		double result =0;

		if (!existCourse(course.getCode())){
			System.out.println(">>>>>>>>>>>>Student " + getName() + " does not register for this course<<<<<<<<<<<");
			return 0;
		}

		if (examGrade.get(course.getCode()) == null){
			System.out.println(">>>>>>>>>>>>Exam grade for this course has not been added<<<<<<<<<<<");
			return 0;
		}
		//Calculating exam grade
		Integer egrade = examGrade.get(course.getCode());
		Integer exam = course.getExamWeightage();
		if (exam == 0){ //course assesment has not been added yet
			System.out.println(">>>>>>>>>>>>Course assesment weightage for this courses has not been added<<<<<<<<<<");
			return 0;
		}
		result += (egrade*exam) /100;
		if (courseworkGrade.get(course.getCode()) == null){
			System.out.println(">>>>>>>>>>>>Coursework grade of student " + this.getName() + " for course " + course.getCode() + " has not been added<<<<<<<<<<<");
			return 0;
		}

		//Calculating coursework grade
		Map <String, Integer> courseworkw = course.getCourseworkWeightage(); //getting weightage for coursework from course
		Map <String, Integer> courseworkg = courseworkGrade.get(course.getCode());//getting grade of course of this student
		Iterator itw = courseworkw.entrySet().iterator();
			while (itw.hasNext()){
				Map.Entry pair = (Map.Entry) itw.next();
				String component = (String) pair.getKey();
				Integer cgrade = courseworkg.get(component);
				Integer w = (Integer) pair.getValue();
				result += (cgrade*w)/100;
			}
		return result;
	}

	/*
		*
		*printing transcript of this student
		*include overall mark, exam mark and all coursework mark of all registered courses
		*
	*/

	public void printTranscript(){
		if (courses.size() == 0){
			System.out.println(">>>>>>>>>>Student has not registered for any course<<<<<<<<<<");
			return;
		}
		if (examGrade.size() == 0 || courseworkGrade.size() == 0){
			System.out.println(">>>>>>>>>>Mark for this student has not been added<<<<<<<<<<");
			return;
		}
		System.out.println("        ======================Student Transcript===================");
		for (Course c : courses){
			if (examGrade.get(c.getCode()) == null || courseworkGrade.get(c.getCode()) == null) continue; // no grade added yet
			System.out.print("Course: " + c.getCode() + ", Mark: " + getMark(c)/20 + ", Exam: " + examGrade.get(c.getCode()));
			Map <String, Integer> cwg = courseworkGrade.get(c.getCode());
			Iterator it = cwg.entrySet().iterator();
			while (it.hasNext()){
				Map.Entry pair = (Map.Entry) it.next();
				System.out.print(", " + pair.getKey() + ": " + pair.getValue());
			}
			System.out.println();
		}
	}

	public boolean equals(Object o){
		if (o instanceof Student){
			Student p = (Student)o;
			return (getMatric().equals(p.getMatric()));
		}
		return false;
	}
}