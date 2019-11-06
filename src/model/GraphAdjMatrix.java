package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.PriorityQueue;

public class GraphAdjMatrix<T extends Comparable<T>> implements Graph<T>{
	
	private int[][] graph;
	private Hashtable<T, Integer> values;
	private Hashtable<Integer, T> indexes;
	private Hashtable<String, Edge<T>> edges;
	private int v;
	
	public GraphAdjMatrix(int size) {
		this.v = 0;
		this.values = new Hashtable<T, Integer>(size);
		this.graph = new int[size][size];
		this.indexes = new Hashtable<Integer, T>(size);
		this.edges = new Hashtable<>();
	}

	@Override
	public boolean adjacent(T source, T dest) {
		// TODO Auto-generated method stub
		if(values.contains(source) && values.contains(dest)) {
			return graph[values.get(source)][values.get(dest)] > 0 ? true:false;
		}
		return false;
	}

	@Override
	public List<T> neighbors(T source) {
		// TODO Auto-generated method stub
		int i = values.get(source);
		List<T> l = new ArrayList<T>();
		for (int j = 0; j < graph[i].length; j++) {
			if(graph[i][j] > 0) {
				l.add(indexes.get(j));
			}
		}
		return l;
	}
	
	@Override
	public void addEdge(T source, T dest, int weight, String id, boolean bidirectional) {
		if(values.containsKey(source) && values.containsKey(dest)) {
			int i = values.get(source) , j = values.get(dest);
			graph[i][j]++;
			
			edges.put(i+" "+j, new Edge<T>(weight,id));
			 if(bidirectional) {
				graph[j][i]++;
				edges.put(j+" "+i, new Edge<T>(weight,id));
			}
		} else {
			throw new IllegalArgumentException("Source or destination vertices may haven't been added to the graph.");
		}
		
	}

	@Override
	public void addVertex(T x) {
		// TODO Auto-generated method stub
		if(v == graph.length) {
			int[][] newGraph = new int[v+1][v+1];
			for (int i = 0; i < graph.length; i++) {
				for (int j = 0; j < graph.length; j++) {
					newGraph[i][j] = graph[i][j];
				}
			}
			graph = newGraph;
		}
		values.put(x, v);
		indexes.put(v, x);
		v++;
	}

	@Override
	public void removeVertex(T x) {
		if (values.containsKey(x) && v > 0) {
			int index = values.get(x);
			values.remove(indexes.remove(index));
			for(int i = index+1; i < v; i++) {
				T vertex = indexes.remove(i);
				if(vertex != null) {
					values.remove(vertex);
					values.put(vertex, i-1);
					indexes.put(i-1, vertex);
				}
			}
			v--;
			for (int i = index; i < v; i++)
				for (int j = 0; j <= v; j++)
					graph[i][j] = graph[i+1][j];
			for (int i = index; i < v; i++)
				for (int j = 0; j < v; j++)
					graph[j][i] = graph[j][i+1];
			for (int i = 0; i <= v; i++) {
				graph[i][v] = 0;
				graph[v][i] = 0;
			}
		}
	}
	
	public void printGraph() {
		System.out.println(v);
		for (int i = 0; i < graph.length; i++) {
			for (int j = 0; j < graph[0].length; j++) {
				System.out.print(graph[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public void removeEdge(T source, T dest, boolean bidirectional) {
		// TODO Auto-generated method stub
		if(values.containsKey(source) && values.containsKey(dest)) {
			int i = values.get(source) , j = values.get(dest);
			edges.remove(i+" "+j);
			graph[i][j]--;
			if(bidirectional) {
				graph[j][i]--;
				edges.remove(j+" "+i);
			}
		}
		
	}
	
	public HashMap<T,T> dijkstra(T source, T dest) {
		Hashtable<T, Integer> distances = new Hashtable<>();
		HashMap<T, T> pred = new HashMap<>();
		distances.put(source, 0);
		PriorityQueue<Pair<T, Integer>> q = new PriorityQueue<>();
		for(T vertex : values.keySet()) {
			if(!vertex.equals(source)) {
				distances.put(vertex, Integer.MAX_VALUE);
			}
			pred.put(vertex, null);
			char first = Pair.COMPARE_BY_FIRST;
			q.offer(new Pair<T,Integer>(vertex,distances.get(vertex),first));
		}
		T u = null;
		while(!q.isEmpty()) {
			u = q.poll().getP1();
			for(T neighbor : neighbors(u)) {
				int alt = distances.get(u) + lengthUV(u, neighbor);
				if(alt < distances.get(neighbor)) {
					distances.put(neighbor,alt);
					pred.put(neighbor, u);
				}
			}
		}
		return pred;
	}
	
	public int lengthUV(T u, T v) {
		int i = values.get(u);
		int j = values.get(v);
		if(graph[i][j] > 0) {
			return edges.get(i+" "+j).
					getWeight();
		}
		return Integer.MAX_VALUE;
	}
	
	public int[][] getGraph() {
		return graph;
	}


	public void setGraph(int[][] graph) {
		this.graph = graph;
	}


	public Hashtable<T, Integer> getValues() {
		return values;
	}


	public void setValues(Hashtable<T, Integer> values) {
		this.values = values;
	}


	public Hashtable<Integer, T> getIndexes() {
		return indexes;
	}


	public void setIndexes(Hashtable<Integer, T> indexes) {
		this.indexes = indexes;
	}


	public int getV() {
		return v;
	}


	public void setV(int v) {
		this.v = v;
	}
	
}
