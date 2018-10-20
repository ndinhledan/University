import java.util.ArrayList;
import java.util.List;

public class Lab{
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