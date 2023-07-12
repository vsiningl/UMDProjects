package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class OrderThread implements Runnable{
	Scanner scanner;
	String filename;
	String content;
	OrdersProcessor processor;
	Map<String, Integer> orders;
	double total;
	Object lock;
	
	public OrderThread(String filename, OrdersProcessor processor, Object lock) {
		try {
			this.scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.filename = filename;
		this.content = "";
		this.processor = processor;
		this.orders = new HashMap<String, Integer>();
		this.total = 0;
		this.lock = lock;
		
	}
	
	public int getId(String file) {
		int searchFrom = file.indexOf(" ");
		String str = file.substring(searchFrom + 1, searchFrom + 5);
		return Integer.parseInt(str);
	}
	
	public int getSequence(int id) {
		return id % 100;
	}
	
	public String readData() {
		String content = "";
		content += scanner.nextLine();
		while(scanner.hasNext()) {
			content += "\n" + scanner.next();
			scanner.nextLine();
		}
		return content;
	}
	

	public void addItemThread(String file) {
		String[] items = file.split("\n");
		for(int i = 1; i < items.length; i++) {
			if(!orders.containsKey(items[i])) {
				Integer quantity = getQuantity(items[i], file);
				orders.put(items[i], quantity);
			}
		}
	}
	
	public void addItemAll(String file) {
		String[] splitFile = file.split("\n");
		Set<String> set = new LinkedHashSet<String>();
		for(int i = 0; i < splitFile.length; i++) {
			set.add(splitFile[i]);
		}
		Object[] items = set.toArray();
		for(int i = 1; i < items.length; i++) {
			if(!processor.getAllOrders().containsKey(items[i])) {
				Integer quantity = getQuantity(items[i].toString(), file);
				processor.getAllOrders().put(items[i].toString(), quantity);
			}else if(processor.getAllOrders().containsKey(items[i])){
				Integer newVal= processor.getAllOrders().get(items[i]) + getQuantity(items[i].toString(), file);
				processor.getAllOrders().put(items[i].toString(), newVal);
			}
		}
	}
	
	public int getQuantity(String item, String file) {
		int counter = 0;
		String[] items = file.split("\n");
		for(int i = 1; i < items.length; i++) {
			if(items[i].equals(item)) {
				counter++;
			}
		}
		return counter;
	}
	
	public double getCost(String item) {
		Double cost = processor.getItemData().get(item);
		return cost;
	}
	
	public void fileWrite(String content, String filename) {
		try {
			BufferedWriter bfw = new BufferedWriter(new FileWriter(filename, true));
			bfw.write(content);
			bfw.flush();
			bfw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		String file = this.readData();
		synchronized(lock) {
			addItemThread(file);
			addItemAll(file);
		}
		System.out.println("Running order for client with id: " + getId(file));
		content += "----- Order details for client with Id: " + getId(file) + " -----";
		Set<String> items = orders.keySet();
		Set<String> sortedItems = new TreeSet<String>(items);
		Iterator<String> iter = sortedItems.iterator();
		while(iter.hasNext()) {
			String item = iter.next();
			content += "\nItem's name: " + item;
			content += ", Cost per item: " + NumberFormat.getCurrencyInstance().format(getCost(item));
			content += ", Quantity: " + getQuantity(item, file);
			content += ", Cost: " + NumberFormat.getCurrencyInstance().format(getCost(item)*getQuantity(item, file));
			total += getCost(item)*getQuantity(item, file);
		}
		content += "\nOrder Total: " + NumberFormat.getCurrencyInstance().format(total) + "\n";
		synchronized(lock) {
		processor.fileWrite.put(getId(file), content);
		}
	}
}
