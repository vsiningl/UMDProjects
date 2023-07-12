package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Department of Computer Science, UMCP
 */

public abstract class Game {
	protected BoardCell[][] board;
	int maxRows, maxCols;

	/**
	 * Defines a board with BoardCell.EMPTY cells.
	 * 
	 * @param maxRows
	 * @param maxCols
	 */
	public Game(int maxRows, int maxCols) {
		this.board = new BoardCell[maxRows][maxCols];
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		for(int i = 0; i < maxRows; i++) {
			for(int h = 0; h < maxCols; h++) {
				board[i][h] = BoardCell.EMPTY;
			}
		}
	}

	public int getMaxRows() {
		return maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}

	//returns with index
	public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
		board[rowIndex][colIndex] = boardCell;
	}

	public BoardCell getBoardCell(int rowIndex, int colIndex) {
		return board[rowIndex][colIndex];
	}

	/**
	 * Initializes row with the specified color.
	 * 
	 * @param rowIndex
	 * @param cell
	 */
	
	//loops through row based on index to set color
	public void setRowWithColor(int rowIndex, BoardCell cell) {
		for(int i = 0; i < maxCols; i++) {
			board[rowIndex][i] = cell;
		}
	}

	/**
	 * Initializes column with the specified color.
	 * 
	 * @param colIndex
	 * @param cell
	 */
	
	//same as above but loops through column
	public void setColWithColor(int colIndex, BoardCell cell) {
		for (int i = 0; i < maxRows; i++) {
			board[i][colIndex] = cell;
		}
	}

	/**
	 * Initializes the board with the specified color.
	 * 
	 * @param cell
	 */
	
	//fills the board, loops through EVERYTHING
	public void setBoardWithColor(BoardCell cell) {
		for(int i = 0; i < maxRows; i++) {
			for(int h = 0; h < maxCols; h++) {
				board[i][h] = cell;
			}
		}
	}

	public abstract boolean isGameOver();

	public abstract int getScore();

	/**
	 * Advances the animation one step.
	 */
	public abstract void nextAnimationStep();

	/**
	 * Adjust the board state according to the current board state and the selected
	 * cell.
	 * 
	 * @param rowIndex
	 * @param colIndex
	 */
	public abstract void processCell(int rowIndex, int colIndex);
}