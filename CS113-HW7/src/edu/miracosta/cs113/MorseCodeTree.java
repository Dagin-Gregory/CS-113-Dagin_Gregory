package edu.miracosta.cs113;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MorseCodeTree.java - A class for translating morse code by use of a binary tree.
 *
 * Instance Variables:
 *      - FILE_NAME (String): The name of the file that contains the pre-written letters and their morse code.
 *
 * Methods:
 *      -Constructors:
 *          - No-argument Constructor: Sets the root equal to '-' and initializes all nodes with letters of the alphabet.
 *      -translateMorseCode(String): Takes the String, checks it for validity, then by traversing through the nodes based
 *       on the morse code given (go to left node for every '*' and right for every '-') returns the respective letter from
 *       that node.
 *      -readMorseCodTree: Takes in ordered lines of characters and their respective morse code representation and adds
 *       them to the morseCodeTree.
 *
 */
public class MorseCodeTree extends BinaryTree < Character > implements Serializable {

    //private static final String FILE_NAME = "";
	private static final String FILE_NAME = "src/edu/miracosta/cs113/Morse_Code.txt";

    /**
     * Creates a new MorseCodeTree and adds letters to the tree.
     */
    public MorseCodeTree ( ) {
        root = new Node < >(' ');
        readMorseCodeTree ();
    }

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     * @throws NullPointerException If the String argument is null.
     * @throws IllegalArgumentException If the String contains illegal characters, or exceed legal String length (4).
     */
   public String translateFromMorseCode ( String morseCode ) throws NullPointerException, IllegalArgumentException {
        if ( morseCode.isEmpty ( ) )  // The String is empty or null.
            throw new NullPointerException ( "Null String." );

        // Check if any invalid characters exist in the String.
        for ( int i = 0; i < morseCode.length (); i++ ) {
            if ( morseCode.charAt ( i ) != '*' && morseCode.charAt ( i ) != '-' && morseCode.charAt ( i ) != ' ' )
                throw new IllegalArgumentException ( "The String may only contain spaces, \"*\", and \"-\"." );
        }

        // Split the code segments into an array of Strings and determine if any segments exceed the allotted length ( max length: 4 ).
        String [ ] codeSegments = morseCode.split ( " " );

        for ( String segment : codeSegments ) {
            if ( segment.length ( ) > 4 )
                throw new IllegalArgumentException ( "Morse code segment too long.\n" );
        }

        // After each case has been check, the translation can begin.
        StringBuilder translatedString = new StringBuilder (  );

        for ( String segment : codeSegments ) {
            Node < Character > currentNode = this.root; // The starting node for translation.

            for ( int i = 0; i < segment.length ( ); i++ ){
                if ( segment.charAt ( i ) == '*' )
                    currentNode = currentNode.left;
                else if ( segment.charAt ( i ) == '-' )
                    currentNode = currentNode.right;
            }

            translatedString.append ( currentNode.data );
        }
        return translatedString.toString ( );
    }

    /**
     * Takes in the lines from the pre-written text file and stores them in an ArrayList of Strings. For each line in
     * the ArrayList, we find the letter and the morse code associated with it. We then begin traversing the tree: if
     * the character at the current index is '*', we check the left node. If it is null, then we add our data to the
     * node. If it is full, the left node becomes the current node, and the for loop will check the next character to
     * determine which node to check next. For every character equal to '-', thr right side will be checked instead of
     * the left.
     */
    private void readMorseCodeTree ( ) {
        Scanner input = null;

        try {
            input = new Scanner ( new FileInputStream ( FILE_NAME ) );
        }
        catch ( FileNotFoundException e ) {
            System.out.println ( FILE_NAME + " file not found." );
        }

        ArrayList < String > codeSegments = new ArrayList < > ( 26 );

        // Break up each line and put it in an ArrayList.
        while ( input.hasNext () )
            codeSegments.add ( input.nextLine (  ) );

        for ( String line: codeSegments ) {
            Node<Character> currentNode = root;

            char newData = line.charAt ( 0 );   // The letter that will be the data for the node.
            String code = line.substring ( 2 ); // The morse code that will help us traverse through the tree.

            // Begin traversing the tree and adding data to the nodes.
            for ( int i = 0; i < code.length (); i++ ) {
                if ( code.charAt ( i ) == '*' ) { // Go left.
                    if ( currentNode.left == null ) // Node is empty, add data.
                        currentNode.left = new Node < > ( newData );
                    else // Node is not empty, left node becomes the current node.
                        currentNode = currentNode.left;
                } else if ( code.charAt ( i ) == '-' ) { // Go right.
                    if ( currentNode.right == null ) // Node is empty, add data.
                        currentNode.right = new Node < > ( newData );
                    else // Node is not empty, right node becomes the current node.
                        currentNode = currentNode.right;
                }
            }
        }
        input.close ();
    }
} // End class.
