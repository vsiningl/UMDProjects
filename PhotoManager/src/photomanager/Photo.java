package photomanager;

/**
 * The Photo class represents a photo. A photo has a source (url o path to the
 * file), the width and height in pixels, the date the photo was taken and
 * comments the user has added (if any). If you look at the class definition,
 * after "public class Photo" you will see "implements Comparable&lt;Photo&gt;".
 * You can ignore it; it means that the Photo class must implement a method
 * called compareTo that takes a Photo as a parameter and returns an integer.
 * Why we need it? Because it will allow us to sort ArrayList of Photos using
 * the compareTo method that you will define. This description has been written
 * under the influence of a lot of caffiene :)
 * 
 * @author UMCP CS Department
 *
 */
public class Photo implements Comparable<Photo> {

	// instance variables!
	private String photoSource;
	private int width, height;
	private String date;
	private StringBuffer comments;

	/**
	 * Initializes the instance variables with the provided parameter values and
	 * initializes comments with a StringBuffer object. It will throw an
	 * IllegalArgumentException with the message "Constructor: Invalid arguments" if
	 * any string parameter is null or blank, or if the width or height (or both)
	 * are negative or zero. Use the String class isBlank() method to verify whether
	 * a string is blank. You can assume that if a date is provided, it represents a
	 * valid date. You don't need to verify whether photoSource represents a photo
	 * that exists.
	 * 
	 * @param photoSource Url or file location.
	 * @param width       Photo's width in pixels.
	 * @param height      Photo's height in pixels.
	 * @param date        Date the photo was taken.
	 * @throws IllegalArgumentException For invalid parameter.
	 */

	// if any parameters are null or blank or inapplicable, then it throws an IAE
	// if not, then it initializes a Photo object with the given parameters
	public Photo(String photoSource, int width, int height, String date) {

		if (photoSource == null || photoSource.isBlank() || date == null || 
			date.isBlank() || width <= 0 || height <= 0) {
			throw new IllegalArgumentException("Constructor: Invalid arguments");
		}

		this.photoSource = photoSource;
		this.width = width;
		this.height = height;
		this.date = date;
		this.comments = new StringBuffer();

	}

	/**
	 * Returns a string with photoSource, width, height and date values separated by
	 * commas. Comments are not part of the string.
	 * 
	 * @return String with values separated by commas.
	 */

	// it returns a string with the parameters separated by commas and no spaces
	// overrides other toStrings
	public String toString() {
		String result = photoSource + "," + width + "," + height + "," + date;
		return result;
	}

	/**
	 * Get method for photoSource.
	 * 
	 * @return photoSource.
	 */

	public String getPhotoSource() {
		return photoSource;
	}

	/**
	 * Get method for width.
	 * 
	 * @return width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get method for height.
	 * 
	 * @return height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Get method for date.
	 * 
	 * @return date.
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Appends the newComment parameter to the comments StringBuffer. Comments must
	 * be separated by commas (the last comment should not be followed by a comma).
	 * If the parameter is null or blank (according to the String class isBlank()
	 * method) no comment will be appended, and the IllegalArgumentException with
	 * the message "Invalid comment" will be thrown. The method returns a reference
	 * to the current object.
	 * 
	 * @param newComment Comment to add.
	 * @return Reference to the current object.
	 */

	// throws IAE if the parameter is blank or null
	// if the comments StringBuffer is empty, then then it's appended with the
	// parameter and without a comma, but if there's something in it then it's
	// appended with a comma first, then the parameter.
	public Photo addComments(String newComment) {
		if (newComment == null || newComment.isBlank()) {
			throw new IllegalArgumentException("Invalid comment");
		}
		if (this.comments.length() == 0) {
			this.comments.append(newComment);
		} else {
			this.comments.append("," + newComment);
		}

		return this;
	}

	/**
	 * Returns a string that corresponds to the comments.
	 * 
	 * @return String corresponding to comments.
	 */

	// returns the comments added to a Photo object as a string.
	public String getComments() {
		StringBuffer newComments = new StringBuffer(comments);
		String stringComments = newComments.toString();
		return stringComments;
	}

	/**
	 * Copy constructor. Modifications to the new object should not affect the
	 * original (parameter) object.
	 * 
	 * @param photo To copy.
	 */

	// copy constructor! makes a new object when given a photo as a parameter.
	// anything applied to the copy doesn't apply to the original parameter.
	public Photo(Photo photo) {
		this.photoSource = photo.photoSource;
		this.width = photo.width;
		this.height = photo.height;
		this.date = photo.date;

		this.comments = new StringBuffer(photo.comments);
	}

	/**
	 * This method will compare the date of the current object against the
	 * parameter's object. Use the Utilities.getDate() method that takes a string as
	 * a parameter and returns an integer (of type long) representing the date. We
	 * can represent dates (including time) using integers. The number returned by
	 * Utilities.getDate() represents the number of seconds that have elapsed since
	 * the Unix epoch (00:00:00 UTC on 1 January 1970). Run the main method of the
	 * Utilities.java class to see examples of these integer values.
	 * 
	 * The compareTo method will return a negative value if the date of the current
	 * object precedes the date of the parameter, zero if the dates are the same,
	 * and a positive value otherwise. Don't be surprise if the amount of code
	 * required for compareTo method is minimal. Do not using casting to convert a
	 * long to int as this could lead to wrong results.
	 * 
	 */

	// uses getDate to get the date of the parameter and the current object,
	// and turn them into longs, then they're compared using something called
	// subtraction(slay) and then casted into an int and returned. you use these
	// in the sortByDate method in photomanager!
	public int compareTo(Photo photo) {
		long date1 = Utilities.getDate(this.date);
		long date2 = Utilities.getDate(photo.date);
		long result = date1 - date2;
		int resultInt = (int) result;

		return resultInt;
	}
}