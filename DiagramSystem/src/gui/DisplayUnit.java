package gui;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 * See main method for color mappings and an example.
 * 
 * @author cmsc131
 *
 */
public class DisplayUnit extends JComponent {
	private static final long serialVersionUID = 1L;
	private static final int EXTRA_SPACE = 45;
	private static final int FRAME_POSITION = 5; // center use 2
	private static boolean RAISED_CELL = true;
	private BoardCell[][] board;
	private int rows, cols;
	private JFrame frame;
	private int cellDimensions;

	public DisplayUnit(final String title, int cellDimension) {
		this.cellDimensions = cellDimension;
		Runnable createShowGUI = new Runnable() {
			public void run() {
				frame = new JFrame(title);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(DisplayUnit.this);
				
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int upperLeftCornerX = (screenSize.width - frame.getWidth()) / FRAME_POSITION;
				int upperLeftCornerY = (screenSize.height - frame.getHeight()) / FRAME_POSITION;
				frame.setLocation(upperLeftCornerX, upperLeftCornerY);
			}
		};
		SwingUtilities.invokeLater(createShowGUI);
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	protected void paintComponent(Graphics g) {
		Graphics g2 = (Graphics2D) g;

		/* Setting background */
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		/* Drawing the diagram */
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				g2.setColor(board[row][col].getColor());
				g2.fill3DRect(col * cellDimensions, row * cellDimensions, cellDimensions, cellDimensions, RAISED_CELL);
			}
		}
	}

	public void renderDiagram(final String diagram) {
		board = BoardCell.getBoard(diagram);
		renderDiagram();
	}

	public void renderDiagram(char[][] charArray) {
		board = BoardCell.getBoard(charArray);
		renderDiagram();
	}

	private void renderDiagram() {
		Runnable createShowGUI = new Runnable() {
			public void run() {
				rows = board.length;
				cols = board[0].length;
				frame.setSize(getCols() * cellDimensions + EXTRA_SPACE, getRows() * cellDimensions + EXTRA_SPACE);
				frame.setVisible(true);
				repaint();
			}
		};
		SwingUtilities.invokeLater(createShowGUI);
	}

	public static void main(String[] args) {
		/* R->Red, G->Green, B->Blue, Y->Yellow, *->Black, .->White */
		String diagram = "RGB\nY*.";
		String diagram2 = "RRRRRRR\nGGGGGGG\nRRRRRRR\nGGGGGGG";

		int cellDimension = 8;
		DisplayUnit display = new DisplayUnit("Diagram's Display", cellDimension);
		display.renderDiagram(diagram);
		JOptionPane.showMessageDialog(null, "Display next");
		display.renderDiagram(diagram2);

		char[][] diagram3 = { { 'R', 'G', 'B' }, { 'Y', '*', '.' } };
		JOptionPane.showMessageDialog(null, "Display next");
		display.renderDiagram(diagram3);
	}
}