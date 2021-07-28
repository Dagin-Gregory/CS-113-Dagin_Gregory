package edu.miracosta.cs113;

import java.util.Scanner;

public class Driver 
{
	public static void main(String[] args)
	{
		String word;
		
		Scanner keyboard = new Scanner(System.in);
		ListStack<Character> stack1 = new ListStack<Character>();
		ListStack<Character> stack2 = new ListStack<Character>();
		
		System.out.print("Please enter a word: ");
		word = keyboard.nextLine();
		keyboard.close(); 
		
		if(word.length() % 2 == 0)
		{
			String firstHalf = "";
			String secondHalf = "";
			
			for(int i = 0; i < word.length(); i++)
			{
				stack1.push(word.charAt(i));
			}
			for(int i = 0; i < word.length() / 2; i++)
			{
				stack2.push(stack1.pop());
			}
			while(stack1.empty())
			{
				char letter = stack1.pop();
				firstHalf += letter;
			}
			while(stack2.empty())
			{
				char letter = stack2.pop();
				secondHalf += letter;
			}
			if(firstHalf.equalsIgnoreCase(secondHalf))
			{
				System.out.println(word + " IS a palindrome!");
			}
			else
			{
				System.out.println(word + " IS NOT a palindrome!");
			}
		}
		else
		{
			String firstHalf = "";
			String secondHalf = "";
			
			for(int i = 0; i < word.length(); i++)
			{
				stack1.push(word.charAt(i));
			}
			for(int i = 0; i < word.length() / 2; i++)
			{
				stack2.push(stack1.pop());
			}
			while(stack1.empty())
			{
				char letter = stack1.pop();
				firstHalf += letter;
			}
			while(stack2.empty())
			{
				char letter = stack2.pop();
				secondHalf += letter;
			}
			firstHalf = firstHalf.substring(1);
			if(firstHalf.equalsIgnoreCase(secondHalf))
			{
				System.out.println(word + " IS a palindrome!");
			}
			else
			{
				System.out.println(word + " IS NOT a palindrome!");
			}
		}
	}
}
