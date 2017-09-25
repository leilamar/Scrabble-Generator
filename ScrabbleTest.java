import java.util.ArrayList;

public class ScrabbleTest {
	
	public static void main(String[] args) {
		String s1 = " aBcdEF3*!";
//		System.out.println(s1);
//		System.out.println(s1.toLowerCase());
//		
//		String s2 = " ";
//		System.out.println(Character.isLetter(s2.charAt(0)));
		
//		for(int i = 0; i < s1.length(); i++){
//			if(!Character.isLetter(s1.charAt(i))){
//				System.out.println("Not valid");
//			} else {
//				System.out.println("Valid");
//			}
//		}
//		
		//first thing I tried for recursive solution
//		int low = 0;
//		int high = entries.size() - 1;
//		int mid = (low + high)/2;
//		int comparison = (entries.get(mid)).compareTo(key);
//		
//		if(entries.size() == 0){ //special case
//			return -1;
//		} else if(comparison == 0){ //base case
//			return mid;
//		} else {
//			ArrayList<String> cut = new ArrayList<String>();
//			if(comparison > 0){ //key is before current entry 
//				//copy entries from indices 0 to mid-1 into new ArrayList
//				for(int i = 0; i < mid; i++){
//					cut.set(i, entries.get(i));
//				}
//			} else {
//				for(int i = mid + 1; i < entries.size(); i++){
//					cut.set(i, entries.get(i));
//				}
//			}
//			
//			return binarySearch(cut, key);
//		}
		
		//recursive sol'n to getAllPermutations with char array
//		if(perm.length() == length){
//		if(!allPerms.contains(perm))
//			allPerms.add(perm); //will modify original ArrayList passed in right?
//		} else {
//			for(char letter : letterArray){
//				String seq = perm + letter;
//				getAllPermHelper(length, seq, letterArray, allPerms);
//			}
//		}
		
//		availableLetters = givenLetters.substring(i+1) + letter;
//		availableLetters = givenLetters.substring(0,i) + givenLetters.substring(i+1, givenLetters.length()-1);
		
		//iterative binary search
//		int low = 0;
//		int high = entries.size() - 1;
		
		//iterative implementation
//		while(high >= low){
//			int mid = (low + high)/2;
//			int comparison = (entries.get(mid)).compareTo(key);
//			
//			if(comparison == 0) //or if((entries.get(mid)).equals(key))**
//				return mid;
//			else if(comparison > 0) //key is before current entry (correct alphabetical?)**
//				high = mid - 1;
//			else if(comparison < 0) //key is after current entry
//				low = mid + 1;
//		}
//		return -1;
		
		//testing substring
//		String test2 = "ape";
//		System.out.println(test2.substring(3));
		
		
		ArrayList<String> test = new ArrayList<String>();
		test.add("apple"); //0 
		test.add("ask"); //1
		test.add("banana"); //2 overflows
		test.add("blah"); //3 
		test.add("carrot"); //4
		test.add("cope"); //5 overflows
		test.add("dorian"); //6 overflows
		test.add("dose"); //7 MIDDLE
		test.add("egg"); //8 overflows
		test.add("fish"); //9 overflows
		test.add("flounder"); //10
		test.add("goat"); //11
		test.add("happy"); //12 overflows
		test.add("ignorance"); //13
		test.add("jackel"); //14
//		System.out.println(binarySearch(test, "car", 0, test.size()-1));
		
		//compareTo String
//		System.out.println(test.get(2).compareTo("bb")); 
		//I don't think it has to be case insensitive?
		int result = isPrefixHelper(test, "ig", 0, test.size()-1);
		System.out.println(result);
		if(result != -1)
			System.out.println(true);
		else
			System.out.println(false);
	}
	
	public static int isPrefixHelper(ArrayList<String> entries, String key, int low, int high){
		//binary search searching for prefixes
		int mid = (low + high)/2;
		int comparison = entries.get(mid).compareTo(key);
		
		if(entries.size() == 0 || high < low){ //special case
			return -1;
		} else if((entries.get(mid)).startsWith(key)){ //base case (entries.get(mid)).startsWith(key)
			System.out.println("look at mid");
			return mid;
		} else if(comparison > 0){ //key is before current entry 
			System.out.println("recursive call - look at bottom half " + low + "-" + (mid-1));
			return isPrefixHelper(entries, key, low, mid-1);
		} else { //key is after current entry
			System.out.println("recursive call - look at top half " +  + (mid+1) + "-" + high);
			return isPrefixHelper(entries, key, mid+1, high);
		} 
	}
	
	public static int binarySearch(ArrayList<String> entries, String key, int low, int high){
		int mid = (low + high)/2;
		int comparison = (entries.get(mid)).compareTo(key);
		
		if(low > high)
			return -1;
		
		if(entries.size() == 0 || high < low){ //special case
			return -1;
		} else if(comparison == 0){ //base case
			return mid;
		} else if(comparison > 0){ //recursive solution //key is before current entry 
				return binarySearch(entries, key, low, mid-1);
		} else { //key is after current entry
			return binarySearch(entries, key, mid+1, high);
		} 
	}

}
