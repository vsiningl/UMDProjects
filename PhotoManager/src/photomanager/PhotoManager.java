package photomanager;

import java.io.*;
import java.util.*;

/**
 * The PhotoManager class keeps track of Photos by using an ArrayList of Photo
 * references. The class relies heavily on ArrayList methods. At least you will
 * be using the following ArrayList methods: add, get, remove, clear. Check the
 * Java API ArrayList entry for information about each of these methods.
 * 
 * @author UMCP CS Department
 *
 */
public class PhotoManager {
	private ArrayList<Photo> allPhotos;

	/**
	 * Assigns to the allPhotos instance variable an ArrayList of Photos.
	 */

	// creates an instance of allPhotos, new empty arraylist
	public PhotoManager() {
		allPhotos = new ArrayList<Photo>();
	}

	/**
	 * Creates a Photo based on the provided parameters and adds the photo to the
	 * allPhotos ArrayList. The photo will be added if it does not already exist in
	 * the ArrayList (hint: use the findPhoto method). The method must handle
	 * (try/catch block) any exception thrown by creating a Photo (remember that a
	 * Photo constructor can throw an IllegalArgumentException). If an exception is
	 * thrown by the Photo constructor, the photo will not be added; in addition the
	 * message "addPhoto: Invalid arguments" will be printed to standard error (that
	 * means using System.err.println (notice the err)) and false will be returned.
	 * If a photo is added the method will return true.
	 * 
	 * @param photoSource Photo's url or filename.
	 * @param width       Photo's width in pixels.
	 * @param height      Photo's height in pixels.
	 * @param date        Date the photo was taken.
	 * @return true if photo added; false otherwise.
	 */

