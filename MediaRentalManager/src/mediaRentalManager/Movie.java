package mediaRentalManager;

public class Movie implements Media{
	
	private String title = "";
	private String rating = "";
	private int available = 0;
	
	public Movie(String title, int available, String rating) {
		this.title = title;
		this.rating = rating;
		this.available = available;
	}

	public String toString() {
		String print = "Title: " + title + ", Copies Available: " + available + ", Rating: " + rating;
		
		return print;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getRating() {
		return rating;
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

	//two compareTos for movie and media
	public int compareTo(Movie movie) {
		int comp = this.title.compareTo(movie.title);
		return comp;
	}
	
	@Override
	public int compareTo(Media media) {
		int comp = this.title.compareTo(media.getTitle());
		return comp;
	}

}
