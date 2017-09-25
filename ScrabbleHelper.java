/**
 * This class holds the main program. Expects two command line arguments, the first
 * a text file that is a dictionary with a list of words and the second a sequence
 * of letters. The program produces all anagrams of the given set of letters that 
 * are valid words in the provided dictionary.
 * 
 * @author Leila Mardoum
 * @version 3/1/17
 */

import java.io.*;
import java.util.*;

public class ScrabbleHelper {
	
	public static void main(String[] args) {
		
		//check if the proper number of command line arguments entered
		if(!(args.length == 2)){
			//if not, print appropriate error message and exit program
			System.err.println("Usage Error: the program expects two argument "
					+ "(a dictionary text file and a sequence of letters)");
			System.exit(1);
		}
		
		//take first parameter and make new File object
		File file = new File(args[0]);
		
		//attempt to construct a Dictionary and Permutations object
		Dictionary dict = null;
		Permutations letters = null;
		
		try{
			//validation for the file occurs in the Dictionary class
			dict = new Dictionary(file);
			//validation for the letters occurs in the Permutations class
			letters = new Permutations(args[1]);
		} catch(IllegalArgumentException e){
			//catch exceptions, print appropriate message, and terminate program
			System.err.println(e.getMessage());
			System.exit(1);
		}
		
		//create a list of all permutations of letters that are words in the dictionary dict
		ArrayList<String> words = letters.getAllWords(dict);
		//sort words alphabetically
		Collections.sort(words); 
		int countWords = words.size();
		
		//print results
		if(countWords == 0){
			System.out.println("No words found");
		} else {
			//state number of words found
			if(countWords != 1)
				System.out.println("Found " + countWords + " words: ");
			else
				System.out.println("Found 1 word: ");
			
			//print words found
			for(String word : words){
				System.out.println("   " + word);
			}
		}
	}
}

