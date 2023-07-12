package onlineTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class SystemManager implements Manager, Serializable{
	/**
	 * 
	 */
	//using arraylists because they make sense to me and are ez
	private static final long serialVersionUID = -8367727174171094004L;
	private ArrayList<Student> studentData = new ArrayList<Student>();
	private ArrayList<Exam> examData = new ArrayList<Exam>();
	private ArrayList<Answer> ansData = new ArrayList<Answer>();
	private String[] letterGrades;
	private double[] cutoffs;
	
	//system manager constructor. questions are stored in each exam.
	public SystemManager() {
		this.studentData = new ArrayList<Student>();
		this.examData = new ArrayList<Exam>();
		this.ansData = new ArrayList<Answer>();
	}

	//adds exam to databse, initializes new exam
	@Override
	public boolean addExam(int examId, String title) {
		Exam exam = new Exam(examId, title);
		//if empty, adds, if else, searches for exam
		if(examData.isEmpty()) {
			examData.add(exam);
			return true;
		}else {
			for(int i = 0; i < examData.size(); i++) {
				//uses .equals from exam class
				if(examData.get(i).equals(exam)) {
					return false;
				}
			}
			examData.add(exam);
			return true;
		}
	}
	
	//getter!
	public ArrayList<Exam> getExamData(){
		return examData;
	}

	//adds new question to the question array in exam w/ correct examid, uses questionNumber to determine index.
	//if a question already exists at that index, then it gets replaces.
	//assigns the question tfquestion type for later grading purposes.
	@Override
	public void addTrueFalseQuestion(int examId, int questionNumber, String text, double points, boolean answer) {
		Question question = new Question(text, points, answer);
		question.setType("TFQuestion");
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				Exam exam = examData.get(i);
				if(exam.getQues().size() >= questionNumber && exam.getQues().get(questionNumber - 1) != null){
					exam.getQues().remove(questionNumber - 1);
				}
				exam.getQues().add(questionNumber - 1, question);
			}
		}
		return;
	}

	//same as above, assigns mcquestion type
	@Override
	public void addMultipleChoiceQuestion(int examId, int questionNumber, String text, double points, String[] answer) {
		Question question = new Question(text, points, answer);
		question.setType("MCQuestion");
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				Exam exam = examData.get(i);
				if(exam.getQues().size() >= questionNumber && exam.getQues().get(questionNumber - 1) != null){
					exam.getQues().remove(questionNumber - 1);
				}
				exam.getQues().add(questionNumber - 1, question);
			}
		}
		return;
		
	}

	//same as above, assigns, fbquestion type
	@Override
	public void addFillInTheBlanksQuestion(int examId, int questionNumber, String text, double points,
			String[] answer) {
		Question question = new Question(text, points, answer);
		question.setType("FBQuestion");
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				Exam exam = examData.get(i);
				if(exam.getQues().size() >= questionNumber && exam.getQues().get(questionNumber - 1) != null){
					exam.getQues().remove(questionNumber - 1);
				}
				exam.getQues().add(questionNumber - 1, question);
			}
		}
		return;
		
	}

	//finds exam with matching id, returns question data in a string format.
	//if the exam list is empty or the id isnt found, it returns not found
	@Override
	public String getKey(int examId) {
		String result = "";
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				Exam exam = examData.get(i);
				for(int j = 0; j < exam.getQues().size(); j++) {
					Question question = exam.getQues().get(j);
					result += "Question Text: " + question.getText() +
							"\nPoints: " + question.getPoints() + 
							"\nCorrect Answer: " + question.getAns() + "\n";
				}
			}else {
				return "exam not found";
			}
		}
		return result;
	}

	public ArrayList<Student> getStudentData(){
		return studentData;
	}
	
	//adds student to student list, only if they don't already exist. 
	@Override
	public boolean addStudent(String name) {
		Student student = new Student(name);
			if(!studentData.contains(student)) {
				studentData.add(student);
				return true;
			}
			 return false;
	}

	public ArrayList<Answer> getAnsData(){
		return ansData;
	}
	
	//adds an answer to the answer list. answers don't have types, all answers are turned into strings
	//booleans and string arrays are stored for later reference just in case!
	@Override
	public void answerTrueFalseQuestion(String studentName, int examId, int questionNumber, boolean answer) {
		Answer ans = new Answer(studentName, examId, questionNumber, answer);
		for(int i = 0; i < studentData.size(); i++) {
			if(studentData.get(i).getName().equals(studentName)) {
				Student student = studentData.get(i);
				if(!student.getIds().contains(examId)) {
					student.getIds().add(examId);
				}
			}
		}
		ansData.add(ans);	
	}

	//same as above
	@Override
	public void answerMultipleChoiceQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		Answer ans = new Answer(studentName, examId, questionNumber, answer);
		for(int i = 0; i < studentData.size(); i++) {
			if(studentData.get(i).getName().equals(studentName)) {
				Student student = studentData.get(i);
				if(!student.getIds().contains(examId)) {
					student.getIds().add(examId);
				}
			}
		}
		ansData.add(ans);
	}

	//same as above
	@Override
	public void answerFillInTheBlanksQuestion(String studentName, int examId, int questionNumber, String[] answer) {
		Answer ans = new Answer(studentName, examId, questionNumber, answer);
		for(int i = 0; i < studentData.size(); i++) {
			if(studentData.get(i).getName().equals(studentName)) {
				Student student = studentData.get(i);
				if(!student.getIds().contains(examId)) {
					student.getIds().add(examId);
				}
			}
		}
		ansData.add(ans);
	}

	@Override
	public double getExamScore(String studentName, int examId) {
		//score variable to return
		double score = 0.0;
		Exam exam = null;
		
		//assigns exam matching parameter id
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				exam = examData.get(i);
			}
		}
		
		//if the exam is found. loop through each question in the exam
		if(exam != null) {
			for(int i = 0; i < exam.getQues().size(); i++) {
				//find the corresponding answer in the answer list
				for(int j = 0; j < ansData.size(); j++) {
					Answer ans = ansData.get(j);
					//checks based on student name
					if(ans.getName().equals(studentName)){
						//checks based on examid
						if(ans.getExamId() == examId) {
							//checks the question number
							if(ans.getQNum() == i + 1) {
								Question que = exam.getQues().get(i);
								//checks the type of the question. if question doesn't have type, nothing is added
								if(que.getType().equals("TFQuestion")) {
									//correct answer, all or nothing
									if(ans.getAns().equals(que.getAns())){
										score += que.getPoints();
									}
								//checks type, fill in blank isn't all or nothing
								}else if(que.getType().equals("FBQuestion")){
									//checks each answer array element, adds points based on the number that match
									//arrays for answer and questions are sorted
									for(int h = 0; h < que.getArr().length; h++) {
										for(int r = 0; r <ans.getArr().length; r++) {
											if(ans.getArr()[r].equals(que.getArr()[h])) {
												score += que.getPoints()/que.getArr().length;
											}
										}
									}
								//checks type
								}else if(que.getType().equals("MCQuestion")){
									if(ans.getAns().compareTo(que.getAns()) == 0){
										score += que.getPoints();
									}
								}
							}
						}
					}
				}
			}
		}
		return score;
	}
	
	//returns the maximum number of points possible for an exam. NOT TO BE CONFUSED WITH MAXSCORE
	public double getMaxPoints(int examId) {
		double score = 0.0;
		Exam exam = null;
		
		//finds exam w the matching id
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				exam = examData.get(i);
			}
		}
		
		//total points possible from 
		if(exam != null) {
			for(int i = 0; i < exam.getQues().size(); i++) {
				Question que = exam.getQues().get(i);
					score += que.getPoints();
			}
		}
		return score;
	}
	
	//grades each question, returns data in a string
	@Override
	public String getGradingReport(String studentName, int examId) {
		//variables for points earned and total points
		double score = 0.0;
		double total = 0.0;
		String result = "";
		Exam exam = null;
		
		//finds matching exam
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				exam = examData.get(i);
			}
		}
		
		//mostly same as exam report
		if(exam != null) {
			for(int i = 0; i < exam.getQues().size(); i++) {
				for(int j = 0; j < ansData.size(); j++) {
					Answer ans = ansData.get(j);
					if(ans.getName().equals(studentName)){
						if(ans.getExamId() == examId) {
							if(ans.getQNum() == i + 1) {
								Question que = exam.getQues().get(i);
								if(que.getType().equals("TFQuestion")) {
									if(ans.getAns().equals(que.getAns())){
										//returns question data and adds to score if answer is right. 
										//tfquestion is all or nothing. adds points to total.
										result += "Question #" + ans.getQNum() + " " + que.getPoints() + 
												" points out of " + que.getPoints() + "\n";
										score += que.getPoints();
										total += que.getPoints();
									}else {
										//doesn't add to score, answer is wrong
										result += "Question #" + ans.getQNum() + " 0.0 points out of " 
											+ que.getPoints() + "\n";
										total += que.getPoints();
									}
								}else if(que.getType().equals("FBQuestion")){
									double partial = 0.0;
									//adds question points to total
									total += que.getPoints();
									//checks each element of answer string array
									for(int h = 0; h < que.getArr().length; h++) {
										for(int r = 0; r <ans.getArr().length; r++) {
											if(ans.getArr()[r].equals(que.getArr()[h])) {
												//partial is the number of points for each answer element, needed for question data
												partial += que.getPoints()/que.getArr().length;
												score += que.getPoints()/que.getArr().length;
											}
										}
									}
									//adds question data to return string
									result += "Question #" + ans.getQNum() + " " + partial + 
											" points out of " + que.getPoints() + "\n";
								}else if(que.getType().equals("MCQuestion")){
									//same as true or false, all or nothing, doesn't check answer array elements, checks the string.
									if(ans.getAns().compareTo(que.getAns()) == 0){
										result += "Question #" + ans.getQNum() + " " + que.getPoints() + 
												" points out of " + que.getPoints() + "\n";
										score += que.getPoints();
										total += que.getPoints();
									}else {
										result += "Question #" + ans.getQNum() + " 0.0 points out of " 
											+ que.getPoints() + "\n";
										total += que.getPoints();
									}
								}
							}
						}
					}
				}
			}
			//total score data
			result += "Final Score: " + score + " out of " + total;
		}
		return result;
	}

	//just assigns parameters to some instance variables
	@Override
	public void setLetterGradesCutoffs(String[] letterGrades, double[] cutoffs) {
		this.letterGrades = letterGrades;
		this.cutoffs = cutoffs;
		
	}

	//returns percent of points earned by a student across all exams taken
	@Override
	public double getCourseNumericGrade(String studentName) {
		//variable for score over all exams and average score per exam
		double score = 0.0;
		double average = 0.0;
		//finds student
		for(int i = 0; i < studentData.size(); i++) {
			if(studentData.get(i).getName().equals(studentName)) {
				Student student = studentData.get(i);
				//loops through every exam taken by student
				for(int j = 0; j < student.getIds().size(); j++) {
					//gets the score, finds percent using maximum points
					score += getExamScore(studentName, student.getIds().get(j))/getMaxPoints(student.getIds().get(j))*100;
				}
				//divides by number of exams
				average = score/student.getIds().size();
			}
		}
		return average;
	}

	//uses course numeric grade to find letter grade
	@Override
	public String getCourseLetterGrade(String studentName) {
		//index counter;
		int index = 0;
		double grade = getCourseNumericGrade(studentName);
		//checks each grade cutoff against grade of a student, adds to index if grade is smaller than cutoff
		while(grade < cutoffs[index]) {
			index++;
		}
		//returns letter grade based on that
		return letterGrades[index];
	}

	@Override
	public String getCourseGrades() {
		Collections.sort(studentData, new StudentComparator());
		String result = "";
		//adds name, score, and letter grade of each student in student list to string and returns
		for(int i = 0; i < studentData.size(); i++) {
			Student student = studentData.get(i);
			result += student.getName() + " " + getCourseNumericGrade(student.getName())
					+ " " + getCourseLetterGrade(student.getName()) + "\n";
		}
		return result;
	}

	@Override
	public double getMaxScore(int examId) {
		boolean exist = false;
		double max = 0.0;
		//checks if exam exists
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				exist = true;
			}
		}
		
		if(exist) {
			//checks each student
			for(int i = 0; i < studentData.size(); i++) {
				Student student = studentData.get(i);
				//checks for exam
				for(int j = 0; j < student.getIds().size(); j++) {
					if(student.getIds().get(j) == examId) {
						//checks score
						if(getExamScore(student.getName(), examId) >= max) {
							//grabs score if its larger than the current max
							max = getExamScore(student.getName(), examId);
						}
					}
				}
			}
		}
		return max;
	}

	//same as max
	@Override
	public double getMinScore(int examId) {
		boolean exist = false;
		double min = 100;
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				exist = true;
			}
		}
		
		if(exist) {
			for(int i = 0; i < studentData.size(); i++) {
				Student student = studentData.get(i);
				for(int j = 0; j < student.getIds().size(); j++) {
					if(student.getIds().get(j) == examId) {
						if(getExamScore(student.getName(), examId) <= min) {
							//grabs if score is smaller than current min
							min = getExamScore(student.getName(), examId);
						}
					}
				}
			}
		}
		return min;
	}

	//gets average score across all students
	@Override
	public double getAverageScore(int examId) {
		boolean exist = false;
		double total = 0.0;
		//counter for number of students that take exam
		int count = 0;
		for(int i = 0; i < examData.size(); i++) {
			if(examData.get(i).getId() == examId) {
				exist = true;
			}
		}
		
		if(exist) {
			//loops through student list
			for(int i = 0; i < studentData.size(); i++) {
				Student student = studentData.get(i);
				//checks that student took exam
				for(int j = 0; j < student.getIds().size(); j++) {
					if(student.getIds().get(j) == examId) {
						//adds student score if the student took the exam
						total += (getExamScore(student.getName(), examId));
						count++;
					}
				}
			}
		}
		//return average
		return total/count;
	}

	//saves the manager to a file!
	@Override
	public void saveManager(Manager manager, String fileName) {
		try {
			FileOutputStream file = new FileOutputStream(fileName);
			ObjectOutputStream output = new ObjectOutputStream(file);
			
			output.writeObject(manager);
			
			file.close();
			output.close();
		//catch for input output exception
		}catch(IOException e){
			System.out.println("ioexception caught, try again");
		}
	}

	//loads a file containing a manager
	@Override
	public Manager restoreManager(String fileName) {
		Manager manager = null;
		try {
			FileInputStream file = new FileInputStream(fileName);
			ObjectInputStream input = new ObjectInputStream(file);
			
			//casts the file to manager object
			manager = (Manager)input.readObject();
			
			input.close();
			file.close();
		//catch for input output and class not found
		}catch(IOException e){
			System.out.println("ioexception caught, try again");
		} catch (ClassNotFoundException e) {
			System.out.println("classnotfoundexception caught");
		}
		
		return manager;
	}

}
