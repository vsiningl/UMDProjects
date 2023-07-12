package mediaRentalManager;

public class Album implements Media{
	
	private String title = "";
	private String artist = "";
	private String songs = "";
	private int available = 0;
	
	public Album(String title, int available, String artist, String songs){
		this.title = title;
		this.artist = artist;
		this.songs = songs;
		this.available = available;
	}
	
	public String toString() {
		String print = "Title: " + title + ", Copies Available: " + available + ", Artist: " + artist + ", Songs: " + songs;
		
		return print;
	}

	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getSongs() {
		return songs;
	}
	
	public int getCopies() {
		return available;
	}
	
	public void removeCopy() {
		available --;
	}
	
	public void addCopy() {
		available ++;
	}
	
	//two compareTos for class and interface
	public int compareTo(Album album) {
		int comp = this.title.compareTo(album.title);
		return comp;
	}

	@Override
	public int compareTo(Media media) {
		int comp = this.title.compareTo(media.getTitle());
		return comp;
	}

}
