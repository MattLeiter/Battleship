package com.example.saedi;

public class Link {
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private Node first;
	private Node second;
	
	public Link(float x1, float y1, float x2, float y2, Node frst, Node scnd) {
		
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.first = frst;
		this.second = scnd;
	}
	
	public Node getFirst() {
		return first;
	}
	
	public Node getSecond() {
		return second;
	}

}
