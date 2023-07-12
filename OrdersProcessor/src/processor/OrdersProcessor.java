package processor;

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
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class OrdersProcessor{
	boolean multiThread;
	String filename;
	String orderFileBase;
	String resultFile;
	int numOrders;
	HashMap<String, Double> itemData; 
	HashMap<String, Integer> allOrders;
	HashMap<Integer, String> fileWrite;
	Object lock;

	public OrdersProcessor() {
		this.multiThread = false;
		this.filename = "";
		this.numOrders = 0;
		this.orderFileBase = "";
		this.resultFile = "";
		this.itemData = new HashMap<String, Double>();
		this.allOrders = new HashMap<String, Integer>();
		this.fileWrite = new HashMap<Integer, String>();
		this.lock = new Object();
	}
	
	public HashMap<String, Integer> getAllOrders(){
		return allOrders;
	}
	
	public HashMap<String, Double> getItemData(){
		return itemData;
	}
	
	public HashMap<Integer, String> getFileWrite() {
		return fileWrite;
	}
	
	public synchronized void addItemData() {
		try {
			Scanner scanner = new Scanner(new File(filename));
			while(scanner.hasNext()) {
				itemData.put(scanner.next(), scanner.nextDouble());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void fileWriter(String content, String filename) {
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
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		OrdersProcessor op = new OrdersProcessor();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter item's data file name: ");
		op.filename = scanner.nextLine();
		op.addItemData();
		System.out.print("Enter 'y' for multiple threads, any other character otherwise: ");
		if(scanner.nextLine().equals("y")) {
			op.multiThread = true;
		}else {
			op.multiThread = false;
		}
		System.out.print("Enter number of orders to process: ");
		op.numOrders = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter order's base filename: ");
		op.orderFileBase = scanner.nextLine();
		System.out.print("Enter result's filename: ");
		op.resultFile = scanner.nextLine();
		scanner.close();
		try {
			FileWriter clear = new FileWriter(op.resultFile, false);
			clear.close();
		} catch (IOException e) {
		}
		if(op.multiThread) {
			ArrayList<Thread> threads = new ArrayList<Thread>();
			for(int i = 1; i <= op.numOrders; i++) {
				StringBuilder builder = new StringBuilder();
				builder.append(op.orderFileBase);
				builder.append(i);
				builder.append(".txt");
				Thread ot= new Thread(new OrderThread(builder.toString(), op, op.lock));
				threads.add(new Thread(new OrderThread(builder.toString(), op, op.lock)));
			}
			for(int i = 0; i < threads.size(); i++) {
				threads.get(i).start();
				
			}
			for(int i = 0; i < threads.size(); i++) {
				try {
					threads.get(i).join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			for(int i = 1; i <= op.numOrders; i++) {
				StringBuilder builder = new StringBuilder();
				builder.append(op.orderFileBase);
				builder.append(i);
				builder.append(".txt");
				OrderThread ot= new OrderThread(builder.toString(), op, op.lock);
				ot.run();
			}
		}
		String content = "";
		synchronized(op.lock) {
		Set<Integer> ids = op.getFileWrite().keySet();
		TreeSet<Integer> sortedIds = new TreeSet<Integer>(ids);
		Iterator<Integer> iter = sortedIds.iterator();
		while(iter.hasNext()) {
			content += op.getFileWrite().get(iter.next());
		}
		}
		content += "***** Summary of all orders *****";
		Set<String> items = op.allOrders.keySet();		
		Set<String> sortedOrders = new TreeSet<String>(items);
		Iterator<String> iterator = sortedOrders.iterator();
		double total = 0;
		while(iterator.hasNext()) {
			String item = iterator.next();
			content += "\nSummary - Item's name: " + item;
			content += ", Cost per item: " + NumberFormat.getCurrencyInstance().format(op.getItemData().get(item));
			content += ", Number sold: " + op.getAllOrders().get(item);
			content += ", Item's Total: " + NumberFormat.getCurrencyInstance().format(op.getItemData().get(item)*op.getAllOrders().get(item));
			total += op.itemData.get(item)*op.getAllOrders().get(item);
		}
		content += "\nSummary Grand Total: " + NumberFormat.getCurrencyInstance().format(total) + "\n";
		op.fileWriter(content, op.resultFile);
		long endTime = System.currentTimeMillis();
		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out.println("Results can be found in the file: " + op.resultFile);
	}
}