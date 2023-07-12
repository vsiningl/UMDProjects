package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7783432361856592746L;
	private String name;
	private ArrayList<Integer> examIds;

	//
	public Student(String name) {
		this.name = name;
		this.examIds = new ArrayList<Integer>();
	}
	
	//student .equals for finding students in manager arraylsit
	public boolean equals(String name) {
		if(this.name.equals(name)) {
			return true;
		}
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	//arraylist of Integers, not exam objects. only stores ids, not a whole exam. too much work
	public ArrayList<Integer> getIds(){
		return examIds;
	}
	
	public String toString() {
		String output = "";
		output += this.getName() + " ";
		for(int i = 0; i < this.getIds().size(); i++) {
			output += this.getIds().get(i);
		}
		output += "\n";
		return output;
	}
}
