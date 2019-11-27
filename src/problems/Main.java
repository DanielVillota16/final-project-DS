package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	
	public static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//miceAndMazeAdjListSolution();
		//miceAndMazeAdjMatrixSolution();
		//darkRoadsAdjListSolution();
		//darkRoadsAdjMatrixSolution();
	}
	
	public static void darkRoadsAdjListSolution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("0 0")) {
			int m = Integer.parseInt(line.split(" ")[0]);
			int n = Integer.parseInt(line.split(" ")[1]);
			int sumRoads = 0;
			AdjListGraph<Integer> graph = new AdjListGraph<>(false, true);
			for (int i = 0; i < m; i++) {
				graph.addVertex(i);
			}
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				int x = Integer.parseInt(line.split(" ")[0]);
				int y = Integer.parseInt(line.split(" ")[1]);
				int z = Integer.parseInt(line.split(" ")[2]);
				sumRoads += z;
				graph.addEdge(x, y, z);
			}
			graph.prim(graph.getVertices().get(0));
			int ans = 0;
			for(int i = 0; i < graph.getVertices().size(); ++i) {
				ans += graph.getVertices().get(i).getD();
			}
			System.out.println(sumRoads - ans);
			line = br.readLine();
		}
		br.close();
	}
	
	public static void darkRoadsAdjMatrixSolution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("0 0")) {
			int m = Integer.parseInt(line.split(" ")[0]);
			int n = Integer.parseInt(line.split(" ")[1]);
			int sumRoads = 0;
			AdjListGraph<Integer> graph = new AdjListGraph<>(false, true);
			for (int i = 0; i < m; i++) {
				graph.addVertex(i);
			}
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				int x = Integer.parseInt(line.split(" ")[0]);
				int y = Integer.parseInt(line.split(" ")[1]);
				int z = Integer.parseInt(line.split(" ")[2]);
				sumRoads += z;
				graph.addEdge(x, y, z);
			}
			graph.prim(graph.getVertices().get(0));
			int ans = 0;
			for(int i = 0; i < graph.getVertices().size(); ++i) {
				ans += graph.getVertices().get(i).getD();
			}
			System.out.println(sumRoads - ans);
			line = br.readLine();
		}
		br.close();
	}
	
	public static void miceAndMazeAdjListSolution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		int[] successForCases = new int[cases];
		for (int i = 0; i < cases; i++) {
			br.readLine();
			int success = 0;
			int n = Integer.parseInt(br.readLine()),
				e = Integer.parseInt(br.readLine()),
				t = Integer.parseInt(br.readLine()),
				m = Integer.parseInt(br.readLine());
			AdjListGraph<Integer> maze = new AdjListGraph<>(true, true);
			for (int j = 1; j <= n; j++) {
				maze.addVertex(j);
			}
			for (int j = 0; j < m; j++) {
				String[] mLine = br.readLine().split(" ");
				int a = Integer.parseInt(mLine[0]),
					b = Integer.parseInt(mLine[1]),
					w = Integer.parseInt(mLine[2]);
				maze.addEdge(a, b, w);
			}
			double[][] floyd = maze.floydwarshall();
			for (int j = 0; j < floyd.length; j++) {
				if(floyd[j][e-1] <= t) {
					success++;
				}
			}
			successForCases[i] = success;
		}
		for (int i = 0; i < cases; i++) {
			System.out.println(successForCases[i]);
			if(i < cases-1)	System.out.println();
		}
	}
	
	public static void miceAndMazeAdjMatrixSolution() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		int[] successForCases = new int[cases];
		for (int i = 0; i < cases; i++) {
			br.readLine();
			int success = 0;
			int n = Integer.parseInt(br.readLine()),
				e = Integer.parseInt(br.readLine()),
				t = Integer.parseInt(br.readLine()),
				m = Integer.parseInt(br.readLine());
			AdjMatrixGraph<Integer> maze = new AdjMatrixGraph<>(true, true);
			for (int j = 1; j <= n; j++) {
				maze.addVertex(j);
			}
			for (int j = 0; j < m; j++) {
				String[] mLine = br.readLine().split(" ");
				int a = Integer.parseInt(mLine[0]),
					b = Integer.parseInt(mLine[1]),
					w = Integer.parseInt(mLine[2]);
				maze.addEdge(a, b, w);
			}
			double[][] floyd = maze.floydwarshall();
			for (int j = 0; j < floyd.length; j++) {
				if(floyd[j][e-1] <= t) {
					success++;
				}
			}
			successForCases[i] = success;
		}
		for (int i = 0; i < cases; i++) {
			System.out.println(successForCases[i]);
			if(i < cases-1)	System.out.println();
		}
		br.close();
	}
	
	public static class AdjListGraph<T> {

		private boolean directed;
		private boolean weighted;
		private int numberOfVertices;
		private int numberOfEdges;

		private List<Vertex<T>> vertices;
		private HashMap<T, AdjVertex<T>> map;

		public AdjListGraph(boolean directed, boolean weighted) {
			this.directed = directed;
			this.weighted = weighted;
			numberOfVertices = 0;
			numberOfEdges = getNumberOfEdges();
			vertices = new LinkedList<Vertex<T>>();
			map = new HashMap<>();
		}

		public List<Vertex<T>> getVertices() {
			return vertices;
		}

		public HashMap<T, AdjVertex<T>> getMap() {
			return map;
		}

		public void setMap(HashMap<T, AdjVertex<T>> map) {
			this.map = map;
		}

		public int getNumberOfVertices() {
			return numberOfVertices;
		}

		public int getNumberOfEdges() {
			return numberOfEdges;
		}

		public boolean isDirected() {
			return directed;
		}

		public boolean isWeighted() {
			return weighted;
		}

		
		public void addVertex(T value) {
			if (!isInGraph(value)) {
				AdjVertex<T> vertex = new AdjVertex<T>(value);
				map.put(value, vertex);
				vertex.setIndex(numberOfVertices);
				vertices.add(vertex);
				numberOfVertices++;
			}
		}

		
		public void addEdge(T x, T y) {
			AdjVertex<T> from = searchVertex(x);
			AdjVertex<T> to = searchVertex(y);
			addEdge(from, to);
		}

		public void addEdge(AdjVertex<T> from, AdjVertex<T> to) {
			addEdge(from, to, 1D);
		}

		
		public void addEdge(T x, T y, double w) {
			if (weighted) {
				AdjVertex<T> from = searchVertex(x);
				AdjVertex<T> to = searchVertex(y);
				addEdge(from, to, w);
			}
		}

		public void addEdge(AdjVertex<T> from, AdjVertex<T> to, double w) {
			if (from != null && to != null) {
				Edge<T> edge = new Edge<T>(from, to, w);
				from.getAdjList().add(edge);
				if (!isDirected()) {
					edge = new Edge<T>(to, from, w);
					to.getAdjList().add(edge);
				}
				numberOfEdges++;
			}
		}

		public void removeVertex(Vertex<T> v) {
			for (int i = 0; i < vertices.size(); i++) {
				removeEdge(vertices.get(i), v);
				if (isDirected()) {
					removeEdge(v, vertices.get(i));
				}
			}
			vertices.remove(v);
			map.remove(v.getValue());
			numberOfVertices--;
		}

		public void removeVertex(T v) {
			if (isInGraph(v)) {
				removeVertex(searchVertex(v));
			}
		}

		public void removeEdge(Vertex<T> x, Vertex<T> y) {
			AdjVertex<T> from = (AdjVertex<T>) x;
			AdjVertex<T> to = (AdjVertex<T>) y;
			List<Edge<T>> adjFrom = from.getAdjList();
			Edge<T> e = from.findEdge(to);
			if (e != null)
				adjFrom.remove(e);

			if (!isDirected()) {
				List<Edge<T>> adjTo = to.getAdjList();
				e = to.findEdge(from);
				if (e != null)
					adjTo.remove(e);
			}

			numberOfEdges--;
		}

		public void removeEdge(T x, T y) {
			if (isInGraph(x) && isInGraph(y)) {
				removeEdge(searchVertex(x), searchVertex(y));
			}
		}

		public List<Vertex<T>> getNeighbors(Vertex<T> x) {
			List<Vertex<T>> neigh = new ArrayList<>();
			AdjVertex<T> from = (AdjVertex<T>) x;
			List<Edge<T>> adj = from.getAdjList();
			for (int i = 0; i < adj.size(); i++) {
				neigh.add(adj.get(i).getDestination());
			}
			return neigh;
		}

		public boolean areAdjacent(Vertex<T> x, Vertex<T> y) {
			return getNeighbors(x).contains(y);
		}

		public boolean isInGraph(T value) {
			return searchVertex(value) != null;
		}

		public double getEdgeWeight(Vertex<T> x, Vertex<T> y) {
			double w = 0;
			if (isInGraph(x.getValue()) && isInGraph(y.getValue())) {
				AdjVertex<T> from = (AdjVertex<T>) x;
				AdjVertex<T> to = (AdjVertex<T>) y;
				Edge<T> e = from.findEdge(to);
				if (e != null)
					w = e.getWeight();
			}
			return w;
		}

		public void setEdgeWeight(Vertex<T> x, Vertex<T> y, double w) {
			if (isInGraph(x.getValue()) && isInGraph(y.getValue()) && weighted) {
				AdjVertex<T> from = (AdjVertex<T>) x;
				AdjVertex<T> to = (AdjVertex<T>) y;
				Edge<T> e = from.findEdge(to);
				if (e != null)
					e.setWeight(w);

				if (!isDirected()) {
					e = to.findEdge(from);
					if (e != null)
						e.setWeight(w);
				}
			}
		}

		public AdjVertex<T> searchVertex(T value) {
			return map.get(value);
		}

		public int getIndexOf(Vertex<T> v) {
			int index = -1;
			boolean searching = true;
			for (int i = 0; i < vertices.size() && searching; i++) {
				if (vertices.get(i) == v) {
					index = i;
					searching = false;
				}
			}
			return index;
		}

		public void bfs(Vertex<T> x) {
			AdjVertex<T> s = (AdjVertex<T>) x;
			for (Vertex<T> u : vertices) {
				u.setColor(Vertex.WHITE);
				u.setD(Integer.MAX_VALUE);
				u.setPred(null);
			}
			s.setColor(Vertex.GRAY);
			s.setD(0);
			s.setPred(null);
			Queue<AdjVertex<T>> q = new LinkedList<>();
			q.offer(s);
			while (!q.isEmpty()) {
				AdjVertex<T> u = q.poll();
				for (int i = 0; i < u.getAdjList().size(); i++) {
					AdjVertex<T> v = (AdjVertex<T>) u.getAdjList().get(i).getDestination();
					if (v.getColor() == Vertex.WHITE) {
						v.setColor(Vertex.GRAY);
						v.setD(u.getD() + 1);
						v.setPred(u);
						q.offer(v);
					}
				}
				u.setColor(Vertex.BLACK);
			}
		}

		public void dfs() {
			for (Vertex<T> u : vertices) {
				u.setColor(Vertex.WHITE);
				u.setPred(null);
			}
			int time = 0;
			for (Vertex<T> u : vertices) {
				if (u.getColor() == Vertex.WHITE)
					time = dfsVisit((AdjVertex<T>) u, time);
			}
		}

		private int dfsVisit(AdjVertex<T> u, int time) {
			time++;
			u.setD(time);
			u.setColor(Vertex.GRAY);
			for (int i = 0; i < u.getAdjList().size(); i++) {
				AdjVertex<T> v = (AdjVertex<T>) u.getAdjList().get(i).getDestination();
				if (v.getColor() == Vertex.WHITE) {
					v.setPred(u);
					time = dfsVisit(v, time);
				}
			}
			u.setColor(Vertex.BLACK);
			time++;
			u.setF(time);
			return time;
		}

		private void initSingleSource(AdjVertex<T> s) {
			for (Vertex<T> u : vertices) {
				u.setD(INF);
				u.setPred(null);
			}
			s.setD(0);
		}

		public void dijkstra(Vertex<T> x) {
			AdjVertex<T> s = (AdjVertex<T>) x;
			initSingleSource(s);
			PriorityQueue<AdjVertex<T>> queue = new PriorityQueue<>();
			queue.add(s);
			while (!queue.isEmpty()) {
				AdjVertex<T> u = queue.poll();
				for (Edge<T> e : u.getAdjList()) {
					AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
					double weight = e.getWeight();
					double distanceFromU = u.getD() + weight;
					if (distanceFromU < v.getD()) {
						queue.remove(v);
						v.setD(distanceFromU);
						v.setPred(u);
						queue.add(v);
					}
				}
			}
		}
		
		public void dijkstraSourceDest(Vertex<T> x, Vertex<T> y) {
			AdjVertex<T> s = (AdjVertex<T>) x;
			initSingleSource(s);
			PriorityQueue<AdjVertex<T>> queue = new PriorityQueue<>();
			queue.add(s);
			AdjVertex<T> u = null;
			while (!queue.isEmpty()) {
				u = queue.poll();
				for (Edge<T> e : u.getAdjList()) {
					AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
					double weight = e.getWeight();
					double distanceFromU = u.getD() + weight;
					if (distanceFromU < v.getD()) {
						queue.remove(v);
						v.setD(distanceFromU);
						v.setPred(u);
						queue.add(v);
					}
				}
				if(u.compareTo(y)==0) break;
			}
		}

		public double[][] floydwarshall() {
			double[][] weights = getWeightsMatrix();
			for (int k = 0; k < vertices.size(); k++) {
				for (int i = 0; i < vertices.size(); i++) {
					for (int j = 0; j < vertices.size(); j++) {
						weights[i][j] = Math.min(weights[i][j], weights[i][k] + weights[k][j]);
					}
				}
			}
			return weights;
		}

		private double[][] getWeightsMatrix() {
			double[][] weights = new double[vertices.size()][vertices.size()];
			for (int i = 0; i < weights.length; i++) {
				Arrays.fill(weights[i], INF);
			}
			for (int i = 0; i < vertices.size(); i++) {
				weights[i][i] = 0;
				AdjVertex<T> u = (AdjVertex<T>) vertices.get(i);
				for (Edge<T> e : u.getAdjList()) {
					AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
					double weight = e.getWeight();
					weights[i][getIndexOf(v)] = weight;
				}
			}
			return weights;
		}

		public void prim(Vertex<T> s) {
			AdjVertex<T> r = (AdjVertex<T>) s;
			for (Vertex<T> u : vertices) {
				u.setD(INF);
				u.setColor(Vertex.WHITE);
			}
			r.setD(0);
			r.setPred(null);
			PriorityQueue<AdjVertex<T>> queue = new PriorityQueue<>();
			for (Vertex<T> u : vertices) {
				queue.add((AdjVertex<T>) u);
			}
			while (!queue.isEmpty()) {
				AdjVertex<T> u = queue.poll();
				for (Edge<T> e : u.getAdjList()) {
					AdjVertex<T> v = (AdjVertex<T>) e.getDestination();
					if (v.getColor() == Vertex.WHITE && e.getWeight() < v.getD()) {
						queue.remove(v);
						v.setD(e.getWeight());
						queue.add(v);
						v.setPred(u);
					}
				}
				u.setColor(Vertex.BLACK);
			}
		}

		public ArrayList<Edge<T>> kruskal() {
			ArrayList<Edge<T>> result = new ArrayList<>(); 
			int e = 0;
			int i = 0;
			ArrayList<Edge<T>> edges = getEdges();
			Collections.sort(edges);
			UnionFind uf = new UnionFind(vertices.size());
			i = 0;
			while (e < vertices.size() - 1 && i < edges.size()) {
				Edge<T> edge = edges.get(i);
				i++;
				int x = uf.find(getIndexOf(edge.getSource()));
				int y = uf.find(getIndexOf(edge.getDestination()));
				if (x != y) {
					result.add(edge);
					e++;
					uf.union(x, y);
				}
			}
			return result;
		}

		public ArrayList<Edge<T>> getEdges() {
			ArrayList<Edge<T>> edges = new ArrayList<>();
			for (int i = 0; i < vertices.size(); i++) {
				AdjVertex<T> v = (AdjVertex<T>) vertices.get(i);
				for (int j = 0; j < v.getAdjList().size(); j++) {
					edges.add(v.getAdjList().get(j));
				}
			}
			return edges;
		}
		
		public List<T> getContents(){
			List<Vertex<T>> vertices= getVertices();
			List<T> contents=new ArrayList<>();
			for(int i=0;i<vertices.size();i++) {
				contents.add(vertices.get(i).getValue());
			}
			return contents;
		}
	}
	
	public static class Vertex<T> implements Comparable<Vertex<T>>{

		public static final int WHITE = 0;
		public static final int GRAY = 1;
		public static final int BLACK = 2;
		
		private T value;

		/**
		 * Distance or Initial TimeSTamp for DFS
		 */
		private double d;
		/**
		 * Final TimeStamp for DFS
		 */
		private int f;
		
		private int index;
		
		private int color;
		
		private Vertex<T> pred;
		
		public Vertex(T value) {
			this.value=value;
			pred=null;
			color=WHITE;
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public double getD() {
			return d;
		}

		public void setD(double d) {
			this.d = d;
		}

		public int getF() {
			return f;
		}

		public void setF(int f) {
			this.f = f;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

		public Vertex<T> getPred() {
			return pred;
		}

		public void setPred(Vertex<T> pred) {
			this.pred = pred;
		}
		
		public int getIndex() {
			return this.index;
		}
		
		public void setIndex(int index) {
			this.index=index;
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		public int compareTo(Vertex<T> vertex) {
			int comp = Double.compare(d, vertex.d);
			if(value instanceof Comparable) {
				if(comp == 0) {
					return ((Comparable) value).compareTo(vertex.getValue());
				}
			}
			return comp;
		}
		
		
		public String toString() {
			return value.toString();
		}
		
	}


	public static class AdjVertex<T> extends Vertex<T> {

		private List<Edge <T>> adjList;
		
		public AdjVertex(T value) {
			super(value);
			adjList= new LinkedList<Edge<T>>();
		}
		
		public List<Edge<T>> getAdjList(){
			return adjList;
		}
		
		public boolean isAdjacent(AdjVertex<T> vertex) {
			for(int i = 0; i<adjList.size(); i++) {
				if (adjList.get(i).getDestination()==vertex)
					return true;
			}
			return false;
		}
		
		public Edge<T> findEdge(AdjVertex<T> vertex){
			for (int i=0; i<adjList.size(); i++) {
				if (adjList.get(i).getDestination()==vertex)
					return adjList.get(i);
			}
			return null;
		}
		

	}
	
	public static class Edge<T> implements Comparable<Edge<T>>{

		private double weight;

		private Vertex<T> source;
		private Vertex<T> destination;

		public Edge(Vertex<T> source, Vertex<T> destination) {
			this(source, destination, 1D);
		}

		public Edge(Vertex<T> source, Vertex<T> destination, double weight) {
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		public double getWeight() {
			return weight;
		}

		public void setWeight(double weight) {
			this.weight = weight;
		}

		public Vertex<T> getSource() {
			return source;
		}

		public Vertex<T> getDestination() {
			return destination;
		}

		
		public int compareTo(Edge<T> o) {
			return Double.compare(weight, o.weight);
		}
		
		
		public String toString() {
			return ""+source+" - "+destination+", "+weight;
		}

	}

	public static class UnionFind {

	  private int[] parent;
	  private int[] rank;


	  public int find(int i) {

	    int p = parent[i];
	    if (i == p) {
	      return i;
	    }
	    return parent[i] = find(p);

	  }


	  public void union(int i, int j) {

	    int root1 = find(i);
	    int root2 = find(j);

	    if (root2 == root1) return;

	    if (rank[root1] > rank[root2]) {
	      parent[root2] = root1;
	    } else if (rank[root2] > rank[root1]) {
	      parent[root1] = root2;
	    } else {
	      parent[root2] = root1;
	      rank[root1]++;
	    }
	  }


	  public UnionFind(int max) {

	    parent = new int[max];
	    rank = new int[max];

	    for (int i = 0; i < max; i++) {
	      parent[i] = i;
	    }
	  }

	  
	  public String toString() {
	    return "<UnionFind\np " + Arrays.toString(parent) + "\nr " + Arrays.toString(rank) + "\n>";
	  }
	}
	
	public static class AdjMatrixGraph<T> {

		private boolean directed;
		private boolean weighted;
		private int numberOfVertices;
		private int numberOfEdges;

		private List<Vertex<T>> vertices;
		private List<ArrayList<Integer>> adjMatrix;
		private List<ArrayList<Double>> weightsMatrix;
		private HashMap<T, Vertex<T>> map;

		public AdjMatrixGraph(boolean directed, boolean weighted) {
			this.directed = directed;
			this.weighted = weighted;
			numberOfVertices = 0;
			numberOfEdges = getNumberOfEdges();
			vertices = new LinkedList<Vertex<T>>();
			adjMatrix = new ArrayList<ArrayList<Integer>>();
			weightsMatrix = new ArrayList<ArrayList<Double>>();
			map = new HashMap<>();
		}

		public List<Vertex<T>> getVertices() {
			return vertices;
		}

		public int getNumberOfVertices() {
			return numberOfVertices;
		}

		public int getNumberOfEdges() {
			return numberOfEdges;
		}

		public boolean isDirected() {
			return directed;
		}

		public boolean isWeighted() {
			return weighted;
		}

		public void addVertex(T value) {
			if (!isInGraph(value)) {
				Vertex<T> vertex = new Vertex<T>(value);
				map.put(value, vertex);
				vertices.add(vertex);

				ArrayList<Integer> adj = new ArrayList<>();
				ArrayList<Double> we = new ArrayList<>();
				for (int i = 0; i < adjMatrix.size(); i++) {
					adjMatrix.get(i).add(0);
					weightsMatrix.get(i).add((double) INF);
				}
				for (int i = 0; i < vertices.size(); i++) {
					adj.add(0);
					we.add((double) INF);
				}
				we.set(we.size() - 1, 0.0);
				adjMatrix.add(adj);
				weightsMatrix.add(we);

				numberOfVertices++;
			}
		}

		public void addEdge(T x, T y) {
			Vertex<T> from = searchVertex(x);
			Vertex<T> to = searchVertex(y);
			addEdge(from, to);
		}

		public void addEdge(Vertex<T> from, Vertex<T> to) {
			addEdge(from, to, 1D);
		}

		public void addEdge(T x, T y, double w) {
			if (weighted) {
				Vertex<T> from = searchVertex(x);
				Vertex<T> to = searchVertex(y);
				addEdge(from, to, w);
			}
		}

		public void addEdge(Vertex<T> from, Vertex<T> to, double w) {
			if (from != null && to != null) {

				adjMatrix.get(getIndexOf(from)).set(getIndexOf(to), 1);
				weightsMatrix.get(getIndexOf(from)).set(getIndexOf(to), Math.min(w, weightsMatrix.get(getIndexOf(from)).get(getIndexOf(to))));
				if (!isDirected()) {
					adjMatrix.get(getIndexOf(to)).set(getIndexOf(from), 1);
					weightsMatrix.get(getIndexOf(to)).set(getIndexOf(from), Math.min(w, weightsMatrix.get(getIndexOf(to)).get(getIndexOf(from))));
				}
				numberOfEdges++;
			}

		}

		public void removeVertex(Vertex<T> v) {
			int index = getIndexOf(v);
			for (int i = 0; i < adjMatrix.size(); i++) {
				adjMatrix.get(i).remove(index);
				weightsMatrix.get(i).remove(index);
			}
			adjMatrix.remove(index);
			weightsMatrix.remove(index);

			vertices.remove(v);
			map.remove(v.getValue());
			numberOfVertices--;
		}

		public void removeVertex(T v) {
			if (isInGraph(v)) {
				removeVertex(searchVertex(v));
			}
		}

		public void removeEdge(Vertex<T> from, Vertex<T> to) {
			adjMatrix.get(getIndexOf(from)).set(getIndexOf(to), 0);
			weightsMatrix.get(getIndexOf(from)).set(getIndexOf(to), (double) INF);
			if (!isDirected()) {
				adjMatrix.get(getIndexOf(to)).set(getIndexOf(from), 0);
				weightsMatrix.get(getIndexOf(to)).set(getIndexOf(from), (double) INF);
			}
			numberOfEdges--;
		}

		public void removeEdge(T x, T y) {
			if (isInGraph(x) && isInGraph(y)) {
				removeEdge(searchVertex(x), searchVertex(y));
			}
		}

		public List<Vertex<T>> getNeighbors(Vertex<T> x) {
			List<Vertex<T>> neigh = new ArrayList<>();
			int index = getIndexOf(x);
			for (int i = 0; i < adjMatrix.get(index).size(); i++) {
				if (adjMatrix.get(index).get(i) == 1.0) {
					neigh.add(vertices.get(i));
				}
			}
			return neigh;
		}

		public boolean areAdjacent(Vertex<T> x, Vertex<T> y) {
			return getNeighbors(x).contains(y);
		}

		public boolean isInGraph(T value) {
			return searchVertex(value) != null;
		}

		public double getEdgeWeight(Vertex<T> x, Vertex<T> y) {
			double w = 0;
			int indX = getIndexOf(x);
			int indY = getIndexOf(y);
			if (isInGraph(x.getValue()) && isInGraph(y.getValue())) {
				w = weightsMatrix.get(indX).get(indY);
			}
			return w;
		}

		public void setEdgeWeight(Vertex<T> x, Vertex<T> y, double w) {
			int indX = getIndexOf(x);
			int indY = getIndexOf(y);
			if (isInGraph(x.getValue()) && isInGraph(y.getValue()) && weighted) {
				weightsMatrix.get(indX).set(indY, w);

				if (!isDirected()) {
					weightsMatrix.get(indY).set(indX, w);
				}
			}
		}

		public Vertex<T> searchVertex(T value) {
			return map.get(value);
		}

		public int getIndexOf(Vertex<T> v) {
			int index = -1;
			boolean searching = true;
			for (int i = 0; i < vertices.size() && searching; i++) {
				if (vertices.get(i) == v) {
					index = i;
					searching = false;
				}
			}
			return index;
		}

		public void bfs(Vertex<T> s) {
			for (Vertex<T> u : vertices) {
				u.setColor(Vertex.WHITE);
				u.setD(INF);
				u.setPred(null);
			}
			s.setColor(Vertex.GRAY);
			s.setD(0);
			s.setPred(null);
			Queue<Vertex<T>> q = new LinkedList<>();
			q.offer(s);
			while (!q.isEmpty()) {
				Vertex<T> u = q.poll();
				List<Vertex<T>> neigh = getNeighbors(u);
				for (int i = 0; i < neigh.size(); i++) {
					Vertex<T> v = neigh.get(i);
					if (v.getColor() == Vertex.WHITE) {
						v.setColor(Vertex.GRAY);
						v.setD(u.getD() + 1);
						v.setPred(u);
						q.offer(v);
					}
				}
				u.setColor(Vertex.BLACK);
			}
		}

		public void dfs() {
			for (Vertex<T> u : vertices) {
				u.setColor(Vertex.WHITE);
				u.setPred(null);
			}
			int time = 0;
			for (Vertex<T> u : vertices) {
				if (u.getColor() == Vertex.WHITE)
					time = dfsVisit(u, time);
			}
		}

		private int dfsVisit(Vertex<T> u, int time) {
			time++;
			u.setD(time);
			u.setColor(Vertex.GRAY);
			List<Vertex<T>> neigh = getNeighbors(u);
			for (int i = 0; i < neigh.size(); i++) {
				Vertex<T> v = neigh.get(i);
				if (v.getColor() == Vertex.WHITE) {
					v.setPred(u);
					time = dfsVisit(v, time);
				}
			}
			u.setColor(Vertex.BLACK);
			time++;
			u.setF(time);
			return time;
		}

		private void initSingleSource(Vertex<T> s) {
			for (Vertex<T> u : vertices) {
				u.setD(INF);
				u.setPred(null);
			}
			s.setD(0);
		}

		public void dijkstra(Vertex<T> s) {
			initSingleSource(s);
			PriorityQueue<Vertex<T>> queue = new PriorityQueue<>();
			queue.add(s);
			while (!queue.isEmpty()) {
				Vertex<T> u = queue.poll();
				List<Vertex<T>> neigh = getNeighbors(u);
				for (Vertex<T> v : neigh) {
					double weight = getEdgeWeight(u, v);
					double distanceFromU = u.getD() + weight;
					if (distanceFromU < v.getD()) {
						queue.remove(v);
						v.setD(distanceFromU);
						v.setPred(u);
						queue.add(v);
					}
				}
			}
		}

		public double[][] floydwarshall() {
			double[][] weights = getWeightsMatrix();
			for (int k = 0; k < vertices.size(); k++) {
				for (int i = 0; i < vertices.size(); i++) {
					for (int j = 0; j < vertices.size(); j++) {
						weights[i][j] = Math.min(weights[i][j], weights[i][k] + weights[k][j]);
					}
				}
			}
			return weights;
		}

		public double[][] getWeightsMatrix() {
			double[][] weights = new double[vertices.size()][vertices.size()];
			for (int i = 0; i < weights.length; i++) {
				for (int j = 0; j < weights.length; j++) {
					weights[i][j] = weightsMatrix.get(i).get(j);
				}
			}
			return weights;
		}

		public void prim(Vertex<T> r) {
			for (Vertex<T> u : vertices) {
				u.setD(INF);
				u.setColor(Vertex.WHITE);
			}
			r.setD(0);
			r.setPred(null);
			PriorityQueue<Vertex<T>> queue = new PriorityQueue<>();
			for (Vertex<T> u : vertices) {
				queue.add(u);
			}
			while (!queue.isEmpty()) {
				Vertex<T> u = queue.poll();
				List<Vertex<T>> neigh = getNeighbors(u);
				for (Vertex<T> v : neigh) {
					if (v.getColor() == Vertex.WHITE && getEdgeWeight(u, v) < v.getD()) {
						queue.remove(v);
						v.setD(getEdgeWeight(u, v));
						queue.add(v);
						v.setPred(u);
					}
				}
				u.setColor(Vertex.BLACK);
			}
		}

		public ArrayList<Edge<T>> kruskal() {
			ArrayList<Edge<T>> result = new ArrayList<>();
			int e = 0;
			int i = 0;

			ArrayList<Edge<T>> edges = getEdges();

			Collections.sort(edges);

			UnionFind uf = new UnionFind(vertices.size());

			i = 0; 
			while (e < vertices.size() - 1 && i < edges.size()) {
				Edge<T> edge = edges.get(i);
				i++;

				int x = uf.find(getIndexOf(edge.getSource()));
				int y = uf.find(getIndexOf(edge.getDestination()));

				if (x != y) {
					result.add(edge);
					e++;
					uf.union(x, y);
				}
			}
			return result;
		}

		public ArrayList<Edge<T>> getEdges() {
			ArrayList<Edge<T>> edges = new ArrayList<>();
			for (int i = 0; i < adjMatrix.size(); i++) {
				for (int j = 0; j < adjMatrix.get(i).size(); j++) {
					if(adjMatrix.get(i).get(j) == 1) {
						edges.add(new Edge<>(vertices.get(i), vertices.get(j), weightsMatrix.get(i).get(j)));
					}
				}
			}
			return edges;
		}

		public List<ArrayList<Integer>> getAdjMatrix() {
			return adjMatrix;
		}
		
		public List<T> getContents(){
			List<Vertex<T>> vertices= getVertices();
			List<T> contents=new ArrayList<>();
			for(int i=0;i<vertices.size();i++) {
				contents.add(vertices.get(i).getValue());
			}
			return contents;
		}

	}
	
}
