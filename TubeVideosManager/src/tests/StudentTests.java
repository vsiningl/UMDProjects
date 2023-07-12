package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import tubeVideosManager.Genre;
import tubeVideosManager.Playlist;
import tubeVideosManager.TubeVideosManager;
import tubeVideosManager.Video;

/**
 * 
 * You need student tests if you are asking for help during office hours about
 * bugs in your code. Feel free to use tools available in TestingSupport.java
 * 
 * @author UMCP CS Department
 *
 */
public class StudentTests {
	Playlist playlist = new Playlist("billboard top 50");
	Video video = new Video("gangnam style", "www.www", 6, Genre.Documentary);
	Video video2 = new Video("toxic", "xxx.xxx", 5, Genre.FilmAnimation);
	TubeVideosManager youtube = new TubeVideosManager();

	// tests video constructor, copy constructor, video getters
	@Test
	public void vidConstructorsGetters() {
		Video vid = new Video("california gurlz", "xyz.xyz", 8, Genre.Educational);
		Video copy = new Video(vid);
		assertTrue(copy.getTitle().equals("california gurlz"));
		assertTrue(copy.getUrl().equals("xyz.xyz"));
		assertTrue(copy.getDurationInMinutes() == 8);
		assertTrue(copy.getGenre() == Genre.Educational);
	}

	// tests playlist constructor, copy constructor, playlist getter
	@Test
	public void playlistConstructorsGetters() {
		Playlist pl = new Playlist("valentine's day");
		Playlist kl = new Playlist(pl);
		assertTrue(kl.getName().equals("valentine's day"));
	}

	// tests video comments methods, addComments and getComments
	@Test
	public void vidGetAddComments() {
		video.addComments("happy new year");
		video.addComments("stream bts");
		assertTrue(video.getComments().get(0).equals("happy new year"));
		assertTrue(video.getComments().get(1).equals("stream bts"));
	}

	// tests video equals and compares methods
	@Test
	public void vidEqualsCompares() {
		int one = video.compareTo(video);
		boolean two = video.equals(video);
		int three = video.compareTo(video2);
		assertTrue(one == 0);
		assertTrue(two == true);
		assertTrue(three < 0);
	}

	// tests addtoplaylist and videotitle getters
	@Test
	public void playlistAddGet() {
		playlist.addToPlaylist("gangnam style");
		playlist.addToPlaylist("toxic");
		assertTrue(playlist.getPlaylistVideosTitles().get(0).equals("gangnam style"));
	}

	// tests add to playlist and remove from playlists
	@Test
	public void playlistRemove() {
		playlist.addToPlaylist("gangnam style");
		playlist.addToPlaylist("toxic");
		playlist.removeFromPlaylistAll("gangnam style");
		assertTrue(playlist.getPlaylistVideosTitles().get(0).equals("toxic"));
	}

	// tests shuffle
	@Test
	public void playlistShuffle() {
		playlist.addToPlaylist("gangnam style");
		playlist.addToPlaylist("toxic");
		playlist.addToPlaylist("california gurlz");
		playlist.addToPlaylist("hype boy");
		playlist.addToPlaylist("pink in the night");
		playlist.shuffleVideoTitles(null);
		assertFalse(playlist.getPlaylistVideosTitles().get(0).equals("gangnam style")
				&& playlist.getPlaylistVideosTitles().get(1).equals("toxic")
				&& playlist.getPlaylistVideosTitles().get(2).equals("california gurlz")
				&& playlist.getPlaylistVideosTitles().get(3).equals("hype boy")
				&& playlist.getPlaylistVideosTitles().get(4).equals("pink in the night"));
	}

	// tests manager constructors and getters
	@Test
	public void managerConstructorGetter() {
		TubeVideosManager drive = new TubeVideosManager();
		drive.addPlaylist("valentine's day");
		drive.addPlaylist("valentine's day");
		drive.addPlaylist("repeat");
		drive.addVideoToDB("gangnam style", "www.www", 6, Genre.Documentary);
		drive.addVideoToDB("california gurlz", "xyz.xyz", 8, Genre.Educational);
		drive.addVideoToPlaylist("california gurlz", "repeat");
		assertTrue(drive.getAllVideosInDB().get(1).getTitle().equals("california gurlz"));
		assertTrue(drive.getPlaylistsNames()[1].equals("repeat"));
		assertTrue(drive.getPlaylist("valentine's day").getName().equals("valentine's day"));
		assertTrue(drive.getPlaylist("repeat").getPlaylistVideosTitles().get(0).equals("california gurlz"));
	}

	// tests manager adding comments to video from manager
	@Test
	public void managerComments() {
		youtube.addPlaylist("playlist");
		youtube.addVideoToDB("gangnam style", "www.www", 6, Genre.Documentary);
		playlist.addToPlaylist("gangnam style");
		youtube.addComments("gangnam style", "slay");
		youtube.addComments("gangnam style", "love omg");
		youtube.addComments("gangnam style", "it's givinggggg");
		assertTrue(youtube.findVideo("gangnam style").getComments().get(0).equals("slay"));
		assertTrue(youtube.findVideo("gangnam style").getComments().get(1).equals("love omg"));
		assertTrue(youtube.findVideo("gangnam style").getComments().get(2).equals("it's givinggggg"));
	}

	// tests search for video
	@Test
	public void managerSearch() {
		TubeVideosManager drive = new TubeVideosManager();
		drive.addVideoToDB("gangnam style", "www.www", 6, Genre.Documentary);
		drive.addVideoToDB("california gurlz", "xyz.xyz", 8, Genre.Educational);
		drive.addVideoToDB("toxic", "xxx.xxx", 5, Genre.FilmAnimation);
		Playlist list = drive.searchForVideos("faves", "toxic", 6, Genre.FilmAnimation);
		assertTrue(list.getPlaylistVideosTitles().get(0).equals("toxic"));
	}
}
