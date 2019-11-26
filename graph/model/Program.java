package model;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private List<Graph> graphs;
	
	public Program(int C) {
		graphs = new ArrayList<Graph>(C);
	}
	
	
	public List<Graph> getGraphs() {
		return graphs;
	}
	
	public void addGraph(int n, int e, int t) { 
		graphs.add(new Graph(n, e, t));
	}
	
	public void addEdge(int a, int b, int W) {
		Graph g = graphs.get(graphs.size()-1);
		Node A = g.getAdjMatrix().getVertices().get(a-1).getValue();
		Node B = g.getAdjMatrix().getVertices().get(b-1).getValue();
		g.getAdjList().addEdge(A, B, W);
		g.getAdjMatrix().addEdge(A, B, W);
	}
	
	public int solveMice(int C) {
		int g = graphs.size()-C;
		return graphs.get(g).solveMice();
	}
	
	
}
