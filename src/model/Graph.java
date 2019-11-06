package model;

import java.util.List;

public interface Graph<T extends Comparable<T>> {
	public boolean adjacent(T source, T dest);
	public List<T> neighbors(T source);
	public void addVertex(T x);
	public void removeVertex(T x);
	void addEdge(T source, T dest, int weight, String id, boolean bidirectional);
	void removeEdge(T source, T dest, boolean bidirectional);
	
}
