package onlineTest;

import java.io.Serializable;
import java.util.ArrayList;

public class Exam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6434941771253509031L;
	private ArrayList<Question> questions; 
	private int id;
	private String title;

	//constructor with question arraylist for all the questions
	public Exam(int id, String title) {
		this.id = id;
		this.title = title;
		this.questions = new ArrayList<>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public ArrayList<Question> getQues(){
		return questions;
	}
	
	//.equals for comparison!
	public boolean equals(Exam ex) {
		if(this.getId() == ex.getId() && this.getTitle().equals(ex.getTitle())) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return this.getId() + " " + this.getTitle();
	}
}
