package com.trie;

import java.util.LinkedList;

public class Trie {
	
	private int count;
	private Node root;
	
	public Trie(){
		this.root = new Node();
		this.count = 0;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**
	 * Method to get first character of a word
	 * @param str
	 * @return ASCII of first character of given word - ASCII of 'a' 
	 */
	public int getFirstCharacter(String str){
		return (int)str.charAt(0) - (int)'a';
	}
	public void addWord(Node node, String word){
		if(word.isEmpty())
			node.setWords(node.getWords() +1 );
		else{
			node.setPrefixes(node.getPrefixes() + 1);
			int k = getFirstCharacter(word);
			if(node.getEdges()[k] == null){
				node.getEdges()[k] = new Node();
			}
			addWord(node.getEdges()[k], word.substring(1, word.length()));
		}
	}
	/**
	 * Method to get Number of words with a given prefix
	 * @param node
	 * @param prefix
	 * @return Number of words with a given prefix
	 */
	public int countPrefixes(Node node, String prefix){
		
		if(prefix.isEmpty())
			return node.getPrefixes();
		else if(node.getEdges()[getFirstCharacter(prefix)] == null)
			return 0;
		else{
			return countPrefixes(node.getEdges()[getFirstCharacter(prefix)], prefix.substring(1,prefix.length()));
		}
	}
	
	/**
	 * Method to get number of occurrences of a word
	 * @param node
	 * @param word
	 * @return Number of occurrences of given word
	 */
	public int countWords(Node node, String word){
		
		if(word.isEmpty())
			return node.getWords();
		else if(node.getEdges()[getFirstCharacter(word)] == null)
			return 0;
		else{
			return countWords(node.getEdges()[getFirstCharacter(word)], word.substring(1,word.length()));
		}
	}
	
	/**
	 * Method to traverse throught the trie. This gives all the words in sorted order.
	 * @param node
	 * @param word
	 * @param list : A list to contain the words
	 */
	public void traversal(Node node, String word, LinkedList list){
		
		if(node == null)
			return;
		Node child[] = node.getEdges();
		for(int i=0;i<26;i++){
			if(child[i] != null){
				if(child[i].getWords()>0){
					int count =child[i].getWords();
					while(count >0){
						list.add(word + (char)(i + 97));
						count--;
					}
				}
				if(child[i].getPrefixes()>0)
					traversal(child[i],word + (char)(i + 97), list);
			}
		}
	}
	
	/**
	 * Method to delete a word from the trie
	 * @param node
	 * @param word
	 * @return true if word to be deleted is found else false
	 */
	public boolean deleteWord(Node node, String word){
		if(word.isEmpty()){ 
			if(node.getWords() > 0){
				node.setWords(node.getWords() - 1);
				return true;
			}
			return false;
		}		
		else if(node.getEdges()[getFirstCharacter(word)] == null)
			return false;
		else{
			if(deleteWord(node.getEdges()[getFirstCharacter(word)], word.substring(1, word.length()))){
				node.setPrefixes(node.getPrefixes() - 1);
				return true;
			}
			return false;
		}
	}
	
	public static void main(String []args){
		Trie t = new Trie();
		t.addWord(t.root,"tree");
		t.addWord(t.root,"trie");
		t.addWord(t.root,"algo");
		t.addWord(t.root,"assoc");
		t.addWord(t.root,"all");
		t.addWord(t.root,"also");
		t.deleteWord(t.root, "algo");
		LinkedList<String> list = new LinkedList<String>();
		t.traversal(t.root, "", list);
		for(String e: list){
			System.out.println(e);
		}
		
		System.out.println(t.countPrefixes(t.root,"al"));
	}
}
