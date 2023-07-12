package mediaRentalManager;

import java.util.ArrayList;

public class Customer implements Comparable<Customer>{
	
	private String name = "";
	private String address = "";
	private String plan = "";
	public ArrayList<String> rented;
	public ArrayList<String> queued;

	
	public Customer(String name, String address, String plan) {
		this.name = name;
		this.address = address;
		this.plan = plan;
		
		this.rented = new ArrayList<String>();
		this.queued = new ArrayList<String>();
	}
	
	public String toString() {
		String print = "Name: " + name + ", Address: " + address + ", Plan: " + plan;
		
		return print;
	}
	
	public String getName() {
		return name;
	}
	
	public String rentedToString() {
		String rent = "";
		
		for(int i = 0; i < rented.size(); i++) {
			rent += rented.get(i);
		}
				
		return rent;
	}
	
	public int compareTo(Customer customer) {
		int comp = this.name.compareTo(customer.name);
		return comp;
	}
	
	public String getPlan() {
		return plan;
	}

}
