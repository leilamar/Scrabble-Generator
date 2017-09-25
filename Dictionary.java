/**
 * This class represents a collection of words read in from a given dictionary text 
 * file. This class is responsible for performing queries in the dictionary, which 
 * includes determining if a given string is a prefix of any of the words in the 
 * dictionary or if a given string is a word in the dictionary.
 * 
 * @author Leila Mardoum
 * @version 3/1/17
 */

import java.io.*;
import java.util.*;

public class Dictionary {
	
	private ArrayList<String> words = new ArrayList<String>(); //where all words from file are stored
	
	/**
	 * Constructor that takes a File object as a parameter. The file should be a 
	 * dictionary with an alphabetical list of words. Checks if the file is valid 
	 * and if it is, stores all the words from the file in the Dictionary in the form
	 * of an ArrayList
	 * 
	 * @param f a dictionary file to with words to be stored
	 * @throws IllegalArgumentException if the there is something wrong with the file 
	 * (the file does not exist, it is not readable)
	 */
	public Dictionary(File f) throws IllegalArgumentException {
		//check if file is valid; if not, print appropriate message
		if (!f.exists()){
			throw new IllegalArgumentException("File " + f.getName() + " does not exist");
		} else if(!f.isFile()){
			throw new IllegalArgumentException(f.getName() + " is not a file");
		} else if(!f.canRead()){
			throw new IllegalArgumentException("File " + f.getName() + " cannot be read");
		} else {
			//populate dictionary with words, but catch exceptions scanner might throw
			try{
				storeAllWords(f);
			} catch (FileNotFoundException e){ 
				//if scanner had issues, throw exception to main method
				throw new IllegalArgumentException("Error reading file");
			}
		}
	}
	
	/**
	 * Determines if the string argument is one of the words stored in this dictionary
	 * using a recursive binary search
	 * 
	 * @param str the string to search for in this dictionary
	 * @return true if the word is found in the dictionary
	 * otherwise, false
	 */
	public boolean isWord(String str){
		//call to helper method binarySearch with necessary parameters
		if(isWordSearch(words, str, 0, words.size()-1) != -1) //if found, return true
			return true;
		else
			return false;
	}
	
	/**
	 * Helper method to isWord method. Searches an ArrayList of Strings for a key 
	 * using a recursive implementation of a binary search. (Differs from 
	 * isPrefixSearch as it searches for the key in the form of a word in the 
	 * list, not just in the form of a prefix)
	 * 
	 * @param entries the list that will be searched
	 * @param key the item to be searched for
	 * @return the index of the key if found
	 * if not found, returns -1
	 */
	private int isWordSearch(ArrayList<String> entries, String key, int low, int high) {
		//get the middle index of the given ArrayList
		int mid = (low + high)/2;
		//compare the word at the middle index with the specified prefix using String compareTo
		int comparison = (entries.get(mid)).compareTo(key);
		
		if(entries.size() == 0 || high < low){ //not found
			return -1;
		} else if(comparison == 0){ //base case
			return mid;
		} else if(comparison > 0){ //key is alphabetically before current entry 
				return isWordSearch(entries, key, low, mid-1);
		} else { //key is alphabetically after current entry
			return isWordSearch(entries, key, mid+1, high);
		} 
	}
	
	/**
	 * Determines if the string argument is a prefix for at least one of the words
	 * in this dictionary using a binary search like, recursive approach
	 * 
	 * @param str the prefix to be searched for in this dictionary
	 * @return true if prefix is found in the dictionary
	 * otherwise, false
	 */
	public boolean isPrefix(String str){
		if(isPrefixSearch(words, str, 0, words.size()-1) != -1) //if found, return true
			return true;
		else
			return false;
	}
	
	/**
	 * Helper method to isPrefix method. It is a binary search implementation 
	 * that searches for a specified prefix in a specified ArrayList
	 * 
	 * @param entries ArrayList containing the list of words to be searched through
	 * @param key the prefix to search for
	 * @param low the starting/beginning index of the search
	 * @param high the highest index of the search
	 * @return the index at which the prefix is first found
	 * if not found, return -1
	 */
	private int isPrefixSearch(ArrayList<String> entries, String key, int low, int high){
		//get the middle index of the given ArrayList
		int mid = (low + high)/2; 
		//compare the word at the middle index with the specified prefix using String compareTo
		int comparison = entries.get(mid).compareTo(key);
		
		if(entries.size() == 0 || high < low){ //not found
			return -1;
		} else if((entries.get(mid)).startsWith(key)){ //base case
			return mid;
		} else if(comparison > 0){ //key is before current entry 
			return isPrefixSearch(entries, key, low, mid-1);
		} else { //key is after current entry
			return isPrefixSearch(entries, key, mid+1, high);
		} 
	}
	
	/**
	 * Method called by constructor to populate Dictionary with the words in the given 
	 * file
	 * 
	 * @param f File containing words to populate dictionary
	 * @throws FileNotFoundException if Scanner has error reading file
	 */
	private void storeAllWords(File f) throws FileNotFoundException { 
		Scanner input = new Scanner(f);
		
		//goes line by line, adding each word to the ArrayList storing the words
		while(input.hasNextLine()){
			String wordEntry = input.nextLine();
			words.add(wordEntry);
		}
		
		input.close();
	}
}
