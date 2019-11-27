package model;

import java.util.ArrayList;
import java.util.List;

public class Program {

	private List<Graph> graphs;
	
	public Program(int C) {
		graphs = new ArrayList<Graph>(C);
	}
	
	public Program() {
		graphs = new ArrayList<Graph>();
	}
	
	public List<Graph> getGraphs() {
		return graphs;
	}
	
	public void addGraph(int n, int e, int t) { 
		graphs.add(new Graph(n, e, t));
	}
	
	public void addGraph(int n) { 
		graphs.add(new Graph(n));
	}
	
	public void addEdge(int a, int b, int W, int n) {
		Graph g = graphs.get(graphs.size()-1);
		Node A = g.getAdjMatrix().getVertices().get(a-n).getValue();
		Node B = g.getAdjMatrix().getVertices().get(b-n).getValue();
		g.getAdjList().addEdge(A, B, W);
		g.getAdjMatrix().addEdge(A, B, W);
		g.setAnswer(W);
	}
	
	public void solveMice() {
		int g = graphs.size()-1;
		graphs.get(g).solveMice();
	}
	
	public void solveDark() {
		int g = graphs.size()-1;
		graphs.get(g).solveDark();
	}
	
}
