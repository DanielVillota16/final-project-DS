package model;

import java.util.Random;

public class Node { 
	
	private int name;
	
	private int x;
	private int y;
	private int type;//india el tipo de nodo, salida o que logro salir
	
	public final static int EXIT = 2;
	public final static int CELL = 0;
	public final static int LEAVE = 1;
	
	public Node(int n) {
		name = n;
		type = CELL;
		generatePos();
	}
	
	
	public void generatePos() {
		Random r = new Random();
		x = r.nextInt(700)+50; 
		y = r.nextInt(500)+50; 
		//x = (int)Math.random()*(700-50)+50;
		//y = (int)Math.random()*(500-50)+50;
	}
	
	public int getName() {
		return name;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
}
