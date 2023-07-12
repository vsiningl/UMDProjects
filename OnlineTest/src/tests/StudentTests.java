package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import onlineTest.Exam;
import onlineTest.Question;
import onlineTest.SystemManager;

/**
 * 
 * You need student tests if you are looking for help during office hours about
 * bugs in your code.
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {

	@Test
	public void testAddExam() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addExam(2, "Exam 2");
		manager.addExam(3, "Exam 3");
		
		ArrayList<Exam> arr = new ArrayList<Exam>();
		arr.add(new Exam(1, "Exam 1"));
		arr.add(new Exam(2, "Exam 2"));
		arr.add(new Exam(3, "Exam 3"));
		
		assertEquals(examString(manager.getExamData()), examString(arr));

	}
	
	@Test
	public void testAddQuestion() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "xyz", 5, new String[] {"a"});
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 5, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "xyz", 5, new String[] {"a"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "xyz", 5, true);
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		
		String key = examString(manager.getExamData()) + "\n(5.0) Question 1: [1, 2, 3]"
				+ "\n(5.0) Question 2: [1, 2, 3]\n(5.0) Question 3: False";
		
		String result = examString(manager.getExamData()) + questionString(manager.getExamData().get(0).getQues());
		
		assertEquals(key,result);
	}
	
	@Test
	public void testKey() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 5, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		
		String key1 = "Question Text: Question 1\nPoints: 5.0\nCorrect Answer: [1, 2, 3]"
				+ "\nQuestion Text: Question 2\nPoints: 5.0\nCorrect Answer: [1, 2, 3]"
				+ "\nQuestion Text: Question 3\nPoints: 5.0\nCorrect Answer: False\n";
		
		String output1 = manager.getKey(1);
	
		String key2 = "exam not found";
		
		String output2 = manager.getKey(0);
		
		assertEquals(output1, key1);
		assertEquals(output2, key2);
	}
	
	@Test
	public void testStudent() {
		SystemManager manager = new SystemManager();
		manager.addStudent("gregory");
		manager.addStudent("bearimy");
		manager.addStudent("gregory");
		
		String key = "gregory \nbearimy \n";
		String output = (manager.getStudentData().get(0).toString() + manager.getStudentData().get(1).toString());
		
		assertEquals(output, key);
	}
	
	@Test
	public void testAddAnswerGrade() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		manager.addStudent("gregory");
		
		manager.answerFillInTheBlanksQuestion("gregory", 1, 1, new String[]{"1", "2", "3"});
		manager.answerMultipleChoiceQuestion("gregory", 1, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("gregory", 1, 3, false);
		
		double output1 = manager.getExamScore("gregory", 1);
		double key1 = 16.0;
		
		manager.addExam(2, "Exam 2");
		manager.addFillInTheBlanksQuestion(2, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(2, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(2, 3, "Question 3", 5, false);
		manager.addStudent("beatrice");
		
		manager.answerFillInTheBlanksQuestion("beatrice", 2, 1, new String[]{"1", "2", "4"});
		manager.answerMultipleChoiceQuestion("beatrice", 2, 2, new String[]{"1", "2", "4"});
		manager.answerTrueFalseQuestion("beatrice", 2, 3, true);

		double key2 = 4;
		double output2 = manager.getExamScore("beatrice", 2);
		
		assertTrue(output1 == key1);
		assertTrue(output2 == key2);
	}
	
	@Test
	public void testMaxMinAvgPoints() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		manager.addStudent("gregory");
		
		manager.answerFillInTheBlanksQuestion("gregory", 1, 1, new String[]{"1", "2", "3"});
		manager.answerMultipleChoiceQuestion("gregory", 1, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("gregory", 1, 3, false);
		
//		manager.addExam(2, "Exam 2");
//		manager.addFillInTheBlanksQuestion(2, 1, "Question 1", 6, new String[]{"1", "2", "3"});
//		manager.addMultipleChoiceQuestion(2, 2, "Question 2", 5, new String[]{"1", "2", "3"});
//		manager.addTrueFalseQuestion(2, 3, "Question 3", 5, false);
		manager.addStudent("beatrice");
		
		manager.answerFillInTheBlanksQuestion("beatrice", 1, 1, new String[]{"1", "2", "4"});
		manager.answerMultipleChoiceQuestion("beatrice", 1, 2, new String[]{"1", "2", "4"});
		manager.answerTrueFalseQuestion("beatrice", 1, 3, true);

		double output1 = manager.getMaxScore(1);
		double key1 = 16.0;
		double output2 = manager.getMinScore(1);
		double key2 = 4;
		double output3 = manager.getAverageScore(1);
		double key3 = 10;
		
		assertTrue(output1 == key1);
		assertTrue(output2 == key2);
		assertTrue(output3 == key3);
	}
	
	@Test
	public void testGradingReport() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		manager.addTrueFalseQuestion(1, 4, "Question 4", 5, true);
		manager.addMultipleChoiceQuestion(1,  5, "Question 5", 5, new String[]{"1", "2", "3"});
		manager.addStudent("beatrice");
		
		manager.answerFillInTheBlanksQuestion("beatrice", 1, 1, new String[]{"1", "2", "4"});
		manager.answerMultipleChoiceQuestion("beatrice", 1, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("beatrice", 1, 3, true);
		manager.answerTrueFalseQuestion("beatrice", 1, 4, true);
		manager.answerMultipleChoiceQuestion("beatrice", 1, 5, new String[]{"1", "2", "5"});

		String key = "Question #1 4.0 points out of 6.0\nQuestion #2 5.0 points out of 5.0"
					+ "\nQuestion #3 0.0 points out of 5.0\nQuestion #4 5.0 points out of 5.0"
					+ "\nQuestion #5 0.0 points out of 5.0\nFinal Score: 14.0 out of 26.0";
		
		String output = manager.getGradingReport("beatrice", 1);
		
		assertEquals(output, key);
	}
	
	@Test
	public void testCutoffsCourseGrades() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		
		manager.addExam(2, "Exam 2");
		manager.addFillInTheBlanksQuestion(2, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(2, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(2, 3, "Question 3", 5, false);
		
		manager.addStudent("gregory");
		
		manager.answerFillInTheBlanksQuestion("gregory", 1, 1, new String[]{"1", "2", "3"});
		manager.answerMultipleChoiceQuestion("gregory", 1, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("gregory", 1, 3, false);
		manager.answerFillInTheBlanksQuestion("gregory", 2, 1, new String[]{"1", "2", "3"});
		manager.answerMultipleChoiceQuestion("gregory", 2, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("gregory", 2, 3, false);
		
		manager.addStudent("beatrice");
		
		manager.answerFillInTheBlanksQuestion("beatrice", 1, 1, new String[]{"6", "5", "4"});
		manager.answerMultipleChoiceQuestion("beatrice", 1, 2, new String[]{"1", "2", "4"});
		manager.answerTrueFalseQuestion("beatrice", 1, 3, true);
		manager.answerFillInTheBlanksQuestion("beatrice", 2, 1, new String[]{"1", "2", "3"});
		manager.answerMultipleChoiceQuestion("beatrice", 2, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("beatrice", 2, 3, false);
		
		manager.setLetterGradesCutoffs(new String[] {"a", "b", "c", "d", "f"}, new double[] {90, 80, 60, 40, 0});
		
		String output1 = manager.getCourseGrades();
		String key1 = "beatrice 50.0 d\ngregory 100.0 a\n";
		
		assertEquals(output1, key1);	
	}
	
	@Test
	public void testSerialization() {
		SystemManager manager = new SystemManager();
		manager.addExam(1, "Exam 1");
		manager.addFillInTheBlanksQuestion(1, 1, "Question 1", 6, new String[]{"1", "2", "3"});
		manager.addMultipleChoiceQuestion(1, 2, "Question 2", 5, new String[]{"1", "2", "3"});
		manager.addTrueFalseQuestion(1, 3, "Question 3", 5, false);
		
		manager.addStudent("gregory");
		manager.answerFillInTheBlanksQuestion("gregory", 1, 1, new String[]{"1", "2", "3"});
		manager.answerMultipleChoiceQuestion("gregory", 1, 2, new String[]{"1", "2", "3"});
		manager.answerTrueFalseQuestion("gregory", 1, 3, false);
		
		manager.setLetterGradesCutoffs(new String[] {"a", "b", "c", "d", "f"}, new double[] {90, 80, 70, 60, 0});
		
		String filename = "saveone";
		manager.saveManager(manager, filename);
		
		SystemManager manager2 = (SystemManager) manager.restoreManager(filename);
		
		String key1 = manager.getCourseGrades();
		String output1 = manager2.getCourseGrades();
		
		assertEquals(key1, output1);
	}

	public String questionString(ArrayList<Question> questions) {
		String output = "";
		for(int i = 0; i < questions.size(); i++) {
			output += "\n(" + questions.get(i).getPoints() + ") " + questions.get(i).getText()
					+ ": " + questions.get(i).getAns();
		}
		return output;
	}
	
	public String examString(ArrayList<Exam> exam) {
		String output = "";
		for(int i = 0; i < exam.size(); i++) {
			output += exam.get(i).toString() + ", ";
		}
		return output;
	}

}
