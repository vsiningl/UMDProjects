package system;

public class CombineLeftRight implements Diagram {

	private int aniType;
	private char[][] fulldi;

	public CombineLeftRight(Diagram left, Diagram right, int animationType) {
		if (right.getBoard().length != left.getBoard().length) {
			throw new IllegalArgumentException();
		}
		this.fulldi = TwoDimArrayUtil.appendLeftRight(left.getBoard(), right.getBoard());
		this.aniType = animationType;
	}

	@Override
	public char[][] getBoard() {
		return fulldi;
	}

	@Override
	public char[][] nextAnimationStep() {
		if (aniType == 1) {
			TwoDimArrayUtil.rotateLeftOneColumn(fulldi);
		}
		if (aniType == 2) {
			TwoDimArrayUtil.rotateTopOneRow(fulldi);
		}
		return fulldi;
	}

	@Override
	public int getNumberRows() {
		return fulldi.length;
	}

	@Override
	public int getNumberCols() {
		return fulldi[0].length;
	}

}
