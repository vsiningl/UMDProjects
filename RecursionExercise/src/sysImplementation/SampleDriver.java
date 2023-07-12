package sysImplementation;

import java.util.Arrays;
import java.util.ArrayList;

public class SampleDriver {

	public static void main(String[] args) {
		String answer = "";

		String str = "spring";
		char delimeter = '*';
		answer += str + "  --->  " + Utilities.addDelimiter(str, delimeter) + "\n";

		str = "c2o3l4le6ge";
		answer += str + "  --->  " + Utilities.getDigits(str) + "\n";

		char[] array = { 'r', 'a', 'c', 'e', 'c', 'a', 'r' };
		char target = 'r', replacement = '*';

		answer += "Original: " + Arrays.toString(array);
		answer += ", Replacing: " + target + " with " + replacement;
		Utilities.replaceCharacter(array, target, replacement);
		answer += ", Result: " + Arrays.toString(array) + "\n";

		int[] intArray = { 1, 5, 2, 3, 10 };
		answer += "Original: " + Arrays.toString(intArray);
		answer += ", Sums of Even: " + Utilities.getSumEven(intArray);

		int[][] twoDimArray = { { 4, 5 }, { 1, 2, 3, 4, 5, 6, 7, 10, 11, 14 }, { 2, 4, 6, 8 },
				{ 10, 21, 31, 45, 51, 62, 71, 12, 13, 14 } };
		int rowLength = 10;
		ArrayList<Integer> list = Utilities.getListRowIndices(twoDimArray, rowLength);
		answer += "\nIndices: " + list.toString();

		
		char[][] charArray = {
				              {'R', 'Y', 'R', 'Y', 'Y'},
				              {'R', 'Y', 'R', 'Y', 'Y'},
				              {'R', 'M', 'R', 'Y', 'Y'},
				              {'R', 'Y', 'Y', 'Y', 'Y'},
				              {'R', 'Y', 'R', 'Y', 'Y'},
				              {'R', 'Y', 'R', 'Y', 'P'},
		};
				
		answer += "\nOriginal Array:\n" + printArray(charArray);
		int x = 1, y = 3;
		target = 'Y';
		replacement = '*';
		Utilities.replaceCells(charArray, x, y, target, replacement);
		answer += "After processing: " + x  + ", " + y + "\n" + printArray(charArray);
		answer += "END";
		System.out.println(answer);
	}
	
	private static String printArray(char [][]array) {
		StringBuffer answer = new StringBuffer();
		
		/* Notice how we can loop through the array */
		for (char[] row : array) {
			for (char entry : row) {
				answer.append(entry + " ");
			}
			answer.append("\n");
		}
		
		return answer.toString();
	}
}