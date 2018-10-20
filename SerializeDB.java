import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;


public class SerializeDB
{
	public static List readSerializedObject(String filename) throws IOException, ClassNotFoundException{
		List details = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			details = (ArrayList) in.readObject();
			in.close();
		}catch(EOFException ex){
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("File error in " + filename);
		} catch (ClassNotFoundException ex) {
			throw new ClassNotFoundException("No class found!");
		}
		return details;
	}

	public static void writeSerializedObject(String filename, List list) throws IOException{
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(filename);
			out = new ObjectOutputStream(fos);
			out.writeObject(list);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new IOException("File error!");
		}
	}

	public static void main(String[] args) {
		List list;
		List list1;
		try	{
				list = new ArrayList<Student>();
				// read from serialized file the list of professors
				list1 = (ArrayList)SerializeDB.readSerializedObject("Professor.dat");
				//for (int i = 0 ; i < list.size() ; i++) {
				//	Professor p = (Professor)list.get(i);
				//	System.out.println("name is " + p.getName() );
				//	System.out.println("contact is " + p.getContact() );
				//}
				Professor p1 = (Professor) list1.get(0);
				Professor p2 = (Professor) list1.get(1);
				Professor p3 = (Professor) list1.get(2);
				// write to serialized file - update/insert/delete
				// example - add one more professor
				Course c1 = new Course("CZ2002", "OOP", false, false, p1, 10);
				Course c2 = new Course("CZ2004", "HCI", true, false, p2, 10);
				Course c3 = new Course("CZ2005","OS", true, true, p3, 10);
				// add to list
				list.add(c1);
				list.add(c2);
				list.add(c3);
				// list.remove(p);  // remove if p equals object in the list

				SerializeDB.writeSerializedObject("Course.dat", list);

		}  catch ( Exception e ) {
				e.printStackTrace();
				System.out.println( "Exception >> " + e.getMessage() );
		}
	}
} 