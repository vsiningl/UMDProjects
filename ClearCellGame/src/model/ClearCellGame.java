package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 */

public class ClearCellGame extends Game {
	//instance variables for constructor and convenience
	int strategy, maxRows, maxCols;
	Random random;
	//score variable
	private int score;

	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	
	//super constructor, only way to make game object
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
		//these are mostly for convenience
		this.maxRows = maxRows;
		this.maxCols = maxCols;
		//new score for every new game
		this.score = 0;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	
	//if there's no cell in the row that is filled, then isGameOver is true
	public boolean isGameOver() {
		for (int h = 0; h < maxCols; h++) {
			if (board[maxRows - 1][h] != BoardCell.EMPTY) {
				return true;
			}
		}
		return false;
	}

	public int getScore() {
		return score;
	}

	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	
	//checks for gameover, then loops backwards through the board to move everything 
	//down one row, and leaves the first row untouched
	public void nextAnimationStep() {
		if (!isGameOver()) {
			for (int i = maxRows - 1; i >= 1; i--) {
				for (int h = 0; h < maxCols; h++) {
					board[i][h] = board[i - 1][h];
				}
			}
			//here the first row becomes filled with random cells
			for (int h = 0; h < maxCols; h++) {
				board[0][h] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
		}
	}

	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		//instance variables for ease
		int rowSearch, colSearch;
		//check for non-empty cell to clear
		if(board[rowIndex][colIndex] != BoardCell.EMPTY) {
			//assigns the boardcell to a variable for ease
			BoardCell newCell = board[rowIndex][colIndex];
			//assigns the boardcell to empty
			board[rowIndex][colIndex] = BoardCell.EMPTY;
			//adds to score
			score++;
		
			//up. rowSearch and rowColumn are set to the indexes of the first
			//cells they should check for. rowSearch is the only one changing
			//because this part only searches upward
			rowSearch = rowIndex - 1;
			colSearch = colIndex;
			//while loop because i couldn't figure out a for loop. loops until 
			//the index reaches zero. checks that the cell matches the original
			//processed cell, if it does it clears the cell, if not, it's done
			while(rowSearch >= 0 && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				//adds one to score and changes the row index
				score++;
				rowSearch--;
			}
		
			//down. still only changes rowSearch because it's only searching down
			rowSearch = rowIndex + 1;
			colSearch = colIndex;
			//loops until it reaches the bottom of the board
			while(rowSearch < maxRows && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				rowSearch++;
			}
			
			//left. changes only the column index, subtracts to go left
			rowSearch = rowIndex;
			colSearch = colIndex - 1;
			while(colSearch >= 0 && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				colSearch--;
			}
			
			//right. adds to column index to go right
			rowSearch = rowIndex;
			colSearch = colIndex + 1;
			while(colSearch < maxCols && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				colSearch++;
			}
			
			//up and left. changes both row and col indexes by a factor of one 
			//each time so that they stay diagonal.
			rowSearch = rowIndex - 1;
			colSearch = colIndex - 1;
			while(colSearch >= 0 && rowSearch >= 0 && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				rowSearch--;
				colSearch--;
			}
			
			//up and right. adds to col instead so it goes the oppisite direction
			rowSearch = rowIndex - 1;
			colSearch = colIndex + 1;
			while(colSearch < maxCols && rowSearch >= 0 && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				rowSearch--;
				colSearch++;
			}
			
			//down and left. row index goes up so the diagonal travels down
			rowSearch = rowIndex + 1;
			colSearch = colIndex - 1;
			while(colSearch >= 0 && rowSearch < maxRows && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				rowSearch++;
				colSearch--;
			}
			
			//down and right
			rowSearch = rowIndex + 1;
			colSearch = colIndex + 1;
			while(colSearch < maxCols && rowSearch < maxRows && board[rowSearch][colSearch] ==  newCell) {
				board[rowSearch][colSearch] = BoardCell.EMPTY;
				score++;
				rowSearch++;
				colSearch++;
			}
			
			//this part loops through the array and checks for an empty row
			//followed by a non-empty row, a sign that the loop hasn't reached
			//the end of the board. if there are two empty rows, it'll just
			//loop through the array more than once to close the gap. it also 
			//doesn't clear any rows left at the bottom because it assumes that 
			//there are empty rows because the game isn't over
			for(int i = 0; i < maxRows - 1; i++) {
				if(isEmpty(i) && !isEmpty(i + 1)) {
					for (int h = i; h < maxRows - 1; h++) {
						board[h] = board[h+1];
					}
					//resets i so that it checks the edited 2d array
					i = 0;
				}
			}
		}
	}
	
	//loops through a row given its index to see if it's empty or not
	public boolean isEmpty(int rowIndex) {
		for(int i = 0; i < maxCols; i++) {
			if(board[rowIndex][i] != BoardCell.EMPTY) {
				return false;
			}
		}
		return true;
	}
}