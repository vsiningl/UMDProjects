package gui;

import java.awt.*;
import java.util.Random;

/**
 * This enumerated type represents a board cell. A board cell has a color (based
 * on Color) and a name (e.g., "R").
 * 
 * @author Dept of Computer Science, UMCP
 */

public enum BoardCell {
	RED(Color.RED, "R"), GREEN(Color.GREEN, "G"), BLUE(Color.BLUE, "B"), YELLOW(
			Color.YELLOW, "Y"), BLACK(Color.BLACK, "*"), WHITE(Color.WHITE, ".");

	private final Color color;
	private final String name;
	private static int totalColors = BoardCell.values().length;

	private BoardCell(Color color, String name) {
		this.color = color;
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public static int getTotalColors() {
		return totalColors;
	}

	/**
	 * Generates a random BoardCell using the specified Random object.
	 * 
	 * @param random
	 * @return random BoardCell
	 */
	public static BoardCell getNonEmptyRandomBoardCell(Random random) {
		int target = random.nextInt(totalColors);
		for (BoardCell boardCell : BoardCell.values()) {
			if (boardCell == BoardCell.WHITE)
				return BoardCell.RED;
			if (target == boardCell.ordinal())
				return boardCell;
		}
		throw new IllegalArgumentException("Invalid random number generated");
	}
	
	public static char[][] getCharArray(String diagram) {
		if (diagram == null || diagram.length() == 0) {
			return null;
		} else {
			int maxRows = 0, maxCols = 0;

			/* Computing number of rows */
			for (int i = 0; i < diagram.length(); i++) {
				if (diagram.charAt(i) == '\n') {
					maxRows++;
				}
			}
			maxRows++; // last row does not have '\n'

			/* Computing row length */
			for (int i = 0; i < diagram.length() && diagram.charAt(i) != '\n'; i++) {
				maxCols++;
			}

			char board[][] = new char[maxRows][maxCols];
			int strIndex = 0;
			for (int row = 0; row < maxRows; row++) {
				for (int col = 0; col < maxCols; col++) {
					board[row][col] = diagram.charAt(strIndex++);
				}
				strIndex++; // skipping '\n'
			}
			
			return board;
		}
	}
	
	public static BoardCell[][] getBoard(char[][] charArray) {
		BoardCell[][] board = new BoardCell[charArray.length][charArray[0].length];
	
		for (int row = 0; row < charArray.length; row++) {
			for (int col = 0; col < charArray[row].length; col++) {
				switch(charArray[row][col]) {
					case 'R':
						board[row][col] = BoardCell.RED;
						break;
					case 'G':
						board[row][col] = BoardCell.GREEN;
						break;
					case 'B':
						board[row][col] = BoardCell.BLUE;
						break;
					case 'Y':
						board[row][col] = BoardCell.YELLOW;
						break;
					case '*':
						board[row][col] = BoardCell.BLACK;
						break;
					case '.':
						board[row][col] = BoardCell.WHITE;
						break;
					default:
						System.err.println("**** getBoard(): Invalid color ****");
						break;
				}
			}
		}
		
		return board;
	}
	
	public static BoardCell[][] getBoard(String diagram) {
		if (diagram == null || diagram.length() == 0) {
			return null;
		} else {
			return getBoard(getCharArray(diagram));
		} 
	}
}