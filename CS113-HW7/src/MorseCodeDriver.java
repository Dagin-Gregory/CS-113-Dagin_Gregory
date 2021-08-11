import edu.miracosta.cs113.MorseCodeTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MorseCodeDriver.java - The main menu program for morse code translation.
 *
 * Algorithm:
 *
 * 1. The program will prompt the user which of the three options they would like to execute:
 * - List all the letters and their morse code representation.
 * - Translate morse code from a text file.
 * - Translate morse code from a String entered in by the user.
 *
 * 2. List all the letters and their morse code representation: Takes in a file and reads each line containing the
 * character and its morse code representation. Then it stores each of the morse code representations, then translates
 * them through the MorseCodeTree translateMorseCode method. Then it prints the character and morse code representation
 * to the console.
 *
 * 3. Translate morse code from text file: The program will prompt the user for the name of the text file they would
 * like to use for translating into morse code. Once connected to the file, the program will take in each line as a
 * representation of the morse code the user would like to translate. Once the String has been taken in, it will
 * be given as an argument to the translateMorseCode method, and the translation will be printed to the screen.
 *
 * 4. Translate morse code from String entered in by the user: Like option two this option will translate a String using
 * the translateMorseCode method to the console.
 *
 * 5. This whole program will continue in a loop until the user elects to terminate the program.
 *
 * @author Matthew Sheehan
 * @version 1.0
 */
class MorseCodeDriver {

    private static final String FILE_NAME = "";

    public static void main ( String[ ] args ) {
        runProgram ();
    }

    /**
     * Provides the interface for the user to translate morse code.
     */
    private static void runProgram ( ) {
        boolean continueProgram = true; // Determines if the user would like to continue using the program.

        System.out.println ( "Hello, and welcome to the morse code program.\n" );

        while ( continueProgram ) {

            // List options.
            menuOptions ( );

            // User picks option.
            int userSelection = checkMenuSelection ( );

            // Menu calls the respective option.
            selectionChosen ( userSelection );

            // User is prompted if they would like to continue using the program.
            System.out.println ( "Would you like to continue using the program?" );
            continueProgram = yesOrNoInput ( );
        }
        // User has opted to quit using the program.
        System.out.println ( "Goodbye.\n" );
        System.exit ( 0 );
    }

    /**
     * Provides the user a list of options available in the program.
     */
    private static void menuOptions ( ) {
        System.out.println ( "Please choose from one of the following options:" );
        System.out.println ( "\t1. Display all letters and their morse code.");
        System.out.println ( "\t2. Translated morse code from a text file." );
        System.out.println ( "\t3. Translated morse code from the console.\n");
    }

    /**
     * Checks that the user has input a valid selection (a number 1 - 3).
     *
     * @return The user's legal argument.
     */
    private static int checkMenuSelection ( ) {
        int userSelection = 0; // The number chosen by the user.
        boolean validInput = false;

        Scanner sc = new Scanner ( System.in );
        System.out.print ( "Your selection: ");

        while ( ! validInput ) {

            try {
                userSelection = sc.nextInt ( );
                System.out.println ( " " );

                if ( userSelection < 1 || userSelection > 3 )
                    throw new IllegalArgumentException ( "Input must be a valid number, 1 - 3. Try again: " );

                validInput = true;
            } 
            catch ( InputMismatchException e ) {
                System.out.print ( "Input must be a valid number, 1 - 3. Try again: " );
            } 
            catch ( IllegalArgumentException e ) {
                e.getMessage ();
            }
            sc.nextLine ( ); // Clear buffer.
        }
        return userSelection;
    }

    /**
     * Selects the respective method based on the user's input.
     *
     * @param userSelection The menu option's corresponding number.
     */
    private static void selectionChosen ( int userSelection ) {
        switch ( userSelection ) {
            case 1:
                menuOptionOne ( );
                break;
            case 2:
                menuOptionTwo ( );
                break;
            case 3:
                menuOptionThree ( );
                break;
        }
        System.out.println ( " " );
    }

