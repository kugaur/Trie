package com.trie;

public class Node {
	
	private int words;
	private int prefixes;
	private Node edges[];
	
    public Node(){
    	this.words =0;
    	this.prefixes =0;
    	this.edges = new Node[26];
    }

	public int getWords() {
		return words;
	}

	public void setWords(int words) {
		this.words = words;
	}

	public int getPrefixes() {
		return prefixes;
	}

	public void setPrefixes(int prefixes) {
		this.prefixes = prefixes;
	}

	public Node[] getEdges() {
		return edges;
	}

	public void setEdges(Node[] edges) {
		this.edges = edges;
	}
}
