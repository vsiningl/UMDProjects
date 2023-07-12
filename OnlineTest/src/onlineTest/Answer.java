package onlineTest;

import java.io.Serializable;
import java.util.Arrays;

public class Answer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3806524430749916168L;
	private String student;
	private int examId, questionNumber;
	private String ans;
	//string array only really needed for fill in the blank answers, so thats why it's named that
	private String[] fbAns;
	
	//same as question, multiple constructors
	public Answer(String student, int examId, int questionNumber, String[] ansText) {
		this.student = student;
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.fbAns = ansText;
		this.ans = ansString(ansText);
	}
	
	public Answer(String student, int examId, int questionNumber, boolean ans) {
		this.student = student;
		this.examId = examId;
		this.questionNumber = questionNumber;
		this.ans = boolString(ans);
	}

	//same tostrings as question
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
	public String getName() {
		return student;
	}
	
	public int getExamId() {
		return examId;
	}
	
	public String[] getArr() {
		return fbAns;
	}
	
	public int getQNum() {
		return questionNumber;
	}
	
	public String getAns() {
		return ans;
	}
}
