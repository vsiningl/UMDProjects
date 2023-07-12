package tubeVideosManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A playlist keeps tracks of titles and it has a name. An ArrayList of string
 * references stores titles.
 * 
 * @author UMCP CS Department
 *
 */

public class Playlist {
	private String name;
	private ArrayList<String> videoTitles;

	/**
	 * Initializes playlist with the specified name and creates an empty ArrayList.
	 * If the parameter is null or is a blank string (according to String class
	 * isBlank() method) the method will throw an IllegalArgumentException (with any
	 * message) and perform no processing.
	 * 
	 * @param name
	 */
	public Playlist(String name) {
		if (name.isBlank() || name.equals(null)) {
			throw new IllegalArgumentException("try again bozo");
		} else {
			this.name = name;
			this.videoTitles = new ArrayList<String>();
		}
	}

	/**
	 * Get method for name
	 * 
	 * @return name string
	 */
	public String getName() {
		return name;
	}

	/**
	 * Initializes the current object so changes to the current object will not
	 * affect the parameter object.
	 * 
	 * @param playlist
	 */
	public Playlist(Playlist playlist) {
		this.name = playlist.name;
		this.videoTitles = playlist.getPlaylistVideosTitles();
	}

	/**
	 * Provided; please don't modify. toString for class
	 * 
	 * @return string with object info
	 */
	public String toString() {
		String answer = "Playlist Name: " + name + "\n";

		answer += "VideoTitles: " + videoTitles;

		return answer;
	}

	/**
	 * Adds the title to the Arraylist storing titles. We can add the same video
	 * title several times. If the parameter is null or is a blank string (according
	 * to String class isBlank() method) the method will throw an
	 * IllegalArgumentException (with any message) and perform no processing.
	 * 
	 * @param videoTitle
	 * @return true if title is added; false otherwise
	 */
	public boolean addToPlaylist(String videoTitle) {
		if (videoTitle.isBlank() || videoTitle.equals(null)) {
			throw new IllegalArgumentException("try again bozo");
		}
		videoTitles.add(videoTitle);
		return true;
	}

	/**
	 * Get method for the ArrayList of titles. You must avoid privacy leaks.
	 * 
	 * @return ArrayList with titles
	 */
	public ArrayList<String> getPlaylistVideosTitles() {
		ArrayList<String> copy = new ArrayList<String>();
		for (int i = 0; i < videoTitles.size(); i++) {
			copy.add(videoTitles.get(i));
		}
		return copy;
	}

	/**
	 * Removes all instances of the title parameter from the ArrayList of titles. If
	 * the parameter is null or is a blank string (according to String class
	 * isBlank() method) the method will throw an IllegalArgumentException (with any
	 * message) and perform no processing.
	 * 
	 * @param videoTitle
	 * @return true if the ArrayList (videoTitles) was changed as a result of
	 *         calling this method and false otherwise.
	 * 
	 */
	public boolean removeFromPlaylistAll(String videoTitle) {
		if (videoTitle.isBlank() || videoTitle.equals(null)) {
			throw new IllegalArgumentException("try again bozo");
		} else {
			int c = videoTitles.size();
			for (int i = 0; i < videoTitles.size(); i++) {
				if (videoTitles.get(i).equals(videoTitle)) {
					videoTitles.remove(i);
					i--;
				}
			}
			if (c != videoTitles.size()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Randomizes the list of titles using a random parameter and
	 * Collections.shuffle. If the parameter is null, call Collections.shuffle with
	 * just the ArrayList.
	 * 
	 * @param random
	 */
	public void shuffleVideoTitles(Random random) {
		if (random == null) {
			Collections.shuffle(videoTitles);
		} else {
			Collections.shuffle(videoTitles, random);
		}
	}
}
