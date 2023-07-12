package implementation;

import java.util.*;
import java.util.Map.Entry;

/**
 * Implements a graph. We use two maps: one map for adjacency properties 
 * (adjancencyMap) and one map (dataMap) to keep track of the data associated 
 * with a vertex. 
 * 
 * @author cmsc132
 * 
 * @param <E>
 */
public class Graph<E> {
	/* You must use the following maps in your implementation */
	private HashMap<String, HashMap<String, Integer>> adjacencyMap;
	private HashMap<String, E> dataMap;

	public Graph() {
		this.adjacencyMap = new HashMap<String, HashMap<String, Integer>>();
		this.dataMap = new HashMap<String, E>();
	}
	
	public void addVertex(String vertexName, E data) {
		if(adjacencyMap.containsKey(vertexName)) {
			throw new IllegalArgumentException("already in there bestie");
		}
		adjacencyMap.put(vertexName, new HashMap<String, Integer>());
		dataMap.put(vertexName, data);
	}
	
	public void addDirectedEdge(String startVertex, String endVertex, int cost) {
		if(!adjacencyMap.containsKey(endVertex) || !adjacencyMap.containsKey(startVertex)) {
			throw new IllegalArgumentException("sucks");
		}
		adjacencyMap.get(startVertex).put(endVertex, cost);
	}
	
	public String toString() {
		TreeMap<String, HashMap<String, Integer>> sortedGraph = new TreeMap<String, HashMap<String, Integer>>(adjacencyMap);
		String str = "";
		
		str += "Vertices: " + sortedGraph.keySet() + "\nEdges:\n";
		
		for(Map.Entry<String, HashMap<String, Integer>> entry : sortedGraph.entrySet()) {
			str += "Vertex(" + entry.getKey() + ")--->" + entry.getValue() + "\n";
		}
		
		return str;
	}
	
	public Map<String, Integer> getAdjacentVertices(String vertexName){
		if(!adjacencyMap.containsKey(vertexName)) {
			return new HashMap<String, Integer>();
		}
		return adjacencyMap.get(vertexName);
	}

	public int getCost(String startVertex, String endVertex) {
		if(!adjacencyMap.containsKey(endVertex) || !adjacencyMap.containsKey(startVertex)) {
			throw new IllegalArgumentException("sucks");
		}
		return adjacencyMap.get(startVertex).get(endVertex);
	}
	
	public Set<String> getVertices(){
		return dataMap.keySet();
	}
	
	public E getData(String vertex) {
		if(!dataMap.containsKey(vertex)) {
			throw new IllegalArgumentException("not here bestie");
		}
		return dataMap.get(vertex);
	}
	
	public void doDepthFirstSearch(String startVertex, CallBack<E> callback) {
		if(!dataMap.containsKey(startVertex) || !adjacencyMap.containsKey(startVertex)) {
			throw new IllegalArgumentException("can't search, so sorry, try again... loser!");
		}
		Stack<String> stack= new Stack<>();
		LinkedList<String> visited = new LinkedList<>();
		
		stack.push(startVertex);
		
		
		
		while(stack.size() != 0) {
			String s = stack.pop();
			if(!visited.contains(s)) {
				callback.processVertex(s, dataMap.get(s));
				visited.add(s);
			}
			TreeMap<String, Integer> sortedAdj = new TreeMap<String, Integer>(getAdjacentVertices(s));
			Object [] adj = (Object[]) sortedAdj.keySet().toArray();
			
			for(int i = 0; i < adj.length; i++) {
				if(!visited.contains(adj[i])) {
					stack.push((String) adj[i]);
				}
			}
		}
	}
	
	public void doBreadthFirstSearch(String startVertex, CallBack<E> callback) {
		if(!dataMap.containsKey(startVertex) || !adjacencyMap.containsKey(startVertex)) {
			throw new IllegalArgumentException("can't search, so sorry, try again... loser!");
		}
		Queue<String> que = new LinkedList<String>();
		LinkedList<String> visited = new LinkedList<String>();
		
		que.add(startVertex);
		
		while(que.size() != 0) {
			TreeMap<String, Integer> sortedAdj = new TreeMap<String, Integer>(getAdjacentVertices(que.peek()));
			Object [] adj = (Object[]) sortedAdj.keySet().toArray();
			for(int i = 0; i < adj.length; i++) {
				if(!visited.contains(adj[i]) && !que.contains(adj[i])) {
					que.add((String) adj[i]);
				}
			}
			if(dataMap.containsKey(que.peek())) {
				callback.processVertex(que.peek(), dataMap.get(que.peek()));
				visited.add(que.poll());
			}
		}
	}
	
	public int doDijkstras(String startVertex, String endVertex, ArrayList<String> shortestPath) {
		Map<String, Integer> costMap = new HashMap<String, Integer>();
		Map<String, String> lastVert = new HashMap<String, String>();
		//google this again, confused
		PriorityQueue<String> que = new PriorityQueue<String>(Comparator.comparingInt(costMap::get));
		
		for(String vertex: adjacencyMap.keySet()) {
			costMap.put(vertex, Integer.MAX_VALUE);
			lastVert.put(vertex, null);
		}
		costMap.put(startVertex, 0);
		que.offer(startVertex);
		
		while(!que.isEmpty()) {
			int minCost = Integer.MAX_VALUE;
			String key = que.poll();
			if(key.equals(endVertex)){
				String curr = endVertex;
				while(curr != null){
					shortestPath.add(curr);
					curr = lastVert.get(curr);
				}
				Collections.reverse(shortestPath);
				return costMap.get(key);
			}

			for(Entry<String, Integer> adj : getAdjacentVertices(key).entrySet()) {
				minCost = costMap.get(key) + adj.getValue();
				if(minCost < costMap.get(adj.getKey())){
					costMap.put(adj.getKey(), minCost);
					lastVert.put(adj.getKey(), key);
					que.remove(adj.getKey());
					que.offer(adj.getKey());
				}
			}
		}
		shortestPath.add("None");
		return -1;
	}
}