package com.train.graph.KeyPath;

public class Vertex {

	public int in;
	private Object value;
	private boolean isVisited;

	public Vertex(Object value) {
		this.value = value;
	}

	public Object value() {
		return value;
	}

}
