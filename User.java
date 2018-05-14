import java.util.Arraylist
import java.io.Serializable;

public class User implements Serializable, Comparable<User> {

	private int userID
	private String userName
	
	public User(String name) {
		setID();
		setName(name);
	}
	public boolean setID(int id) {
		this.userID = id;
		return true;
	}
	
	public int getId() {
		return this.userID;
	}
	
	public boolean setName(String name) {
		this userName = name;
		return true;
	}
	
	public String getName() {
		return this.userName;
	}
	
}