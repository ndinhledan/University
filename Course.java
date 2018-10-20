import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.Serializable;
import java.io.IOException;

	public class Course implements Serializable{
		private String code; //course code e.g CZ2002
		private String name;
		private boolean hasTut;
		private boolean hasLab;
		private Professor coordinator;
		private int exam;
		private Map<String, Integer> coursework = new HashMap<String, Integer>();//component map to weightage
		private int vacancy;;
		private ArrayList<Tutorial> tuts = new ArrayList<Tutorial>();
		private ArrayList<Lab> labs = new ArrayList<Lab>();
		private Map<Student, String> students = new HashMap<Student, String>(); //student map to index
		private static final long serialVersionUID = -3914670736074682579L;

		///////////////////////////////////////////////////////////////////
		//Nested Classes Tutorial and Lab
		////////////////////////////////////////////////////////////////////

		public class Tutorial implements Serializable{
		private String index;
		private int vacancy;
		private int reg;
		private ArrayList<Student> students = new ArrayList<Student>();
		private static final long serialVersionUID = -1912256906115544712L;

		public Tutorial(){}

		public Tutorial(String index){
			this.index = index;
			vacancy =10;
			reg =0;
		}
		public Tutorial(String index, int vacancy){
			this.index = index;
			this.vacancy = vacancy;
			reg=0;
		}

		public String getIndex(){
			return index;
		}

		public int getVacancy(){
			return vacancy;
		}

		public int getReg(){
			return reg;
		}

		public void regTut(Student student){
			students.add(student);
			vacancy --;
			reg++;
		}

		public List getStudent(){
			return students;
		}

		public void printStudent(){
			System.out.println("    ===========List of students of index " + index +" ===========");
			for (Student s : students){
				System.out.println("Student: " + s.getName() + ", Matric: " + s.getMatric());
			}
		}
	}

	public class Lab implements Serializable {
		private String index;
		private int vacancy;
		private int reg;
		private ArrayList<Student> students = new ArrayList<Student>();
		private static final long serialVersionUID = -1497047205835041867L;

		public Lab(){}

		public Lab(String index){
			this.index = index;
			vacancy =10;
			reg =0;
		}
		public Lab(String index, int vacancy){
			this.index = index;
			this.vacancy = vacancy;
			reg=0;
		}

		public String getIndex(){
			return index;
		}

		public int getVacancy(){
			return vacancy;
		}

		public int getReg(){
			return reg;
		}

		public void regLab(Student student){
			students.add(student);
			vacancy --;
			reg++;
		}

		public List getStudent(){
			return students;
		}

		public void printStudent(){
			System.out.println("    ===========List of students of index " + index +" ===========");
			for (Student s : students){
				System.out.println("Student: " + s.getName() + ", Matric: " + s.getMatric());
			}
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////
	//Constructor
	////////////////////////////////////////////////////////////////////////////////////

	public Course(String code, String name, Boolean hasTut, Boolean hasLab, Professor coordinator, int vacancy){
		this.code = code;
		this.name = name;
		this.hasTut = hasTut;
		this.hasLab = hasLab;
		this.coordinator = coordinator;
		this.vacancy = vacancy;
		if (hasTut || hasLab){ // course has tut or lab
			while (addIndex() != 0){
				int check = addIndex();
				if (check ==2) System.out.println("Vacancy for course and index(es) does not match");
				if (check ==0) break;
			}
		}
	}

	public Course(){};

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}

	public boolean includeTut(){
		return hasTut;
	}

	public boolean includeLab(){
		return hasLab;
	}

	public Professor getCoordinator(){
		return coordinator;
	}

	public int getExamWeightage(){
		return exam;
	}

	public Map<String, Integer> getCourseworkWeightage(){
		return coursework;
	}
	
	public void printWeightage(){
		System.out.printf("Exam: %d percent\n", exam);
		Iterator it = coursework.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " :" + pair.getValue() + " percent");
		}
	}

	public int getVacancy(){
		return vacancy;
	}

	public void addTut(Tutorial tut){
		tuts.add(tut);
	}

	public int getSizeTut(){
		return tuts.size();
	}

	public void printTut(){
		for (Tutorial t : tuts){
			System.out.printf("Index: %s === Vacancy: %d\n", t.getIndex(), t.getVacancy());
		}
	}

	public void addLab(Lab lab){
		labs.add(lab);
	}

	public int getSizeLab(){
		return labs.size();
	}

	public void printLab(){
		for (Lab l : labs){
			System.out.printf("Index: %s === Vacancy: %d\n", l.getIndex(), l.getVacancy());
		}
	}

	public int addStudent(Student stu){
		if (vacancy <1 ) { // no vacancy
			return 2;
		}
		for (Student s : students.keySet()){ //duplicate student
			if (s.equals(stu)){
				return 3;
			}
		}
		vacancy--;
		students.put(stu, "Lecture");
		return 0;
	}
	
	public int addStudent(Student stu, String index){
		Tutorial ttemp = new Tutorial();
		Boolean tflag = false; // true if tutorial found by index
		Lab ltemp = new Lab();
		Boolean lflag = false; // true if lab found by index
		for (Tutorial t : tuts){
			if(t.getIndex().equals(index)){
				ttemp = t;
				tflag = true;
				break;
			}
		}
		for (Lab l : labs){
			if(l.getIndex().equals(index)){
				ltemp = l;
				lflag = true;
				break;
			}
		}
		if (!tflag && !lflag){ //either tut or lab cannot be found i.e wrong index
			System.out.println("return 1");
			return 1;
		}
		if (vacancy <1 || (ttemp.getVacancy() <1 && ltemp.getVacancy() <1)) { //no vacancy
			System.out.println("return 2");
			return 2;
		}
		for (Student s : students.keySet()){ //student alread registered
			if (s.equals(stu)){
				System.out.println("return 3");
				return 3;
			}
		}
		vacancy--;
		ttemp.regTut(stu);
		ltemp.regLab(stu);
		students.put(stu, index);
		return 0;
	}

	public int getSizeStudent(){
		int size =0;
		Iterator it = students.entrySet().iterator();
		while (it.hasNext()){
			size ++;
		}
		return size;
	}

	public void printStudent(){
		Iterator it = students.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry pair = (Map.Entry) it.next();
			Student tmp = (Student) pair.getKey();
			System.out.println("Student name: " + tmp.getName() + ", Index: " + pair.getValue());
		}
	}

	public void printStudentIndex(String index){
		Tutorial ttemp = new Tutorial();
		Boolean tflag = false; // true if tutorial found by index
		Lab ltemp = new Lab();
		Boolean lflag = false; // true if lab found by index
		for (Tutorial t : tuts){
			if(t.getIndex().equals(index)){
				ttemp = t;
				break;
			}
		}
		for (Lab l : labs){
			if(l.getIndex().equals(index)){
				ltemp = l;
				break;
			}
		}
		if (!tflag || !lflag){ //neither tut or lab cannot be found i.e wrong index
			System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
			return;
		}
		else {
			ttemp.printStudent();
		}	
	}

	public void printIndex(){
		if (!hasTut && !hasLab) return;
		for (Tutorial t : tuts){
			System.out.printf("Index: %s, Vacancy: %d for ", t.getIndex(), t.getVacancy());
			if (hasTut) System.out.print("Tutorial");
			if (hasLab) System.out.print(" and Lab");
			System.out.println(".");
		}
	}
	public int addIndex(){
		Scanner sc = new Scanner(System.in);
		try{
			System.out.print("Enter number of index(es) for " +getCode() +": ");
			int num = sc.nextInt();
			if (num <1){
				System.out.println("Invalid Input!");
				return 1;
			} 
			for (int i=0; i<num; i++){
				System.out.print("Enter index for index number "+(i+1) + ": ");
				String index = sc.next();
				System.out.print("Enter vacancy for "+getCode() +": ");
				int vacancy = sc.nextInt();
				if (vacancy *num != getVacancy()) return 2; // number of vacancy does not match
				if (hasTut) addTut(new Tutorial(index, vacancy));
				if (hasLab) addLab(new Lab(index, vacancy));
			}
		} catch(InputMismatchException e){
			System.out.println("Invalid Input!");
			return 1;
		} catch(Exception e){
			System.out.println("Error!!");
			return -1;
		}
		return 0;
	}

	public int addWeightage(){
		Scanner sc = new Scanner(System.in);
		int sum =0;
		try {
			System.out.print("Enter exam weightage(%): ");
			exam = sc.nextInt();
			System.out.print("Enter number of component in coursework: ");
			int compo = sc.nextInt();
			for (int i =0; i<compo; i++){
				System.out.print("Enter component name: ");
				String component = sc.next();
				System.out.print("Enter component weightage(%): ");
				int weight = sc.nextInt();
				coursework.put(component, weight);
			}
		} catch(InputMismatchException e){
			System.out.println("Invalic Input!");
			return 1;
		} catch(Exception e){
			System.out.println("Error!");
			return -1;
		} 
		// check if mark add up to 100%
		for (int v : coursework.values()){
			sum += v;
		}
		if (exam + sum != 100){
			return 2;
		}
		return 0;
	}

	public boolean equals(Object o){
		if (o instanceof Course){
			Course p = (Course)o;
			return (getCode().equals(p.getCode()));
		}
		return false;
	}

}