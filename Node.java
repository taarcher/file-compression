package csc316.project1;

/**
 * 
 * Node.java
 * 
 * This is a class created to create a Node for a singly linked list
 * of strings.  The only data the node holds is a string.  Should it a different 
 * data type be necessary for the list, the String portion should be able to change
 * easily and fit different needs.
 * 
 * @author Thomas "Andy" Archer
 *
 */
public class Node {
	
	//Link to the next Node in the linked list
	Node next;
	
	//String holding the word in this Node of the linked list
	String word;

/**
 * Constructor for the Node.  
 * 
 * @param input - String being stored in this Node for the Linked List
 */
	public Node( String input) {
		
		word = input;
	}

}
