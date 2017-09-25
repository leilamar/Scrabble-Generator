/**
 * This class represents a sequence of letters from which permutations and words
 * can be constructed. Given a dictionary, this class can generate all permutations 
 * of the letters that are words in the dictionary (and more).
 * 
 * @author Leila Mardoum
 * @version 3/1/17
 */

import java.util.ArrayList;

public class Permutations {
	
	private String letters;
	
	/**
	 * Constructor that takes a String object containing a sequence of letters 
	 * from which permutations will be generated
	 * 
	 * @param letters String of letters to be permuted
	 * @throws IllegalArgumentException if the String letters contains illegal
	 * characters (the only legal characters are letters a-z and A-Z)
	 */
	public Permutations(String letters) throws IllegalArgumentException {
		//check if letters composed of only legal characters (letters a-z and A-Z)
		for(int i = 0; i < letters.length(); i++){
			if(!Character.isLetter(letters.charAt(i))){
				throw new IllegalArgumentException("Error: you entered an invalid character;"
						+ " only letters can be accepted");
			}
		}
		
		//check if there is at least one letter in the given string
		if(letters.length() == 0){
			throw new IllegalArgumentException("Error: no letters provided, cannnot compute any words");
		}
		
		//if all requirements are satisfied, can initialize letters
		this.letters = letters.toLowerCase();
		
	}
	
	/**
	 * Computes and returns a list of all permutations of letters in this permutation 
	 * object (may throw an OutOfMemoryError for objects with more than 10 letters)
	 * 
	 * @return an ArrayList<String> containing all possible permutations of letters
	 */
	public ArrayList<String> getAllPermutations(){
		//initialize ArrayList which will hold all permutations
		ArrayList<String> allPerms = new ArrayList<String>();
		
		if(letters.length() == 1){
			//if just one letter, will only be one permutation
			allPerms.add(letters);
		} else {
			//else, call helper method to get all permutations
			getAllPermHelper(letters.length(), "", letters, allPerms);
		}
		return allPerms;
	}
	
	/**
	 * Helper method to getAllPermutations method. Generates all possible permutations
	 * of the letters in this Permutations object
	 * 
	 * @param length number of characters in the string of letters we are permuting
	 * @param perm keeps track of the developing permutation
	 * @param givenLetters keeps track of which letter to use next in permutation
	 * @param allPerms the ArrayList that will store all the permutations
	 */
	private void getAllPermHelper(int length, String perm, String givenLetters, ArrayList<String> allPerms){
		
		char letter = ' '; //will hold letter to add to permutation
		if(length == 0){ //base case
			if(!allPerms.contains(perm)){ //check for duplicates
				allPerms.add(perm);
			}
			return;
		}
		
		for(int i = 0; i < length ; i++){ //loop through the length of letters
			letter = givenLetters.charAt(i); //get appropriate letter to add
			String seq = perm + letter; //add the letter to the permutation
			
			//adjust which letter should come next in permutation
			StringBuilder letterFeed = new StringBuilder(givenLetters);
			letterFeed.deleteCharAt(i);
			letterFeed.append(letter);
			
			//call method with new values, decrement length to eventually reach base case
			getAllPermHelper(length - 1, seq, letterFeed.toString(), allPerms);
		}
	}
	
	/**
	 * Computes and returns a list of all words contained in the dictionary object
	 * that can be created from the letters in this permutation object. 
	 * 
	 * @param dictionary location to compare currently generating permutations with
	 * words in dictionary
	 * @return ArrayList of permutations that are words in the dictionary
	 */
	public ArrayList<String> getAllWords(Dictionary dictionary){
		//initialize an ArrayList in which to store found words
		ArrayList<String> allWords = new ArrayList<String>();
		//call helper method which will do the permutation generating and populating of the list
		getAllWordsHelper(letters.length(), "", letters, allWords, dictionary);
		return allWords;
	}
	
	/**
	 * Helper method to getAllWords method. Generates permutations of the letters 
	 * in this permutation object and populates a list with all words contained in
	 * the dictionary object that can be created from the letters. Stops generating 
	 * a given permutation as soon as it determines that it is not a prefix for any 
	 * word in the dictionary. 
	 * 
	 * @param length number of characters in the string of letters we are permuting
	 * @param perm keeps track of the developing permutation
	 * @param givenLetters keeps track of which letter to use next in permutation
	 * @param allWords the ArrayList that will store all the found words
	 * @param dict dictionary where list of words are stored; used to compare 
	 * currently generating permutations with prefixes and words in the dictionary
	 */
	private void getAllWordsHelper(int length, String perm, String givenLetters, ArrayList<String> allWords, Dictionary dict){
		char letter = ' '; //will hold letter to add to permutation
		
		//check if the proper length and a word in the dictionary
		if(length == 0 && dict.isWord(perm)){ 
			if(!allWords.contains(perm)){ //check for duplicates
				allWords.add(perm); 
			}
			return;
		}
		
		for(int i = 0; i < length ; i++){ //loop through the length of letters
			letter = givenLetters.charAt(i); //get appropriate letter to add
			String seq = perm + letter;//add the letter to the permutation
		
			//check if there any words in the dictionary that begin this sequence of characters
			if(!dict.isPrefix(seq)){
				//if not, no need to finish compiling this permutation
				continue;
			}
			
			//adjust which letter should come next in permutation
			StringBuilder letterFeed = new StringBuilder(givenLetters);
			letterFeed.deleteCharAt(i);
			letterFeed.append(letter);
			
			//call method with new values, decrement length to for permutation to 
				//eventually reach correct length
			getAllWordsHelper(length - 1, seq, letterFeed.toString(), allWords, dict);
		}
	}
	
//	/**
//	 * Inefficient implementation of getAllWords. Gets a list of all possible permutations
//	 * and then loops through, checking whether each permutation is in the dictionary. Not
//	 * included in final program.
//	 */
//	public ArrayList<String> getAllWords(Dictionary dictionary){
//		//get a list of all possible permutations of the letters
//		ArrayList<String> allPerms = getAllPermutations();
//		ArrayList<String> foundWords = new ArrayList<String>();
//		
//		//loop through each permutation and check if it is a word in the dictionary
//		for(String permutation : allPerms) {
//			if(dictionary.isWord(permutation)) 
				//if so, add it to the list of found words
//				foundWords.add(permutation);
//		}
//		
//		return foundWords;
//	}
}
