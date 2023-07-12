package system;

import app.DrawingApp;
import gui.BoardCell;

public class HorizontalBars implements Diagram{
	
	private char[][] hbars;
	private int aniType;

	public HorizontalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3, int animationType) {
		this.hbars = BoardCell.getCharArray(DrawingApp.getHorizontalBars(maxRows, maxCols, bars, color1, color2, color3));
		this.aniType = animationType;
	}
	
	@Override
	public char[][] getBoard() {
		return hbars;
	}

	@Override
	public char[][] nextAnimationStep() {
		if(aniType == 1) {
			TwoDimArrayUtil.rotateTopOneRow(hbars);
		}
		return hbars;
	}

	@Override
	public int getNumberRows() {
		return hbars.length;
	}

	@Override
	public int getNumberCols() {
		return hbars[0].length;
	}

}
