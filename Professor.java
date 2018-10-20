import java.io.Serializable;

public class Professor implements Serializable {
	private String name ;
	private String id;
	private String email ;
	private int contact ;
	private static final long serialVersionUID = -3914670736074682579L;

	public Professor(String name, String id, String email, int contact)  {
		this.name = name ;
		this.id = id;
		this.email = email ;
		this.contact = contact ;
	}

	public Professor(){};

	public String getName(){ 
		return name; 
	}
	
	public String getID(){
		return id;
	}
	
	public int getContact(){ 
		return contact; 
	}
	
	public String getEmail(){
		return email;
	}

	public boolean equals(Object o) {
		if (o instanceof Professor) {
			Professor p = (Professor)o;
			return (getID().equals(p.getID()));
		}
		return false;
	}
}