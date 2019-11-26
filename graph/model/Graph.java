package model;

import graphs.*;

public class Graph {
	
	private AdjListGraph<Node> adjList;
	
	private AdjMatrixGraph<Node> adjMatrix;
	
	private int N;
	private int E;
	private int T;
	//private int M;
	
	public Graph(int n, int e, int t) {
		adjList = new AdjListGraph<Node>(true, true);
		adjMatrix = new AdjMatrixGraph<Node>(true, true);
		N = n;
		E = e;
		T = t;
		createVertices();
	}
	
	private void createVertices() {
		for (int i = 1 ; i <= N; i++) {
			Node n = new Node(i);
			adjList.addVertex(n);
			adjMatrix.addVertex(n);
		}
		adjMatrix.getVertices().get(E).getValue().setType(Node.EXIT);
		
	}
	
	public AdjListGraph<Node> getAdjList() {
		return adjList;
	}
	public AdjMatrixGraph<Node> getAdjMatrix() {
		return adjMatrix;
	}
	
	public int solveMice() {
		int success = 0;
		double[][] floyd = adjMatrix.floydwarshall();
		for (int j = 0; j < floyd.length; j++) {
			if(floyd[j][E-1] <= T) {
				adjMatrix.getVertices().get(j).getValue().setType(Node.LEAVE);
				success++;
			}
		}
		return success;
	}

}
