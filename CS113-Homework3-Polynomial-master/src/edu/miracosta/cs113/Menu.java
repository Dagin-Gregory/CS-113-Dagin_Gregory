package edu.miracosta.cs113;

// imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Menu
{
	/********** CLASS VARIABLES **********/
	private ArrayList<String> options;
	
			/********** CONSTRUCTORS **********/
	
	// CONSTRUCTOR: default no argument constructor
	public Menu()
	{
		this.options = new ArrayList<String>();
	}
	
	// CONSTRUCTOR: full constructor
	public Menu(String[] options)
	{
		this.options = new ArrayList<String>(Arrays.asList(options));
	}

			/********** MUTATORS **********/
	
	// MUTATOR: sets menu options
	public void setOptions(String[] options)
	{
		this.options = new ArrayList<String>(options.length);
	}
	
			/********** ACCESSORS **********/
	
	// ACCESSOR: get menu options
	public ArrayList<String> getOptions()
	{
		return new ArrayList<String>(this.options);
	}
	

			/********** CLASS METHODS **********/
	
	// METHOD: prints out the menu options
	public void printOptions()
	{
		System.out.println(this.toString());
	}
	
	// METHOD: prompts the user for a choice within option range
	public int prompt(Scanner keyboard)
	{
		int temp = 0;
		String prompt;

		prompt = String.format("Select an option (1 - %d): ", options.size());
		temp = readInt(keyboard, prompt, options.size());

		return temp;
	}
	
	// METHOD: reads input from user wihting options range
	private static int readInt(Scanner keyboard, String prompt, int maxRange)
	{
		// Local variables
		String temp;
		boolean isNotValid;
		int result;
		
		temp = "";
		result = -1;	
		isNotValid = false;

		do
		{
			System.out.print(prompt);
			temp = keyboard.nextLine();
			try
			{
				result = Integer.parseInt(temp);
				isNotValid = result <= 0 || result > maxRange;
			}
			catch (NumberFormatException e)
			{
				isNotValid = true;
			}

			if (isNotValid)
			{
				System.out.println("Invalid Choice!!\n");
			}
		}
		while (isNotValid);

		return result;
	}
	
			/********** OVERRIDES **********/
	
	// OVERRIDE: equals() method for comparison
	public boolean equals(Object other)
	{
		if (other == null || other.getClass() != this.getClass())
		{
			return false;
		}
		else
		{
			Menu otherM= (Menu)other;
			return this.options.equals(otherM.getOptions());
		}
	}

	// OVERRIDE: toSring method for printing class content
	public String toString()
	{
		String temp = "";
		for(int i = 0; i < this.options.size(); i++)
		{
			temp += String.format("%d) %s\n", i+1, this.options.get(i));
		}
		
		return temp;
	}
}
