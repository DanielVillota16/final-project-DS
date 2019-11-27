package model;

import java.util.List;

import graphs.*;

public class Graph {
	
	private AdjListGraph<Node> adjList;
	
	private AdjMatrixGraph<Node> adjMatrix;
	
	private int N;
	
	private int E;
	
	private int T;
	
	private int answer;
	
	private int totalW;
	//private int M;
	
	public Graph(int n, int e, int t) {
		adjList = new AdjListGraph<Node>(true, true);
		adjMatrix = new AdjMatrixGraph<Node>(true, true);
		N = n;
		E = e;
		T = t;
		answer = 0;
		createCells();
	}
	
	public Graph(int n) {
		adjList = new AdjListGraph<Node>(false, true);
		adjMatrix = new AdjMatrixGraph<Node>(false, true);
		N = n;
		answer = 0;
		createVertices();
	}
	
	private void createCells() {
		for (int i = 1 ; i <= N; i++) {
			Node n = new Node(i);
			adjList.addVertex(n);
			adjMatrix.addVertex(n);
		}
		adjMatrix.getVertices().get(E-1).getValue().setType(Node.EXIT);
	}
	
	public void createVertices() {
		for (int i = 0 ; i < N; i++) {
			Node n = new Node(i);
			adjList.addVertex(n);
			adjMatrix.addVertex(n);
		}
	}
	

	
	public AdjListGraph<Node> getAdjList() {
		return adjList;
	}
	public AdjMatrixGraph<Node> getAdjMatrix() {
		return adjMatrix;
	}
	public int getAnswer() {
		return answer;
	}
	
	public void setTotal(int answer) {
		this.totalW += answer;
	}
	
	public void solveMice() {
		int success = 0;
		double[][] floyd = adjMatrix.floydwarshall();
		for (int j = 0; j < floyd.length; j++) {
			if(floyd[j][E-1] <= T) {
				adjMatrix.getVertices().get(j).getValue().setType(Node.LEAVE);
				success++;
			}
		}
		answer = success;		
	}
	
	public void solveDark() {
		adjList.prim(adjList.getVertices().get(0));
		int ans = 0;
		for(int i = 0; i < adjList.getVertices().size(); ++i) {
			ans += adjList.getVertices().get(i).getD();
		}
		answer = totalW-ans;
	}

	public void EdgesTrue() {
		List<Edge<Node>> list = adjList.getEdges();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setMarked(true);
		}
		
	}

}
