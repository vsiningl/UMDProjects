package system;

import app.DrawingApp;
import gui.BoardCell;

public class VerticalBars implements Diagram{

	private char[][] vbars;
	private int aniType;
	
	public VerticalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3, int animationType) {
		this.vbars = BoardCell.getCharArray(DrawingApp.getVerticalBars(maxRows, maxCols, bars, color1, color2, color3));
		this.aniType = animationType;
	}
	
	@Override
	public char[][] getBoard() {
		return vbars;
	}

	@Override
	public char[][] nextAnimationStep() {
		if(aniType == 1) {
			TwoDimArrayUtil.rotateLeftOneColumn(vbars);
		}
		return vbars;
	}

	@Override
	public int getNumberRows() {
		return vbars.length;
	}

	@Override
	public int getNumberCols() {
		return vbars[0].length;
	}
	

}
