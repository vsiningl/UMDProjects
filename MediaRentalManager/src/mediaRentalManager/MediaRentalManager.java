package mediaRentalManager;

import java.util.ArrayList;
import java.util.Collections;

public class MediaRentalManager implements MediaRentalManagerInt{
	
	private ArrayList <Customer> customer = new ArrayList<Customer>();
	private ArrayList <Media> media = new ArrayList<Media>();
	private int limit = 2;

	@Override
	public void addCustomer(String name, String address, String plan) {
		Customer newbie = new Customer(name, address, plan);
		customer.add(newbie);
		
		Collections.sort(customer);
	}

	@Override
	public void addMovie(String title, int copiesAvailable, String rating) {
		Movie film = new Movie(title, copiesAvailable, rating);
		media.add(film);
		
		//uses compareTo to sort
		Collections.sort(media);
	}

	@Override
	public void addAlbum(String title, int copiesAvailable, String artist, String songs) {
		Album record = new Album(title, copiesAvailable,artist,songs);
		media.add(record);
		
		Collections.sort(media);
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		this.limit = value;
		
	}

	@Override
	public String getAllCustomersInfo() {
		String list = "***** Customers' Information *****\n";
		
		for (int i = 0; i < customer.size(); i++) {
			Customer c = customer.get(i);
			list += customer.get(i) + "\nRented: " + c.rented + "\nQueue: " + c.queued + "\n";
		}
		return list;
	}

	@Override
	public String getAllMediaInfo() {
		String list = "***** Media Information *****";
		
		for(int i = 0; i < media.size(); i++) {
			Media m = media.get(i);
			list += "\n" + m.toString();
		}
		return list;
	}

	@Override
	public boolean addToQueue(String customerName, String mediaTitle) {
		
		for(int i = 0; i < customer.size(); i++) {
			Customer c = customer.get(i);
			if(customerName.equals(c.getName())) {
				for(int h = 0; h < c.queued.size(); h++) {
					//queued is a public string arraylist, so no getter
					if(c.queued.get(h).equals(mediaTitle)) {
						return false;
					}
				}
				c.queued.add(mediaTitle);
			}
		}
		return true;
	}

	@Override
	public boolean removeFromQueue(String customerName, String mediaTitle) {
		for(int i = 0; i < customer.size(); i++) {
			Customer c = customer.get(i);
			if(customerName.equals(c.getName())) {
				for(int h = 0; h < c.queued.size(); h++) {
					if(c.queued.get(h).equals(mediaTitle)) {
						customer.get(h).queued.remove(mediaTitle);
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public String processRequests() {
		String msg = "";
		
		//traverses customer arraylist
		for(int i = 0; i < customer.size(); i++) {
			Customer c = customer.get(i);
			if(c.getPlan().equals("UNLIMITED")) {
				//traverses queued arraylist for customer
				for(int h = 0; h < c.queued.size(); h++) {
					String q = c.queued.get(h);
					//traverses media arraylist for a match that has copies availale
					for(int r = 0; r < media.size(); r++) {
						Media m = media.get(r);
						if(q.equals(m.getTitle()) && m.getCopies()>0) {
							//adds to rented, removes from queued, removes a copy from media
							c.rented.add(q);
							c.queued.remove(h);
							h--;
							m.removeCopy();
							msg += "Sending " + m.getTitle() + " to " + c.getName() + "\n";
						}
					}
				}
			}else if(c.getPlan().equals("LIMITED")){
				for(int h = 0; h < c.queued.size(); h++) {
					String q = c.queued.get(h);
					for(int r = 0; r < media.size(); r++) {
						Media m = media.get(r);
						//only works if the size of rented arraylist is less than the limit
						if(q.equals(m.getTitle()) && m.getCopies()>0 && c.rented.size() < limit) {
							c.rented.add(q);
							c.queued.remove(h);
							h--;
							m.removeCopy();
							msg += "Sending " + m.getTitle() + " to " + c.getName() + "\n";
						}
					}
				}
			}
		}
		return msg;
	}

	@Override
	public boolean returnMedia(String customerName, String mediaTitle) {
		//traverses customer arraylist
		for(int i = 0; i < customer.size(); i++) {
			Customer c = customer.get(i);
			//finds matching customer with matching name
			if(c.getName().equals(customerName)) {
				//traverses rented arraylist
				for(int h = 0; h < c.rented.size(); h++) {
					String r = c.rented.get(h);
					//finds matching title
					if(r.equals(mediaTitle)) {
						//removes it from rented arraylist
						c.rented.remove(mediaTitle);
						//finds media in media arraylist
						for(int n = 0; n < media.size(); n++) {
							Media m = media.get(n);
							if(m.getTitle().equals(mediaTitle)) {
								//adds a copy
								m.addCopy();
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> fetch = new ArrayList<String>();
		//if everything is null, returns entire list
		if(title == null && rating == null && artist == null && songs == null) {
			for(int i = 0; i < media.size(); i++) {
				fetch.add(media.get(i).getTitle());
			}
			return fetch;
		}
		//traverses media arraylist
		for(int i = 0; i < media.size(); i++) {
			Media g = media.get(i);
			//if title isn't null, searches based on title
			if(title != null && g.getTitle().equals(title)) {
				fetch.add(g.getTitle());
				//if title is null, checks for class of media to search based on different stored data
			}else if(g instanceof Movie == true) {
				//makes media to downcast to movie to use getRating
				Media m = g;
				Movie m2 = (Movie) m;
				//if ratings match, title of movie is added to returning arraylist
				if(rating != null && m2.getRating().equals(rating)) {
					fetch.add(g.getTitle());
				}
			}else if(g instanceof Album == true) {
				//makes media to downcast to album to use getSongs and getArtist
				Media m = g;
				Album m2 = (Album) m;
				//matches based on artist first, adds to arraylist if they match
				if(artist != null && m2.getArtist().equals(artist)) {
					fetch.add(g.getTitle());
					//matches based on songs after, adds to arraylist if they match
				} else if(songs!= null && m2.getSongs().contains(songs)){
					fetch.add(g.getTitle());
				}
				//if nothing matches, nothing is added
			}
		}
		return fetch;
	}

}