	// adds a photo to the arraylist with photo object parameters, so it
	// initializes a new object with the parameters and adds it to allPhotos,
	// but only if it already isn't in the arraylist, which is determined with
	// findPhoto as the if condition. it throws an IAE with a try/catch. there's
	// nothing outside of the try/catch block because if there's an IAE, the
	// method should'nt work.
	public boolean addPhoto(String photoSource, int width, int height, String date) {
		try {
			if (findPhoto(photoSource) == -1) {
				Photo pic = new Photo(photoSource, width, height, date);
				allPhotos.add(pic);
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException e) {
			System.err.println("addPhoto: Invalid arguments");
			return false;
		}
	}

	/**
	 * Returns a string where each Photo is printed on a line by itself.
	 * 
	 * @return String with information about all photos.
	 */

	// turns allPhotos into a string where each photo object is printed on a new
	// line
	public String toString() {
		String pic = "";

		for (int i = 0; i < allPhotos.size(); i++) {
			pic += allPhotos.get(i) + "\n";
		}
		return pic;
	}

	/**
	 * Returns the index in the ArrayList associated with the Photo that has a
	 * photoSource corresponding to the parameter. The method will return -1 if no
	 * photo is found or if the parameter is null.
	 * 
	 * @param photoSource Photo's photoSource.
	 * @return Index in the array or -1 (photo not found).
	 */

	// if the photoSource of the current object and parameter are different, then
	// it keeps going through the array to find one that matches. if there's a
	// match it returns the index of the matching photoSource. the method by
	// default returns -1, so if a match in't found, it returns -1. if the
	// parameter is null it also returns -1.
	public int findPhoto(String photoSource) {
		for (int i = 0; i < allPhotos.size(); i++) {
			Photo pic = new Photo(allPhotos.get(i));
			if (photoSource.equals(pic.getPhotoSource()))
				return i;
		}

		if (photoSource == null) {
			return -1;
		}
		return -1;
	}

	/**
	 * Adds the specified comment to the photo with the specified photoSource (if
	 * such photo is present in allPhotos). It returns true if the comments are
	 * added and false if the photo could not be found, or if the parameters are
	 * invalid. A parameter is invalid if it is null or if the newComment string is
	 * blank (according to the String method isBlank()).
	 * 
	 * @param photoSource PhotoSource of photo we would like to add the comment.
	 * @param newComment  Comment to add.
	 * @return true if comment added; false otherwise.
	 */

	// if the photo doesn't exist, or if any parameters are null, it returns
	// false and nothing is added. if there's a match, which is when something
	// that isn't -1 is returned for findPhoto, the addComments method is called
	// for the photo at the index that matches the parameter. it returns true
	// because a comment is added
	public boolean addComment(String photoSource, String newComment) {
		if (photoSource == null || photoSource.isBlank() || newComment == null 
			|| newComment.isBlank()) {
			return false;
		}
		int index = findPhoto(photoSource);
		allPhotos.get(index).addComments(newComment);
		return true;
	}

	/**
	 * Returns the comments of the photo associated with the specified photoSource.
	 * The method will return null if no photo exists with the specified
	 * photoSource, or if the parameter is null.
	 * 
	 * @param photoSource photo to find comments for.
	 * @return Comments or null.
	 */

	// this returns the comments of a specific photo if it exists in the array,
	// which is checked with findPhoto and the comments of the photo at that
	// index are returned with the getComments Photo method
	public String getComments(String photoSource) {

		if (photoSource == null || photoSource.isBlank() || 
			findPhoto(photoSource) == -1) {
			return null;
		}
		int index = findPhoto(photoSource);
		String picComments = allPhotos.get(index).getComments();
		return picComments;
	}

	/**
	 * Removes all the photos from allPhotos. This method only takes a single line
	 * of code.
	 */

	// clears the whole allPhotos arraylist
	public void removeAllPhotos() {
		allPhotos.clear();
	}

	/**
	 * Removes the Photo with the specified photoSource (if it exists). Returns true
	 * if the photo was removed and false if the photo was not found or the
	 * parameter is null. Remember that you can remove elements from an ArrayList
	 * using the ArrayList remove method.
	 * 
	 * @param photoSource Photo we would like to remove.
	 * @return true if photo was removed; false otherwise.
	 */

	// it removes a photo if it exists in the arraylist, found using findPhoto
	// and the photo at that index is removed and true is returned. if it's not
	// found or the parameter is null, it doesn't do anything and returns false
	public boolean removePhoto(String photoSource) {
		if (findPhoto(photoSource) == -1 || photoSource == null || 
				photoSource.isBlank()) {
			return false;
		} else {
			int index = findPhoto(photoSource);
			allPhotos.remove(index);
			return true;
		}
	}

	/**
	 * Loads the photos specified in filename to the allPhotos ArrayList. It adds to
	 * the ArrayList (it does not clear the ArrayList before adding photos). Each
	 * line of the file will have information about a photo. The information will be
	 * the photoSource, followed by the width, height and date. You can assume the
	 * file will have correct information. The following is an examples of a file
	 * entry: <br>
	 * umcp/college1.jpg 300 400 10/18/2020-17:10 <br>
	 * If an error takes place while opening the file (e.g., file does not exist),
	 * an error message (any message is fine) will be printed using
	 * System.err.println. Notice that your code will not crash when a file could
	 * not be opened; in this case the method will print the error message and
	 * return false. The method will return false and perform no processing when the
	 * parameter is null.
	 * 
	 * @param filename Name of file with information about photos.
	 * @return true if the data was loaded and false otherwise.
	 * 
	 */

	// given a filename it scans the file and while there's more stuff to scan it
	// scans for a string, an int, an int, and a string and initializes them as
	// a photo object and adds it to the arraylist with addPhoto method. there
	// aren't any alternative constructors, so it should work. it's in a
	// try/catch, so if the file can't be read an FNF is thrown, and an error
	// message is printed, and false is returned. if the filename parameter is
	// null or blank false is returned, if there's an FNF, it returns false. it
	// only returns true if the try block runs.
	public boolean loadPhotos(String filename) {
		if (filename == null || filename.isBlank()) {
			return false;
		} else {
			try {
				Scanner reader = new Scanner(new File(filename));
				while (reader.hasNext()) {
					addPhoto(reader.next(), reader.nextInt(), reader.nextInt(), 
							reader.next());
				}
				return true;
			} catch (FileNotFoundException e) {
				System.err.println("uh oh");
			}
		}
		return false;
	}

	/**
	 * Sorts the allPhotos by date. This method requires a single line of code.
	 */

	// uses collections and the overridden date compareTo photo method to compare
	// and sort the allPhotos arraylist
	public void sortPhotosByDate() {
		Collections.sort(allPhotos);
	}

	/**
	 * This method creates an HTML file with all the photos. This method has been
	 * implemented for you and it is not used by any other method.
	 * 
	 * @param htmlFilename Web page with photos.
	 */

	// i didn't have to write this, thank god.
	public void createHTMLPage(String htmlFilename) {
		String body = "";

		for (Photo photo : allPhotos) {
			body += "<img src=\"" + photo.getPhotoSource() + "\" ";
			body += "width=\"" + photo.getWidth() + "\" ";
			body += "height=\"" + photo.getHeight() + "\" ";
			body += "alt=\"photo image\"><br>\n";
		}

		Utilities.generateHTMLPageWithBody(htmlFilename, body);
	}
}