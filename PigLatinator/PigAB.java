// Austin Bennett
// PigLatinatorPlus! (PigAB)

import javax.swing.JOptionPane;

public class PigAB {

	public static void main(String[] args) {

		// Instantiation of object menu to use methods in it
		Operations menu = new Operations();

		// Initializing values to make sure that the program works correctly
		boolean quitProgram = false;
		int menuSelect = -1;

		// Loop prompting for action while player hasn't selected a string
		do {

			// Prompts for input
			menuSelect = Integer.parseInt(JOptionPane.showInputDialog
		          ("1. Enter a new string\n" +
			   "2. Report the number of words in the message\n" +
		           "3. Write the message in reverse order\n" +
			   "4. Alphabatize the words in the message\n" +
			   "5. Provide an ordered list of unique words with frequencies\n" +
			   "6. Convert the string to Pig Latin\n" +
			   "7. End the program\n" + "Please select an option: "));

			// Enters a new string
			if (menuSelect == 1) {
			    String currString = JOptionPane.showInputDialog
			    		                        ("Please enter a string!");
				menu.resetString(currString);
			}

			// Prints the number of words in the sentence
			else if (menuSelect == 2) {
				menu.printNumWords();
			}

			// Prints the string in reverse
			else if (menuSelect == 3) {
				menu.printReverse();
			}

			// Prints the string in alphabetical order
			else if (menuSelect == 4) {
				menu.printAlphabetical();
			}

			// Prints the frequency of the words
			else if (menuSelect == 5) {
				menu.printFrequencies();
			}

			// Prints the word in Pig Latin
			else if (menuSelect == 6) {
				menu.pigLatinator();
			}

			// Sets the quitProgram sentinel value to true
			else if (menuSelect == 7) {
				quitProgram = true;
			}

			// Alerts the user that the number entered is not a valid selection
			else {
				System.out.print("Invalid input! Please select an option!");
			}

		} while (quitProgram == false);
	}

}
