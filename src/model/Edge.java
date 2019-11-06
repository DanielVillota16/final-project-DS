package model;

public class Edge<V> implements Comparable<Edge<V>>{
	private V vertex;
	private int weight;
	private String id;
	public Edge(V vertex, int weight, String id) {
		this.vertex = vertex;
		this.weight = weight;
		this.id = id;
	}
	public Edge(int weight, String id) {
		this.weight = weight;
		this.id = id;
	}
	public V getVertex() {
		return vertex;
	}
	public void setVertex(V vertex) {
		this.vertex = vertex;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return id;
	}
	@Override
	public int compareTo(Edge<V> o) {
		// TODO Auto-generated method stub
		return id.compareTo(o.id) + weight==o.weight?0:1;
	}
	
}