    /**
     * Takes in a file and reads each line containing the character and its morse code representation. Then it stores
     * each of the morse code representations, then translates them through the MorseCodeTree translateMorseCode
     * method. Then it prints the character and morse code representation to the console.
     *
     */
    private static void menuOptionOne ( ) {
        MorseCodeTree translator = new MorseCodeTree ( );
        Scanner input = null;

        ArrayList < String > morseCodeSegments = new ArrayList < > ( 26 ); // Stores the morse code in each line.
        char [ ] letters = new char [ 26 ]; // Stores the letter after morse code translation.

        try {
            input = new Scanner ( new FileInputStream ( FILE_NAME ) );
        } 
        catch ( FileNotFoundException e ) {
            System.out.println ( FILE_NAME + " file not found." );
        }

        // Take in morse code segments from file.
        while ( input.hasNext ( ) ) 
            morseCodeSegments.add ( input.nextLine (  ).substring ( 2 ) );
        

        // Translate the segments, using MorseCodeTree as per the instructions.
        for ( int i = 0; i < morseCodeSegments.size ( ); i++) 
            letters [ i ] = ( translator.translateFromMorseCode ( morseCodeSegments.get ( i ) ).charAt ( 0 ) );

        // Print the segments and letters to the screen.
        for ( int i = 0; i < morseCodeSegments.size ( ); i++ ) {
            System.out.printf ( "Morse Code: %-4s ", morseCodeSegments.get ( i ) );
            System.out.printf ( "  Letter: %2s", letters [ i ] );
            System.out.println ( " " );
        }
    }

    /**
     * Prompts the user for the file name containing morse code, then translates the information from the file.
     */
    private static void menuOptionTwo ( ) {
        MorseCodeTree translator = new MorseCodeTree ( ); // The object for performing the morse code translation.
        Scanner sc = new Scanner ( System.in ); // The Scanner that will take in the name of the file.
        Scanner input = null; // The Scanner that will take input from the file itself.
        String bigString = ""; // The String that will hold all the data from the file.
        boolean validInput = false; // Controls the loop until a valid input is made.

        System.out.print ( "Please enter the name/directory of the file you would like to translate morse code from: ");

        String fileName = sc.nextLine ( );

        while ( ! validInput ) {
            try {
                input = new Scanner ( new FileInputStream ( fileName ) );
                validInput = true;
            } 
            catch ( FileNotFoundException e ) {
                System.out.print ( fileName + " file not found. Try again: ");
                sc.nextLine(); // Clear buffer.
            }
        }

        while ( input.hasNext ( ) ) 
            bigString += input.nextLine ( );

        System.out.println ( translator.translateFromMorseCode ( bigString ) );
    }

    /**
     * Translates a String input by the user into morse code.
     */
    private static void menuOptionThree ( ) {
        Scanner sc = new Scanner ( System.in ); // Take in user input.
        MorseCodeTree translator = new MorseCodeTree ( ); // The object for translating the morse code.

        boolean validInput = false; // Determines if the user has entered a valid String for translation.

        System.out.print ( "Please enter the morse code you would like to translate: " );

        while ( ! validInput ) {
            try {
                String userInput = sc.nextLine ( );
                System.out.println ( "Translated: " + translator.translateFromMorseCode ( userInput ) );
                validInput = true;
            } 
            catch ( NullPointerException e ) {
                System.out.print ( e.getMessage ( ) + ". Try again: " );
            } 
            catch ( IllegalArgumentException e ) {
                System.out.print ( e.getMessage ( ) + ". Try again: " );
            }
        }
    }

    /**
     * Helper method for runProgram. Returns true or false based on the user's selection.
     *
     * @return true if user enters "yes" or its variants, false otherwise.
     */
    private static boolean yesOrNoInput ( ) {
        Scanner sc = new Scanner ( System.in );
        System.out.print ( "Your selection: " );

        String userInput = sc.next ( );

        System.out.println ( " " );

        userInput = userInput.toLowerCase ( );

        switch ( userInput ) {
            case "yes": case "y":
                return true;
        }
        return false;
    }
}
