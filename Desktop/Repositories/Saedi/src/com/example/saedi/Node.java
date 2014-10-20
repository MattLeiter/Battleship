package com.example.saedi;


public class Node {
	
	private float x;
	private float y;
	private String name;
	
	public Node(float x, float y, String nm) {
		this.x = x;
		this.y = y;
		this.name = nm;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public String getName() {
		return name;
	}

}
