package onlineTest;

import java.io.Serializable;
import java.util.Arrays;

public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4344462423517259222L;
	private String text;
	private double points;
	private String answer;
	private boolean ans;
	private String[] ansText;
	private String type;
	
	
	//multiple constructors for different question types
	public Question(String text, double points, String[] ansText) {
		this.text = text;
		this.points = points;
		this.ansText = ansText;
		this.answer = ansString(ansText);
		this.type = "";
	}
	
	public Question(String text, double points,  boolean ans) {
		this.text = text;
		this.points = points;
		this.ans = ans;
		this.answer = boolString(ans);
		this.type = "";
	}
	
	//toStrings for answers, makes it easier for printing the question
	public String ansString(String[] ansText) {
		String result = "[";
		Arrays.sort(ansText);
		for(int i = 0; i < ansText.length - 1; i++) {
			result += ansText[i] + ", ";
		}
		result += ansText[ansText.length - 1] + "]";
		return result;
	}
	
	public String boolString(boolean ans) {
		if(ans) {
			return "True";
		}
		return "False";
	}
	
	//getters!!
	public String getText() {
		return this.text;
	}
	
	public String getAns() {
		return answer;
	}
	
	//gets original array for answers, used to grade
	public String[] getArr() {
		return ansText;
	}
	
	public boolean getTF() {
		return ans;
	}
	
	public void setType(String type) {
		this.type = type;
		
	}
	
	public String getType() {
		return type;
	}
	
	public double getPoints() {
		return points;
	}
	
}
