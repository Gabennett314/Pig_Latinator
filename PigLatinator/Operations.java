import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class Operations {
	
	// Instance variables that keep track of important data
	private String chosenString;
	ArrayList<String> listOfWords;
	ArrayList<String> listOfSpacers;
	
	
	// Default constructor for the Operations Class
	public Operations () {
		resetString("Error: No String Entered!");
	}
	
	
	// Method for resetting a string and regenerating the listOfWords and
	// listOfWords ArrayLists based off of the new String
	public void resetString(String enteredString) {
		chosenString = enteredString;
		listOfWords = generateListOfBlocks(true);
		listOfSpacers = generateListOfBlocks(false);
	}
	
	
	// Splits up string into blocks of either words or spaces in-between them
	private ArrayList<String> generateListOfBlocks(boolean findAlpha) {
		ArrayList<String> spacerGroups = new ArrayList<String>();
		String currBlock = "";
		
		for (int index = 0; index < chosenString.length(); index++) {
			
			// Adds character to selected block if valid
			if (Character.isLetter(chosenString.charAt(index)) == findAlpha) {
				currBlock += chosenString.charAt(index);
			}
			
			// If character doesn't match the block, the block is added to the
			// ArrayList and a new block begins to be formed
			else if (currBlock.length() > 0) {
				spacerGroups.add(currBlock);
				currBlock = "";
			}
		}
		
		// If there is a remaining block after the loop ends, then the final
		// Block is added to the ArrayList
		if (currBlock.equals("") == false) {
			spacerGroups.add(currBlock);
		}
		
		return spacerGroups;
	}
	
	
	// Prints number of words in the string
	public void printNumWords() {
		System.out.println("Number of words: " + listOfWords.size());
	}
	
	
	// Prints string in reverse
	public void printReverse() {
		System.out.print("Reverse: ");
		for (int i = listOfWords.size()-1; i >= 0; i--) {
			System.out.print(listOfWords.get(i) + " ");
		}
		System.out.print("\n");
	}
	
	
	// Prints string in Alphabetical Order
	public void printAlphabetical() {
		
		// Makes sorted ArrayList of the listOfWords
		ArrayList<String> lexicographicalOrder = new ArrayList<String>
        														  (listOfWords);
		Collections.sort(lexicographicalOrder);
		
		// Prints the words in alphabetical order
		System.out.print("Alphabetized: ");
		for (String word: lexicographicalOrder) {
			System.out.print(word + " ");
		}
		
		System.out.print("\n");
	}
	
	
	// Generates a dictionary with the words in listOfWords as keys and the
	// Frequencies as the values
	private Map<String, Integer> generateFrequencies() {
		Map<String, Integer> wordFrequencies = new HashMap<String, Integer>();
		
		for (String currWord: listOfWords) {
			
			// If the current word isn't a key, one is made with it
			if (wordFrequencies.get(currWord.toLowerCase()) == null) {
				wordFrequencies.put(currWord.toLowerCase(), new Integer(1));
			}
			
			// Adds one to the value (frequency) of the key (word)
			else {
				wordFrequencies.put(currWord.toLowerCase(), 
						wordFrequencies.get(currWord.toLowerCase()) + 1);
			}
			
		}
		
		return wordFrequencies;
	}
	
	
	// Prints the frequencies of the words in lexicographical order
	public void printFrequencies () {
		
		// Initializes values used for printing
		Map<String, Integer> wordFrequencies = generateFrequencies();
		Set<String> outputtedValues = new HashSet<String>();
		String currNext = "";
		int currMost = 0;
		
		System.out.println("Frequency: ");
		
		// While not all keys have been printed, loop continues
		while (wordFrequencies.size() > outputtedValues.size()) {
			
			// Loops through keys and finds the next key by value and then by
			// Lexicographical Order to break ties
			for (String key: wordFrequencies.keySet()) {
				if ((wordFrequencies.get(key) > currMost || 
					(wordFrequencies.get(key) == currMost && 
					 key.compareTo(currNext) < 0)) && 
				    (outputtedValues.contains(key) == false)) {
					currNext = key;
					currMost = wordFrequencies.get(key);
				}
				
			}
			
			// Prints key
			System.out.println(currNext + ": " + currMost);
			
			// Primes value for next iteration
			outputtedValues.add(currNext);
			currNext = "";
			currMost = 0;
		}
		
	}
	
	//////////////////////// PIG LATINATOR BEGINS HERE /////////////////////////
	
	
	// Generates Pig-Latin for a word that has no vowels
	private String generateTypeOne (String chosenString) {
		return chosenString + "ay";
	}
	
	
	// Generates Pig-Latin for a word that starts with a vowel
	private String generateTypeTwo (String chosenString) {
		return chosenString + "yay";
	}
	
	
	// Generates Pig-Latin for a word that has, but doesn't start with a vowel 
	private String generateTypeThree (int splittingIndex, String chosenString) {
		
		// Sets initial values for later use 
		String newWord = "";
		char currChar;
		boolean isCapitalized = isCapitalized(chosenString);
		chosenString = chosenString.toLowerCase();
		
		// Takes slice of word after first vowel, and if first letter is 
		// Capitalized, the newWord starts capitalized
		for (int index=splittingIndex; index < chosenString.length(); index++) {
			currChar = chosenString.charAt(index);
			if (isCapitalized == true && index == splittingIndex)
				currChar = Character.toUpperCase(currChar);
			newWord += currChar;
		}
		
		// Adds on slice of word before first vowel
		for (int index = 0; index < splittingIndex; index++) {
			currChar = chosenString.charAt(index);
			newWord += currChar;
		}
		
		// Adds "ay" to word
		newWord = newWord + "ay";
		
		return newWord;
	}
	
	
	// Wrapper function for choosing type of Pig-Latin based on word
	private String findNewWord(int splittingIndex, String chosenString) {
		
		String newWord = "";
		
		// No vowels in word case
		if (splittingIndex == chosenString.length()) {
			newWord = generateTypeOne(chosenString);
		}
		
		// Word starts with vowel case
		else if (splittingIndex == 0) {
			newWord = generateTypeTwo(chosenString);
		}
		
		// Word doesn't start with, but contains a vowel
		else {
			newWord = generateTypeThree(splittingIndex, chosenString);
		}
		
		return newWord;
	}
	
	
	// Checks if the initial word is capitalized
	private boolean isCapitalized (String chosenString) {
		
		return (chosenString.charAt(0) >= 'A' && chosenString.charAt(0) <= 'Z');
		
	}
	
	
	// Finds index to spit word. The index is also used in determining the type
	// Of word, because it either finds the value or returns an error value
	// Which is the length if the word
	private int findSplittingIndex (String chosenString) {
		
		String vowelsInWord = "aeiou";
		int firstVowel = chosenString.length();
		chosenString = chosenString.toLowerCase();
		
		// Finds the first vowel
		for (int currVowel = 0; currVowel < vowelsInWord.length(); currVowel++){
			if(chosenString.indexOf(vowelsInWord.charAt(currVowel)) != -1 &&
			   chosenString.indexOf(vowelsInWord.charAt(currVowel))<firstVowel){
				firstVowel=chosenString.indexOf(vowelsInWord.charAt(currVowel));
			}
		}
		
		// Finds first Y if no vowels are present
		if (firstVowel==chosenString.length() && chosenString.indexOf('y')!=-1)
			firstVowel = chosenString.indexOf('y');
		
		return firstVowel;
		
	}
	
	
	// Wrapper function for a Pig-Latin word
	private String pigLatinateWord(String chosenWord) {
		
		int splittingIndex = findSplittingIndex(chosenWord);
		String pigLatinatedWord = findNewWord(splittingIndex, chosenWord);
		return pigLatinatedWord;
		
	}
	
	
	// Prints all values in Pig-Latin with punctuation intact
	public void pigLatinator () {
		
		// Prints alternating values of words and punctuation to generate
		// Words in Pig-Latin while keeping punctuation intact
		for (int index = 0; index < listOfWords.size(); index++) {
			
			// Case for an apostrophe (Since the second part is treated as
			// Another word, it just doesn't Pig-Latinate that one)
			if (index == 0 || (index != 0 && 
				listOfSpacers.get(index-1).equals("'") == false))
		        System.out.print(pigLatinateWord(listOfWords.get(index)));
			else
				System.out.print(listOfWords.get(index));
			
			// Case where String ends on a word
			if (index < listOfSpacers.size())
				System.out.print(listOfSpacers.get(index));
		}
		
		System.out.print("\n");
	}
}