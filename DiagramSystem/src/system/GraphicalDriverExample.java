package system;
import javax.swing.JOptionPane;

import gui.*;

/**
 * Sample driver illustrating the displaying and animation of
 * diagrams.
 * 
 * @author cmsc132
 *
 */
public class GraphicalDriverExample {
	public static void main(String[] args) {
		char color1 = 'R', color2 = '.', color3 = 'Y';
		int size = 9, animationType = 1;
			
		/* Controls size of squares (cells) */
		int cellDimensions = 15;
		int animationSpeedInMilliSecs = 100;
		
		/* Initializing display unit */
		GraphicalUtilities.initDisplayUnit("Display", cellDimensions);
		
		/* Flag example */
		Flag flag = new Flag(size, color1, color2, color3, animationType);
		/* Displaying flag */
		GraphicalUtilities.displayDiagram(flag);
		/* Animating flag */
		JOptionPane.showMessageDialog(null, "Press OK to start animation");
		GraphicalUtilities.animate(flag, animationSpeedInMilliSecs);
		
		
		/* Horizontal bars example */
		int maxRows = 12, maxCols = 10, bars = 2;
        color1 = 'R'; color2 = 'G'; color3 = 'B';
        animationType = 1;
		HorizontalBars horizontalBars = new HorizontalBars(maxRows, maxCols, bars, 
										color1, color2, color3, animationType);
		/* Displaying bars */
		GraphicalUtilities.displayDiagram(horizontalBars);
		/* Animating bars */
		JOptionPane.showMessageDialog(null, "Press OK to start animation");
		GraphicalUtilities.animate(horizontalBars, animationSpeedInMilliSecs);
		
		
		/* Vertical bars example */
		maxRows = 12; maxCols = 10; bars = 2;
        color1 = 'R'; color2 = 'G'; color3 = 'B';
        animationType = 1;
		VerticalBars verticalBars = new VerticalBars(maxRows, maxCols, bars, 
										color1, color2, color3, animationType);
		/* Displaying bars */
		GraphicalUtilities.displayDiagram(verticalBars);
		/* Animating bars */
		JOptionPane.showMessageDialog(null, "Press OK to start animation");
		GraphicalUtilities.animate(verticalBars, animationSpeedInMilliSecs);
		
		
		/* Combining left and right */
		horizontalBars = new HorizontalBars(maxRows, maxCols, bars, 
						 color1, color2, color3, animationType);
		verticalBars = new VerticalBars(maxRows, maxCols, bars, 
					   color1, color2, color3, animationType);
		Diagram combined = new CombineLeftRight(horizontalBars, verticalBars, 1);
		/* Displaying combined */
		GraphicalUtilities.displayDiagram(combined);
		/* Animating combined */
		JOptionPane.showMessageDialog(null, "Press OK to start animation");
		GraphicalUtilities.animate(combined, animationSpeedInMilliSecs);
		
		
		/* Combining top and bottom */
		horizontalBars = new HorizontalBars(maxRows, maxCols, bars, 
						 color1, color2, color3, animationType);
		verticalBars = new VerticalBars(maxRows, maxCols, bars, 
					   color1, color2, color3, animationType);
		combined = new CombineTopBottom(horizontalBars, verticalBars, 1);
		/* Displaying combined */
		GraphicalUtilities.displayDiagram(combined);
		/* Animating combined */
		JOptionPane.showMessageDialog(null, "Press OK to start animation");
		GraphicalUtilities.animate(combined, animationSpeedInMilliSecs);
		
		System.exit(0);
	}
}