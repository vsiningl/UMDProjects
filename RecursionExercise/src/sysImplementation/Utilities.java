package sysImplementation;

import java.util.ArrayList;

public class Utilities {

	public static String addDelimiter(String str, char delimiter) {
		if(str.length() == 0) {
			return "";
		}
		if(str.length() == 1) {
			return str;
		}else {
			return str.charAt(0) + (delimiter + addDelimiter(str.substring(1), delimiter));
		}
	}

	public static String getDigits(String str) {
		if(str.length() == 0) {
			return "";
		}
		if(Character.isDigit(str.charAt(0))) {
			return str.charAt(0) + (getDigits(str.substring(1)));
		}else {
			return getDigits(str.substring(1));
		}
	}

	public static void replaceCharacterAux(char[] array, char target, char replacement, int index) {
		if(index == array.length) {
			return;
		}else if(array[index] == target) {
			array[index] = replacement;
			replaceCharacterAux(array, target, replacement, index + 1);
		}else {
			replaceCharacterAux(array, target, replacement, index + 1);
		}
	}
	public static void replaceCharacter(char[] array, char target, char replacement) {
			replaceCharacterAux(array, target, replacement, 0);
	}
		
	public static int getSumEvenAux(int[] array, int index) {
		if(index > array.length - 1) {
			return 0;
		}
		if(array[index]%2 == 0){
			return array[index] + getSumEvenAux(array, index + 1);
		}
		else {
			return getSumEvenAux(array, index + 1);
		}
	}
	
	public static int getSumEven(int[] array) {
		return getSumEvenAux(array, 0);
	}

	public static ArrayList<Integer> getListRowIndicesAux(int[][] array, int rowLength, int index, ArrayList<Integer> indices){
		if(index >= array.length) {
			return indices;
		}else if(array[index].length == rowLength) {
			indices.add(index);
			return getListRowIndicesAux(array, rowLength, index + 1, indices);
		}else {
			return getListRowIndicesAux(array, rowLength, index + 1, indices);
		}
	}
	
	public static ArrayList<Integer> getListRowIndices(int[][] array, int rowLength) {
		ArrayList <Integer> indices = new ArrayList <Integer>();
		return getListRowIndicesAux(array, rowLength, 0, indices);
	}
	

	public static int replaceCells(char[][] array, int x, int y, char target, char replacement) {
		if(!isValid(array, x, y)) {
			return 0;
		}
		if(array[x][y] == target) {
			array[x][y] = replacement;
			return 1 + replaceCells(array, x - 1, y, target, replacement)
			+ replaceCells(array, x, y - 1, target, replacement)
			+ replaceCells(array, x + 1, y, target, replacement)
			+ replaceCells(array, x, y + 1, target, replacement)
			+ replaceCells(array, x - 1, y - 1, target, replacement)
			+ replaceCells(array, x - 1, y + 1, target, replacement)
			+ replaceCells(array, x + 1, y - 1, target, replacement)
			+ replaceCells(array, x + 1, y + 1, target, replacement);
		}
		return 0;
	}
	
	public static boolean isValid(char[][] array, int x, int y) {
		if(x < 0 || x >= array.length || y < 0 || y >= array[x].length) {
			return false;
		}
		return true;
	}
}
