package csc316.project1;

/**
 * 
 * LinkedList.java
 * 
 * This is a class created to implement a Linked List.  It needs a separate class
 * for "Nodes".  The methods are written for this to operate as a singly linked list.
 * 
 * @author Thomas "Andy" Archer
 *
 */
public class LinkedList {
	
	//Starting Node for the list.
	public Node start;
//	public int count;

/**
 * Constructor for the LinkedList.  This initializes the start Node with 
 * an exclamation point since the list will be populated with words and not 
 * punctuation.  This produces an easy way to spot if the start is called when 
 * it should not be in the output.
 */
	public LinkedList() {
		
		start = new Node( "!" );
//		count = 0;
		
	}

	/**
	 * Method which checks to see if the list is empty.  Returns false if there 
	 * are any words in the linked list.
	 * 
	 * @return boolean - true if list is empty, false if list has any element(s)
	 */
	public boolean isEmpty(){
		
		return start.next == null;
		
	}

/**
 *  Method which adds a Node to the list.  
 *  	
 * @param input - String element which is a word to be included in the list.
 */
	public void add( String input ){
		
		Node temp = new Node( input );
		temp.next = start.next;
		start.next = temp;
//		printList();
	}

	/**
	 *  Method to move a Node to the top of the list.  This method finds the 
	 *  Node by matching the string passed into the method with a word in the list.
	 *  
	 * @param move - String of the word to be moved to the front of the list.
	 */
	public void moveToFront( String move ){
		
		Node temp = start.next;
		Node temp_prev = start;
		String target = temp.word;
		
		while (( target.equals( move )) != true ){
			temp_prev = temp;
			temp = temp.next;
			target = temp.word;
		}
		
		temp_prev.next = temp.next;
		temp.next = start.next;
		start.next = temp;
//		printList();
	}
	
	/**
	 * Method to move a Node to the top of the list.  This is based on the 
	 * position of the item number passed into the method as an int.
	 * 
	 * @param position - int with the number of the node to be repositioned to the
	 * 					 top
	 */
	public void moveToFront ( int position ){
		Node temp = start.next;
		Node temp_prev = start;
		
		for (int i = 1; i < position; i++){
			temp_prev = temp;
			temp = temp.next;
		}
		
		temp_prev.next = temp.next;
		temp.next = start.next;
		start.next = temp;
//		printList();
	}

	/**
	 * This method returns the word in the Node at a given position in the 
	 * LinkedList.  This position is passed into the method by an int.
	 * 
	 * @param position - int representing the position of the item in the 
	 * 					 list to be returned.
	 * 
	 * @return - String - word in the list in the position requested.
	 */
	public String posToWord( int position ){
		Node temp = start.next;
		
		for (int i = 1; i < position; i++){
			temp = temp.next;
		}
		
		return temp.word;
	}
	
	/**
	 * Method to determine in a given object is in the LinkedList.  This method
	 * is case sensitive.  
	 * 
	 * @param find - String - word being looked for in the list.
	 * 
	 * @return boolean - returns true if the word is found in the list.  
	 * 					 returns false if the word is not found in the list.
	 */
	public boolean find( String find ){
		Node temp = start.next;
		
		while (temp != null){
			if( temp.word.equals( find )){
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	/**
	 * Method to return the position of the requested word in the LinkedList. This
	 * method is case sensitive.
	 * 
	 * @param find - String - word being searched for in the list.
	 * 
	 * @return - int - returns the number the item is in the list (top being 1)
	 * 				   returns -1 if the requested word is not in the list.
	 */
	public int position( String find ){
		Node temp = start;
		int pos = 0;
		while (temp != null){
			if( temp.word.equals( find )){
				return pos;
			}
			temp = temp.next;
			pos++;
		}
		return -1;
	}
	
	/** 
	 * Method to print the entire list, starting at the top, in the terminal window. 
	 * Intended for debugging purposes.
	 */
	public void printList(){
		Node temp = start;
		String printList = new String("");
		while( temp.next != null){
			temp = temp.next;
			printList += temp.word + " ";
			
		}
		System.out.println(printList);
	}
}
