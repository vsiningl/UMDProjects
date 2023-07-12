package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import model.BoardCell;
import model.ClearCellGame;
import model.Game;

/* The following directive executes tests in sorted order */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StudentTests {
		private static String getBoardStr(Game game) {
		int maxRows = game.getMaxRows(), maxCols = game.getMaxCols();

		String answer = "Board(Rows: " + maxRows + ", Columns: " + maxCols + ")\n";
		for (int row = 0; row < maxRows; row++) {
			for (int col = 0; col < maxCols; col++) {
				answer += game.getBoardCell(row, col).getName();
			}
			answer += "\n";
		}

		return answer;
	}
	/* Remove the following test and add your tests */
	@Test
	public void gameConstruct() {
		String boardTest = "Board(Rows: 3, Columns: 3)\n...\n...\n...\n";
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		assertTrue(getBoardStr(thing).equals(boardTest));
	}
	
	@Test
	public void gameColSetters() {
		String boardTest = "Board(Rows: 3, Columns: 3)\nBR.\n.R.\n.R.\n";
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		thing.setBoardCell(0, 0, BoardCell.BLUE);
		thing.setColWithColor(1, BoardCell.RED);
		assertTrue(getBoardStr(thing).equals(boardTest));
	}
	
	@Test
	public void gameRowSetters() {
		String boardTest = "Board(Rows: 3, Columns: 3)\nBBB\nRRR\nBBB\n";
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		thing.setBoardWithColor(BoardCell.BLUE);
		thing.setRowWithColor(1, BoardCell.RED);
		assertTrue(getBoardStr(thing).equals(boardTest));
	}
	
	@Test
	public void gameGetters() {
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		thing.setBoardWithColor(BoardCell.BLUE);
		thing.setRowWithColor(1, BoardCell.RED);
		thing.setBoardCell(0, 0, BoardCell.GREEN);
		thing.setColWithColor(1, BoardCell.RED);
		String result = thing.getBoardCell(0, 0).toString() + thing.getMaxCols()
						+ thing.getMaxRows();
		assertTrue(result.equals("GREEN33"));
	}
	
	@Test
	public void ccGameOverAnimation() {
		String boardTest = "Board(Rows: 3, Columns: 3)\nRGY\nYRR\nRYB\n";
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		thing.nextAnimationStep();
		thing.nextAnimationStep();
		thing.nextAnimationStep();
		assertTrue(thing.isGameOver());
		assertTrue(getBoardStr(thing).equals(boardTest));
	}
	
	@Test
	public void ccProcessCellScore() {
		String boardTest = "Board(Rows: 3, Columns: 3)\n..B\n...\n...\n";
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		thing.setBoardWithColor(BoardCell.BLUE);
		thing.setRowWithColor(2, BoardCell.EMPTY);
		thing.setColWithColor(1, BoardCell.RED);
		thing.setRowWithColor(1, BoardCell.RED);
		thing.setBoardCell(0, 0, BoardCell.RED);
		thing.processCell(1, 1);
		assertTrue(getBoardStr(thing).equals(boardTest));
		assertTrue(thing.getScore() == 6);
	}
	
	@Test
	public void ccProcessCellScore2() {
		String boardTest = "Board(Rows: 3, Columns: 3)\n..G\n...\n...\n";
		Game thing = new ClearCellGame(3,3, new Random(1L), 1);
		thing.setBoardWithColor(BoardCell.BLUE);
		thing.setRowWithColor(2, BoardCell.EMPTY);
		thing.setBoardCell(0, 2, BoardCell.GREEN);
		thing.processCell(0, 1);
		assertTrue(getBoardStr(thing).equals(boardTest));
		assertTrue(thing.getScore() == 5);
	}
	
	@Test
	public void ccProcessCellScore3() {
		String boardTest = "Board(Rows: 4, Columns: 3)\n..G\n...\n...\n...\n";
		Game thing = new ClearCellGame(4,3, new Random(1L), 1);
		thing.setRowWithColor(0, BoardCell.BLUE);
		thing.setRowWithColor(1, BoardCell.BLUE);
		thing.setBoardCell(0, 2, BoardCell.GREEN);
		thing.processCell(0, 1);
		assertTrue(getBoardStr(thing).equals(boardTest));
		assertTrue(thing.getScore() == 5);
	}
}
