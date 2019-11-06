package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;


public class GraphAdjList<T extends Comparable<T>> implements Graph<T> {
	
	private Hashtable<T, Hashtable<T, Edge<T>>> graph;
	
	public GraphAdjList(){
		graph = new Hashtable<T, Hashtable<T, Edge<T>>>();
	}

	@Override
	public boolean adjacent(T source, T dest) {
		return graph.get(source).containsKey(dest)?true:false;
	}

	@Override
	public List<T> neighbors(T source) {
		List<T> l = new ArrayList<T>();
		for(Edge<T> edge : graph.get(source).values()) {
			l.add(edge.getVertex());
		}
		return l;
	}

	@Override
	public void addVertex(T x) {
		graph.put(x, new Hashtable<T, Edge<T>>());
	}

	@Override
	public void removeVertex(T x) {
		graph.remove(x);
	}

	@Override
	public void addEdge(T source, T dest, int weight, String id, boolean bidirectional) {
		// TODO Auto-generated method stub
		if(graph.containsKey(source) && graph.containsKey(dest)) {
			graph.get(source).put(dest, new Edge<T>(dest, weight, id));
			if(bidirectional)
				graph.get(dest).put(source, new Edge<T>(source, weight, id));
		} else {
			throw new IllegalArgumentException("Source or destination vertices may haven't been added to the graph.");
		}
	}

	@Override
	public void removeEdge(T source, T dest, boolean bidirectional ) {
		// TODO Auto-generated method stub
		graph.get(source).remove(dest);
		if(bidirectional) 
			graph.get(dest).remove(source);
	}
	
	public void printGraph() {
		for (T vertex : graph.keySet()) {
			System.out.println(vertex + " -> " + graph.get(vertex).values());
		}
	}
	
	public Object[] dijkstra(T source, T dest){
		Hashtable<T, Integer> distances = new Hashtable<>();
		HashMap<T, T> pred = new HashMap<>();
		distances.put(source, 0);
		PriorityQueue<Pair<T, Integer>> q = new PriorityQueue<>();
		for(T vertex : graph.keySet()) {
			if(!vertex.equals(source)) {
				distances.put(vertex, Integer.MAX_VALUE);
			}
			pred.put(vertex, null);
			q.offer(new Pair<T,Integer>(vertex,distances.get(vertex),Pair.COMPARE_BY_SECOND));
		}
		T u = null;
		while(!q.isEmpty()) {
			u = q.poll().getP1();
			for(T neighbor : neighbors(u)) {
				int alt = distances.get(u) + lengthUV(u, neighbor);
				if(alt < distances.get(neighbor)) {
					distances.put(neighbor,alt);
					pred.put(neighbor, u);
					q.remove(neighbor);
					q.offer(new Pair<T,Integer>(neighbor,alt,Pair.COMPARE_BY_SECOND));
				}
			}
		}
		Object[] maps = new Object[2];
		maps[0] = pred;
		maps[1] = distances;
		return maps;
	}
	
	public int lengthUV(T u, T v) {
		if(graph.containsKey(u) && graph.get(u).containsKey(v)) {
			return graph.get(u).get(v).getWeight();
		}
		return Integer.MAX_VALUE;
	}

	public Hashtable<T, Hashtable<T, Edge<T>>> getGraph() {
		return graph;
	}

	public void setGraph(Hashtable<T, Hashtable<T, Edge<T>>> graph) {
		this.graph = graph;
	}
	
}


