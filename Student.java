import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Student implements Serializable{
	private String name;
	private String matric;
	private ArrayList<Course> courses = new ArrayList<Course>();
	private Map<Course, Map> grade = new HashMap<Course, Map>();
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
		if (course.addStudent(this) == 0){
			courses.add(course);
			return 0;
		}
		else {
			return 1;
		}
	}

	public int addCourse(Course course, String index){
		if (course.addStudent(this, index) ==0){
			courses.add(course);
			return 0;
		}
		else {
			return 1;
		}
	}

	public void addGrade(Course course) throws InputMismatchException{
		Map<String, Integer> cw = new HashMap<String, Integer>();
		Scanner sc = new Scanner(System.in);
		for (String c : course.getCourseworkWeightage().keySet()){
			System.out.printf("Enter mark for %s: ", c);
			int mark = sc.nextInt();
			cw.put(c, mark);
		}
		grade.put(course, cw);
	}

	public boolean equals(Object o){
		if (o instanceof Student){
			Student p = (Student)o;
			return (getMatric().equals(p.getMatric()));
		}
		return false;
	}
}