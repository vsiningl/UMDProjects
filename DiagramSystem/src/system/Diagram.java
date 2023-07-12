package system;

/**
 * Interface implemented by any class representing a Diagram. A diagram
 * is defined by a two-dimensional array of characters (board), where each character
 * represents a color.  Colors: Red ('R'), Green ('G'), Blue ('B'), Yellow ('Y'),
 * Black ('*'), White ('.').
 * 
 * @author cmsc132
 *
 */
public interface Diagram {
	/**
	 * Returns a two-dimensional array of characters representing a diagram.
	 * @return
	 */
	public char[][] getBoard();
	
	/**
	 * Returns the next two-dimensional array of characters to display during
	 * an animation.
	 * @return
	 */
	public char[][] nextAnimationStep();
	
	/**
	 * Number of rows associated with the diagram.
	 * @return
	 */
	public int getNumberRows();
	
	/**
	 * Number of columns associated with the diagram.
	 * @return
	 */
	public int getNumberCols();
}
