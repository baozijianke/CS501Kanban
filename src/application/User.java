package application;

import java.util.ArrayList;
import java.io.Serializable;

public class User implements Serializable{

	private int userID;
	private String userName;
	
	public User(String name) {
		//setID();
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
		this.userName = name;
		return true;
	}
	
	public String getName() {
		return this.userName;
	}
	
}