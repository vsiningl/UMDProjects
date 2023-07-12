package onlineTest;

import java.io.Serializable;
import java.util.Comparator;

public class StudentComparator implements Comparator<Student>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8948646346084661194L;

	@Override
	public int compare(Student s1, Student s2) {
		return s1.getName().compareTo(s2.getName());
	}

}
