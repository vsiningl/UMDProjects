package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import system.Diagram;
import javax.swing.*;

/**
 * This class provides methods that display a diagram and animate a diagram.
 * 
 * @author cmsc132
 *
 */
public class GraphicalUtilities {
	private static DisplayUnit displayUnit;

	/**
	 * This method must be called before displaying a diagram or animating a diagram.
	 * 
	 * @param title title to display in the GUI
	 * @param cellDimensions size of the square/cell associated with the diagram
	 */
	public static void initDisplayUnit(String title, int cellDimensions) {
		if (displayUnit != null) {
			JOptionPane.showMessageDialog(null, "Display Unit already initialized");
		} else {
			displayUnit = new DisplayUnit(title, cellDimensions);
		}
	}

	/** 
	 * Displays a diagram.  The initDisplayUnit method must have been called before
	 * calling this method.
	 * 
	 * @param diagram
	 */
	public static void displayDiagram(Diagram diagram) {
		if (displayUnit == null) {
			JOptionPane.showMessageDialog(null, "Display Unit not initialized");
			return;
		}
		
		displayUnit.renderDiagram(diagram.getBoard());
	}

	/** 
	 * Animates a diagram.  The initDisplayUnit method must have been called before
	 * calling this method.
	 * 
	 * @param diagram
	 * @param speedInMilliSecs animation speed
	 */
	public static void animate(Diagram diagram, int speedInMilliSecs) {
		if (displayUnit == null) {
			JOptionPane.showMessageDialog(null, "Display Unit not initialized");
			return;
		}
		
		ActionListener animator = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				char[][] nextStep = diagram.nextAnimationStep();
				displayUnit.renderDiagram(nextStep);
			}
		};

		Timer timer = new Timer(speedInMilliSecs, animator);
		timer.start();
		JOptionPane.showMessageDialog(null, "Press OK to stop animation");
		timer.stop();
	}
}