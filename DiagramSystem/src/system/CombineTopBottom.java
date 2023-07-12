package system;

public class CombineTopBottom implements Diagram {

	private int aniType;
	private char[][] fulldi;

	public CombineTopBottom(Diagram top, Diagram bottom, int animationType) {
		if (top.getBoard().length != bottom.getBoard().length) {
			throw new IllegalArgumentException();
		}
		this.fulldi = TwoDimArrayUtil.appendTopBottom(top.getBoard(), bottom.getBoard());
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
			return fulldi;
		}
		if (aniType == 2) {
			TwoDimArrayUtil.rotateTopOneRow(fulldi);
			return fulldi;
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
