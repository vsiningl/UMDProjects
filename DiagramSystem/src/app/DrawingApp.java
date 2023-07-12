package app;

import java.util.Random;

public class DrawingApp {

	public static String getRectangle(int maxRows, int maxCols, char symbol) {
		String rectangle = ("");
		if (maxRows < 1 || maxCols < 1) {
			return null;
		}
		for (int n = 1; n <= maxRows; n++) {
			for (int c = 1; c <= maxCols; c++) {
				rectangle += symbol;
			}
			rectangle += "\n";
		}
		return rectangle.trim();
	}

	public static String getFlag(int size, char color1, char color2, char color3) {
		int maxRows = size * 2;
		int maxCols = size * 5;
		String rectangle = ("");

		if (size < 3) {
			return null;
		}
		for (int n = 1; n <= (size); n++) {
			for (int x = 1; x <= n; x++) {
				rectangle += color1;
			}
			for (int c = 1; c <= maxCols - n; c++) {
				if (n == 1 || n == size) {
					rectangle += color2;
				} else {
					rectangle += color3;
				}
			}
			rectangle += "\n";
		}
		for (int n = maxRows / 2; n >= 1; n--) {
			for (int x = 1; x <= n; x++) {
				rectangle += color1;
			}
			for (int c = 1; c <= maxCols - n; c++) {
				if (n == size || n == 1) {
					rectangle += color2;
				} else {
					rectangle += color3;
				}
			}
			rectangle += "\n";
		}
		return rectangle.trim();
	}

	public static String getHorizontalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		int colorRows = maxRows / bars;
		int limit = 1;
		String rectangle = (" ");

		if (colorRows < 1 || isValidColor(color1) == false || isValidColor(color2) == false
				|| isValidColor(color3) == false) {
			return null;
		}

		for (int b = 1; b <= bars; b++) {
			if (limit > 3) {
				limit = 1;
			}
			for (int x = 1; x <= colorRows; x++) {
				for (int n = 1; n <= maxCols; n++) {
					if (limit == 1) {
						rectangle += color1;
					}
					if (limit == 2) {
						rectangle += color2;
					}
					if (limit == 3) {
						rectangle += color3;
					}
				}
				rectangle += "\n";
			}

			limit++;
		}
		return rectangle.trim();

	}

	public static String getVerticalBars(int maxRows, int maxCols, int bars, char color1, char color2, char color3) {
		int colorCols = maxCols / bars;
		int limit = 1;
		String rectangle = (" ");

		if (colorCols < 1 || isValidColor(color1) == false || isValidColor(color2) == false
				|| isValidColor(color3) == false) {
			return null;
		}

		for (int y = 1; y <= maxRows; y++) {
			for (int b = 1; b <= bars; b++) {
				for (int n = 1; n <= colorCols; n++) {
					if (limit > 3) {
						limit = 1;
					}
					if (limit == 1) {
						rectangle += color1;
					}
					if (limit == 2) {
						rectangle += color2;
					}
					if (limit == 3) {
						rectangle += color3;
					}
				}
				limit++;
			}
			rectangle += "\n";
			limit = 1;
		}
		return rectangle.trim();
	}

	public static char getRandomColor(Random random) {
		int n = random.nextInt(6);
		char color = ' ';
		if (n == 0) {
			color = 'R';
		}
		if (n == 1) {
			color = 'G';
		}
		if (n == 2) {
			color = 'B';
		}
		if (n == 3) {
			color = 'Y';
		}
		if (n == 4) {
			color = '*';
		}
		if (n == 5) {
			color = '.';
		}
		return color;
	}

	private static boolean isValidColor(char color) {
		boolean colorReturn = false;
		if (color == 'R') {
			colorReturn = true;
		}
		if (color == 'G') {
			colorReturn = true;
		}
		if (color == 'B') {
			colorReturn = true;
		}
		if (color == 'Y') {
			colorReturn = true;
		}
		if (color == '*') {
			colorReturn = true;
		}
		if (color == '.') {
			colorReturn = true;
		}
		return colorReturn;
	}
}