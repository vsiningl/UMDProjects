
//why are there so many imports
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InterestTableGUI extends Application {
	// variables i use in inner classes
	private TextField principle, rate;
	private Slider years;
	private TextArea result;

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane grid = new GridPane();
		grid.setHgap(1);
		grid.setVgap(8);
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setAlignment(Pos.BOTTOM_CENTER);

		Button siboop = new Button("Simple Interest");
		// inner class at the bottom
		siboop.setOnAction(new ButtonHandler());

		Button ciboop = new Button("Compound Interest");
		// anon class, overrides inner class
		ciboop.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double principleVal = Double.parseDouble(principle.getText());
				double rateVal = Double.parseDouble(rate.getText());
				double yearVal = years.getValue();
				double yearlyInterest = 0.0;
				String output = "Principle: " + String.format("%.2f", principleVal) + ", Rate: "
						+ String.format("%.2f", rateVal) + "\nYear, Compound Interest Amount";
				for (int i = 1; i <= yearVal; i++) {
					yearlyInterest = Interest.compound(principleVal, i, rateVal);
					output += "\n" + i + "-->" + "$" + String.format("%.2f", yearlyInterest);
				}
				result.setText(output);
			}
		});

		Button biboop = new Button("Both Interests");
		// lambda expression
		biboop.setOnAction(e -> {
			double principleVal = Double.parseDouble(principle.getText());
			double rateVal = Double.parseDouble(rate.getText());
			double yearVal = years.getValue();
			double yearlyInterest1 = 0.0;
			double yearlyInterest2 = 0.0;
			String output = "Principle: " + String.format("%.2f", principleVal) + ", Rate: "
					+ String.format("%.2f", rateVal) + "\nYear, Simple Interest Amount, Compound Interest Amount";
			for (int i = 1; i <= yearVal; i++) {
				yearlyInterest1 = Interest.simple(principleVal, i, rateVal);
				yearlyInterest2 = Interest.compound(principleVal, i, rateVal);
				output += "\n" + i + "-->$" + String.format("%.2f", yearlyInterest1) + "-->$"
						+ String.format("%.2f", yearlyInterest2);
			}
			result.setText(output);
		});

		Label priLabel = new Label("Principle: ");
		principle = new TextField();

		Label rateLabel = new Label(" Rate(Percentage): ");
		rate = new TextField();

		Label yearLabel = new Label("Number of Years: ");
		// 25year max
		years = new Slider();
		years.setMin(0);
		years.setMax(25);
		years.setValue(0);
		years.setMajorTickUnit(1);
		years.setShowTickMarks(true);
		years.setShowTickLabels(true);
		years.setMinWidth(400);

		result = new TextArea();
		result.setEditable(false);
		result.setMinHeight(200);
		result.setMinWidth(500);

		// adds nodes to hboxes, puts the hboxes in a vbox, then the vbox into lower
		// center gridpane
		HBox hbox1 = new HBox();
		hbox1.setPadding(new Insets(10, 20, 10, 5));
		hbox1.getChildren().addAll(priLabel, principle, rateLabel, rate);

		HBox hbox2 = new HBox();
		hbox2.getChildren().addAll(yearLabel, years);

		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(10, 5, 10, 10));
		hbox3.getChildren().addAll(siboop, ciboop, biboop);

		VBox vbox4 = new VBox();
		vbox4.setPadding(new Insets(10, 10, 10, 10));
		vbox4.getChildren().addAll(hbox1, hbox2, hbox3);

		grid.add(result, 1, 0);
		grid.add(vbox4, 1, 1, 3, 3);

		Scene screen = new Scene(grid, 700, 400);
		primaryStage.setTitle("Interest Calculator");
		primaryStage.setScene(screen);

		primaryStage.show();
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double principleVal = Double.parseDouble(principle.getText());
			double rateVal = Double.parseDouble(rate.getText());
			double yearVal = years.getValue();
			double yearlyInterest = 0.0;
			String output = "Principle: " + String.format("%.2f", principleVal) + ", Rate: "
					+ String.format("%.2f", rateVal) + "\nYear, Simple Interest Amount";
			for (int i = 1; i <= yearVal; i++) {
				yearlyInterest = Interest.simple(principleVal, i, rateVal);
				output += "\n" + i + "-->" + "$" + String.format("%.2f", yearlyInterest);
			}
			result.setText(output);
		}
	}

	public static void main(String[] args) {
		Application.launch();
	}

}
