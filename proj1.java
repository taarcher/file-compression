package csc316.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
*	proj1.java
*
*	Reads a text file and using a move to front linked list, replaces words with numbers
*	in order to reduce file size.  The LinkedList and Node objects are in other classes
*	in the project package.
*
*	Author:  Thomas "Andy" Archer
*
*	input:  text file through standard input
*	output: text file output.txt
**/
public class proj1 {

	LinkedList List = new LinkedList();
	
/**
 * 	Constructor for the program.
 * 
 *  @param input Scanner to read the file from standard input.  Passed by the main method.
 */
	public proj1( Scanner input ){
		
		Scanner in = input;
		
		PrintWriter out = null;
		try {
			out = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String of the first line of the input file as provided by the scanner
		String line = in.nextLine();
		
		//boolean indicator for compression or decompression
		boolean compress = true;
		String output = new String();
		
		//Variables to keep track of the two file sizes
		int UncompSize = 0;
		int CompSize = 0;
		
		// Check if file is already compressed with the "0 " indicator
		//toggle boolean indicator if necessary
		if (line.charAt(0) == '0'){
			compress = false;
			line = line.substring(2);
			CompSize += 2;
		}
		
		//process of the first line if compressing the file
		if( compress ){
			UncompSize += line.length();
			output = "0 " + processCompression( line ) + "\n";
			CompSize += output.length();
			out.print(output);
		
		//process of the first line if decompressing the file
		} else {
			CompSize += line.length();
			output = processDecompression( line ) + "\n";
			UncompSize += output.length();
			out.print(output);
		}
		
		//processing the remaining lines in the file
		while(in.hasNextLine()){
			line = in.nextLine();
			
			//if decompressing, checks for the line which contains
			//the file size statistics
			if( line.startsWith("0 ") ){
				break;
			}
			if( compress ){
				UncompSize += line.length();
				output = processCompression( line ) + "\n";
				CompSize += output.length();
			} else {
				CompSize += line.length();
				output = processDecompression( line ) + "\n";
				UncompSize += output.length();
			}
			out.print(output);
		}
		
		//print the file size statistics
		if ( compress ) {
			out.print("0 Uncompressed: " + UncompSize + " bytes;  Compressed: " + CompSize + " bytes");
		}
		//close the read and write files
		in.close();
		out.close();

		
	}
	
	/**
	 * Checks in the list is currently empty.  
	 * 
	 * @return returns true if no words in the list.
	 */
	public boolean isEmpty(){
		
		return List.isEmpty();
		
	}
	
	/**
	 * Adds a word to the list.  Adds it in the first position and shifts the existing list
	 * accordingly
	 * @param word  Word to add to the list as a string.
	 */
	public void addWord( String word ){
		List.add( word );
	}
	
	/**
	 * Moves a word from somewhere in the list to the front of the list based on the position.
	 * 
	 * @param position  position of the word to be moved to the front, with the front position being 1
	 */
	public void moveToFront( int position ){

		List.moveToFront( position );
	}
	
	/**
	 * Gets the word based on the position of the word in the list, as passed by value.
	 * 
	 * @param position int representing the current position of the word in the list
	 * @return returns the word at that position in the list, and moves the word to the 
	 * 		   front of the list.
	 */
	public String posToWord( int position ){
		String word = List.posToWord( position );
		List.moveToFront( position );
		return word;
	}

	/**
	 * Method to handle decompressing a given string representing a line in the file.
	 * 
	 * @param input string as a line in the input file
	 * @return compressed string
	 */
	public String processDecompression(String input) {
		String Decomp = new String();
		String temp = new String();
		int tempNum = 0;
		
		for( int i = 0; i < input.length(); i++){
			//read words in the file
			if( Character.isLetter(input.charAt(i))){
				temp += Character.toString(input.charAt(i));
				while( (i + 1) < input.length() && Character.isLetter(input.charAt(i+1)) ){
					i++;
					temp += Character.toString(input.charAt(i));
				}
				//add words to the list
				List.add(temp);
			//read numbers in the input file
			} else if ( Character.isDigit(input.charAt(i)) ){
				tempNum = input.charAt(i) - 48;
				while( (i + 1) < input.length() && Character.isDigit(input.charAt(i+1))){
					i++;
					tempNum *= 10;
					tempNum += (input.charAt(i) - 48);
				}
				if( tempNum > 0){
					temp = List.posToWord( tempNum );
					List.moveToFront( tempNum );
					tempNum = 0;
				}
			//print symbols
			} else {
				temp = Character.toString(input.charAt(i));
			}
			Decomp += temp;
			temp = "";
			tempNum = 0;
					
		}
		//return the decompressed string
		return Decomp;
	}

	/**
	 * Method to compress the given line of the input file passed as a string
	 * 
	 * @param input line of the input file as a string
	 * @return compressed line from file as a string
	 */
	public String processCompression(String input) {
		String Comp = new String();
		String temp = new String();
		for( int i = 0; i < input.length(); i++){
			//read words from the line
			if( Character.isLetter(input.charAt(i))){
				temp += Character.toString(input.charAt(i));
				while( (i + 1) < input.length() && Character.isLetter(input.charAt(i+1)) ){
					i++;
					temp += Character.toString(input.charAt(i));
				}
				//check for the word on the list
				int index = List.position(temp);
				//if word is on the list, move it to the front.
				if( index > 0 ){
					temp = "" + List.position(temp);
					if(index > 1){
						List.moveToFront( index );
					} 
				}
				//if word is not on the list, index will = -1
				//if that is the case, add temp to the list
				if( index < 1 ){
					List.add(temp);
				}
			//print symbols to the compressed file
			} else {
				temp = Character.toString(input.charAt(i));
			}
			Comp += temp;
			temp = "";
			
		}
		//return the compressed line of text from the input file.
		return Comp;
		
	}

/**
 * 		Main method which launches the program.  Loads the needed files.
 * @param args command line arguments from standard input
 * @throws FileNotFoundException Exception thrown if file is not found
 */
	
	public static void main(String[] args) throws FileNotFoundException {
		new proj1( new Scanner( new File( args[0] ) ) );
	}

}
