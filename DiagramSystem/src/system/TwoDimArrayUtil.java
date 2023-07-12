package system;

public class TwoDimArrayUtil {

	public static boolean isRagged(char[][] array) {
		//every array has at least one row, so the first row is used as a baseline
		int rowLength = array[0].length;
		boolean result = false;

		for (int row = 0; row < array.length; row++) {
			//if the length of a row isn't the same as the first one, then the 
			//array is jagged, so the boolean is true
			if (rowLength != array[row].length) {
				result = true;
			}
		}
		return result;
	}

	public static void rotateTopOneRow(char[][] array) {
		//if the array is null or ragged, it throws an error, stops processing
		if (array == null || TwoDimArrayUtil.isRagged(array) == true) {
			throw new IllegalArgumentException("try again");
		}
		
		//if the array is only one row, it can't be rotated, so it doesn't process
		//any further
		if (array.length == 1) {
			return;
		}

		//variables to initialize a temp array
		int rowCount = array.length;
		int colCount = array[0].length;
		
		//the temp array! tada
		char[][] copy = new char[rowCount][colCount];
		
		//puts the first row of the parameter array into the last row of the 
		//temp array
		copy[array.length - 1] = array[0];
		
		//copies the rest of the parameter array into the temp array, starting
		//from the second row of the parameter array and ending at the last row
		for (int i = 0; i < array.length - 1; i++) {
			copy[i] = array[i + 1];
		}

		//this casts the temp array back into the parameter array
		for (int row = 0; row < array.length; row++) {
			array[row] = copy[row];
		}
	}

	public static void rotateLeftOneColumn(char[][] array) {
		//if the array is null or jagged it throws an error and ends the method
		if (array == null || isRagged(array) == true) {
			throw new IllegalArgumentException("try again");
		}

		//if an array isn't ragged, then the number of columns in the first row
		//is the number of columns in the whole array, so if it's one, it can't
		//be rotated, so the method ends here.
		if (array[0].length == 1) {
			return;
		}

		//variables to initialize the temp array
		int rowCount = array.length;
		int colCount = array[0].length;

		//the temp array
		char[][] copy = new char[rowCount][colCount];
		
		//for loop to traverse the rows of the array
		for (int row = 0; row < array.length; row++) {
			//a counter for the columns of the row, resets with each row
			int counter = 0;
			//copies the columns into the temp array starting from the second
			//column to the last one.
			for (int col = 1; col < array[row].length; col++) {
				copy[row][counter] = array[row][col];
				counter++;
			}
			//copies the first column into the last column in the tmep array.
			copy[row][array[0].length - 1] = array[row][0];
		}

		//casts temp array back to parameter array
		for (int row = 0; row < array.length; row++) {
				array[row] = copy[row];
		}
	}

	public static char[][] appendTopBottom(char[][] top, char[][] bottom) {

		//new array with enough rows to contain both parameter arrays
		char[][] combined = new char[top.length + bottom.length][];
		//for loop to copy the top array, copies by row because the shape of each
		//array is preserved
		for (int i = 0; i < top.length; i++) {
			combined[i] = top[i];
		}
		//for loop to copy the bottom array starting from the first empty row, 
		//or the row right after the last row of the top array
		for (int i = 0; i < bottom.length; i++) {
			combined[i + top.length] = bottom[i];
		}
		return combined;
	}

	public static char[][] appendLeftRight(char[][] left, char[][] right) {
		int smaller;
		int bigger;
		//this is to determine how many rows to initialize the new array with
		//it has to be big enough for the array with more rows
		if (left.length > right.length) {
			bigger = left.length;
			smaller = right.length;
		} else {
			bigger = right.length;
			smaller = left.length;
		}
		//initializes an array with enough rows for both arrays
		char[][] combined = new char[bigger][];
		
		//for loop to traverse the number of rows used by both arrays
		for (int i = 0; i < smaller; i++) {
			//makes a 1d for each row with enough columns for both arrays
			char[] row = new char[left[i].length + right[i].length];
			//for loop to put the left array in starting with the first column
			for (int h = 0; h < left[i].length; h++) {
				row[h] = left[i][h];
			}
			//for loop to put the right array in starting with the first empty
			//column
			for (int h = 0; h < right[i].length; h++) {
				row[h + left[i].length] = right[i][h];
			}
			//casts the 1d array into the 2d array for the combined parameter
			//arrays
			combined[i] = row;
		}

		//for when one array has more rows than the other, it puts the rest of the
		//larger array into the combined array, starting with the last row of the
		//smaller array. pasting rows works fine because the smaller array has
		//no columns to add, and everything shifts towards the left to fill space
		if (left.length > right.length) {
			for (int i = smaller; i < bigger; i++) {
				combined[i] = left[i];
			}
		} else {
			for (int i = smaller; i < bigger; i++) {
				combined[i] = right[i];
			}
		}
		return combined;
	}
}
