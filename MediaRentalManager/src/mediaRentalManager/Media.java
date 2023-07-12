package mediaRentalManager;

public interface Media extends Comparable<Media>{
	//only contains common methods between movie and album
	
	public String getTitle();
	
	public int getCopies();
	
	public void removeCopy();
	
	public void addCopy();
}
