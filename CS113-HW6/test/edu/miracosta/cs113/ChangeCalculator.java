package edu.miracosta.cs113;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ChangeCalculator {

	static ArrayList<Integer> c = new ArrayList<Integer>();
	
	private static Integer[] coinValues = {25,10,5,1};
	
	private static ArrayList<String> combos = new ArrayList<String>();
	
	public static int calculateChange(int cents) {
		
		//Clear the list from any previous operation so it has a blank slate to get started from
		c.clear();
		
		/*Add one to the begging of the list as the recursion needs a starting node to begin the process
		* which in this case is 1 as there is only a single way to make 0 cents, which is by simply having nothing.
		*/
		c.add(1);
		for(int x = 0; x < cents; x++) {
			c.add(0);
		}
		
		for(int coinValue : coinValues) {
			for(int i = 0; i <= cents; i++) {
				//Review if statement
				if(i >= coinValue) {
					/*c.get(i) will return the "set that contains our value", where as c.get(i - coinValue) will return the set that doesn't
					 *contain our coin. (Formula gotten from book and multiple online sources/this is how a recursion tree works)
					 */
					c.set(i, c.get(i) + c.get(i - coinValue));
				}
			}
		}
		
		getComboList(cents, 0, 0, 0, cents);
		
		return(c.get(cents));
		
	}
	
	public static void getComboList(int cents, int quarter, int dime, int nickel, int penny){
		
		final int Quarter = coinValues[0], Dime = coinValues[1], Nickel = coinValues[2], Penny = coinValues[3];
		
		/* If the coin values multiplied by the amount of coins there are doesn't equal the amount of cents we want, the combination is invalid
		 * so throw it out and move on.
		*/
		
		String s = "[" + quarter + ", " + dime + ", " + nickel + ", "+ penny + "]";
		
		/*If the coins multiplied by their respective values don't sum to the total # of cents we're looking for, OR the current
		* combination isn't unique, we can simply disregard the entire branch, as we've just found it to be an invalid
		* combination or non-unique, that means that another branch has either traced out or is tracing out the same route this branch 
		* will trace anyways or it sums to a cent value we're not looking for, meaning it's unnecessary and we can just stop it right here.
		*/
		if(quarter * Quarter + dime * Dime + nickel * Nickel + penny * Penny != cents || combos.contains(s)) {
			return;
		}
		
		combos.add(s);
		//System.out.println(s);
		
		/*This function works by trying to reduce the amount of pennies it has to 0, and along the way will branch off and return every valid
		 * combination for a given amount of cents along the way.
		 * 
		 * This works as when an amount of cents is input, it can satisy one or more of the if statements, which will branch out
		 * and create all of the possible coin combinations for a given starting cents amount, and some branches will overlap, however
		 * since each leaf is checking whether it's combination is unique or not before adding it to the list of valid combinations,
		 * this eliminates the possibility of having two valid combinations in the ending list that are exactly the same.
		 */ 
		
		if(penny >= 5) {
			getComboList(cents, quarter, dime, nickel + 1, penny - 5);
		}
		if(penny >= 10) {
			getComboList(cents, quarter, dime + 1, nickel, penny - 10);
		}
		if(penny >= 25) {
			getComboList(cents, quarter + 1, dime, nickel, penny - 25);
		}
		
		
	}
	
	public static void printCombinationsToFile(int cents){
		
		try {
			File file = new File("test/edu/miracosta/cs113/CoinCombinations.txt");
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			
			for (String combination : combos) {
				pw.println(combination);
			}
			pw.close();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
	} // End of class ChangeCalculator

}