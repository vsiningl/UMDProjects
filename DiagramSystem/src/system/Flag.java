package system;

import app.DrawingApp;
import gui.BoardCell;

public class Flag implements Diagram{

	private char[][] flag;
	private int aniType;
	
	public Flag(int size, char color1, char color2, char color3, int animationType) {
		flag = BoardCell.getCharArray(DrawingApp.getFlag(size, color1, color2, color3));
		this.aniType = animationType;
	}
	
	@Override
	public char[][] getBoard() {
		return flag;
	}

	@Override
	public char[][] nextAnimationStep() {
		if(aniType == 1) {
			TwoDimArrayUtil.rotateLeftOneColumn(flag);
		}
		if(aniType == 2) {
			TwoDimArrayUtil.rotateTopOneRow(flag);
		}
		return flag;
	}

	@Override
	public int getNumberRows() {
		return flag.length;
	}

	@Override
	public int getNumberCols() {
		return flag[0].length;
	}

}
