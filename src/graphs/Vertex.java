package graphs;

import java.util.Comparator;

public class Vertex<T> implements Comparable<Vertex<T>>{

	public static final int WHITE = 0;
	public static final int GRAY = 1;
	public static final int BLACK = 2;
	
	private T value;

	/**
	 * Distance or Initial TimeSTamp for DFS
	 */
	private double distance;
	/**
	 * Final TimeStamp for DFS
	 */
	private int finalTime;
	
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

	public double getDistance() {
		return distance;
	}

	public void setDistance(double d) {
		this.distance = d;
	}

	public int getFinalTime() {
		return finalTime;
	}

	public void setFinalTime(int f) {
		this.finalTime = f;
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

	@Override
	public int compareTo(Vertex<T> vertex) {
		int comp = Double.compare(distance, vertex.distance);
		if(value instanceof Comparable) {
			if(comp == 0) {
				return ((Comparable) value).compareTo(vertex.getValue());
			}
		}
		return comp;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
}
