package edu.miracosta.cs113.change;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
* ChangeCalculator : Class containing the recursive method calculateChange, which determines and prints all
* possible coin combinations representing a given monetary value in cents.
*
* Problem derived from Koffman & Wolfgang's Data Structures: Abstraction and Design Using Java (2nd ed.):
* Ch. 5, Programming Project #7, pg. 291.
*
* NOTE: An additional method, printCombinationsToFile(int), has been added for the equivalent tester file to
* verify that all given coin combinations are unique.
*/
public class ChangeCalculator {

static int[] coinValues = {25, 10, 5, 1};

static ArrayList<Integer> c;

private static ArrayList<String> combos = new ArrayList<String>();


/**
* Wrapper method for determining all possible unique combinations of quarters, dimes, nickels, and pennies that
* equal the given monetary value in cents.
*
* In addition to returning the number of unique combinations, this method will print out each combination to the
* console. The format of naming each combination is up to the user, as long as they adhere to the expectation
* that the coins are listed in descending order of their value (quarters, dimes, nickels, then pennies). Examples
* include "1Q 2D 3N 4P", and "[1, 2, 3, 4]".
*
* @param cents a monetary value in cents
* @return the total number of unique combinations of coins of which the given value is comprised
*/
public static int calculateChange(int cents) {

	c = new ArrayList<Integer>();

	//Populate the list with zeros
	for (int i = 0; i < cents + 1; i++){
		c.add(0);
	}

	c.set(0, 1); //set the first element to 1 bc 0 cents can be made 1 way

	for (int i = 0; i < coinValues.length; i++) { //nested loop to loop through the values of the arraylsit

		for (int j = 0; j < cents + 1; j++) {
			if (coinValues[i] <= j) //Once the coins value is bigger than the loop we can keep expanding our count recursively
			c.set(j, c.get(j) + c.get(j - coinValues[i])); //update the list by setting the index equal to the current index added to the old value (c.get j - coinvalues[i])
			}
	}

	makeChange(cents, 0, 0, 0, cents); // return the value at the last position of the list (cents)

	for(String string : combos) { //Print combinations to console
		System.out.println(string);
	}

	return c.get(cents);

}

/**
* Takes total cents and number of coins and adds them to arraylist 'combs' storing combinations
* @param total total coins
* @param numQuarter number of quarters
* @param numDime number of dimes
* @param numNickel number of nickels
* @param numPenny number of pennies
*/
private static void makeChange(int total, int numQuarter, int numDime, int numNickel, int numPenny) {

final int QUARTER = coinValues[0], DIME = coinValues[1], NICKEL = coinValues[2], PENNY = coinValues[3];

if (numQuarter * QUARTER + numDime * DIME + numNickel * NICKEL + numPenny * PENNY > total) {
	return;
}

//Combination in string
String s = "[" + numQuarter + ", " + numDime + ", " + numNickel + ", "
+ numPenny + "]";

if (!combos.contains(s))
combos.add(s);


// Recursive Cases
if (numPenny >= 5)
makeChange(total, numQuarter, numDime, numNickel + 1, numPenny - 5);

if (numPenny >= 10)
makeChange(total, numQuarter, numDime + 1, numNickel, numPenny - 10);

if (numPenny >= 25)
makeChange(total, numQuarter + 1, numDime, numNickel, numPenny - 25);
}

/**
* Calls upon calculateChange(int) to calculate and print all possible unique combinations of quarters, dimes,
* nickels, and pennies that equal the given value in cents.
*
* Similar to calculateChange's function in printing each combination to the console, this method will also
* produce a text file named "CoinCombinations.txt", writing each combination to separate lines.
*
* @param cents a monetary value in cents
*/
public static void printCombinationsToFile(int cents) {

	calculateChange(cents);

	try {
		File file = new File(System.getProperty("user.dir") + "/src/edu.miracosta.cs113/change/CoinCombinations.txt");
		PrintWriter pw = new PrintWriter(new FileWriter(file));
			for (String combination : combos) {
				pw.println(combination);
			}
			pw.close();
		} 
	
	catch (IOException e) {
		e.printStackTrace();
	}
}

} // End of class ChangeCalculator