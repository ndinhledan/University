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
		private Map<String, Integer> coursework = new HashMap<String, Integer>();
		private int vacancy;;
		private ArrayList<Tutorial> tuts = new ArrayList<Tutorial>();
		private ArrayList<Lab> labs = new ArrayList<Lab>();
		private Map<Student, String> students = new HashMap<Student, String>();
		private static final long serialVersionUID = -3914670736074682579L;

		public class Tutorial implements Serializable{
		private String index;
		private int vacancy;
		private int reg;
		private ArrayList<Student> students = new ArrayList<Student>();

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
	}

	public class Lab implements Serializable {
		private String index;
		private int vacancy;
		private int reg;
		private ArrayList<Student> students = new ArrayList<Student>();

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
	}

	public Course(String code, String name, Boolean hasTut, Boolean hasLab, Professor coordinator, int vacancy){
		this.code = code;
		this.name = name;
		this.hasTut = hasTut;
		this.hasLab = hasLab;
		this.coordinator = coordinator;
		this.vacancy = vacancy;
		if (hasTut || hasLab){
			while (addIndex() != 0){
				int check = addIndex();
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
		if (vacancy <1 ) {
			System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
			return 2;
		}
		vacancy--;
		students.put(stu, "Lecture");
		return 0;
	}
	
	public int addStudent(Student stu, String index){
		Tutorial ttemp = new Tutorial();
		Lab ltemp = new Lab();
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
		if (ttemp == null || ltemp == null){
			System.out.println(">>>>>>>>>>No index found<<<<<<<<<<");
			return 1;
		}
		if (vacancy <1 || ttemp.getVacancy() <1 || ltemp.getVacancy() <1) {
			System.out.println(">>>>>>>>>>No vacancy left<<<<<<<<<<");
			return 2;
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

	public void printIndex(){
		for (Tutorial t : tuts){
			System.out.printf("Index: %s, Vacancy: %d\n", t.getIndex(), t.getVacancy());
		}
	}
	public int addIndex(){
		Scanner sc = new Scanner(System.in);
		try{
			System.out.print("Enter number of index(es): ");
			int num = sc.nextInt();
			if (num <1) return 1;
			for (int i=0; i<num; i++){
				System.out.print("Enter index for index number "+(i+1) + ": ");
				String index = sc.next();
				System.out.print("Enter vacancy: ");
				int vacancy = sc.nextInt();
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