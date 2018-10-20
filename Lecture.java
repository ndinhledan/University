import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Lecture implements Serializable{
	private int vacancy;
	private static final long serialVersionUID = -3914670736074682579L;

	public Lecture(int vacancy){
		this.vacancy = vacancy;
	}

	public void regLec(Student student){
		vacancy--;
	}

	public int getVacancy(){
		return vacancy;
	} 
}
