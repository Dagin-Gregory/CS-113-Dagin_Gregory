package edu.miracosta.cs113;

import java.util.Scanner;

public class Driver
{
    // Finals
    private static final String POLY_NAME_A = "Polynomial A: ";
    private static final String POLY_NAME_B = "Polynomial B: ";
    private static final int BUFFER_A = -8;

    // Static
    private static Polynomial polyA, polyB;
    private static Scanner keyboard;

    public static void main(String[] args)
    {
        // Instantiate variables
        polyA = new Polynomial();
        polyB = new Polynomial();

        // Header
        ConsoleForm.printHeader(3, "Polynomials", "7/27/2021");
        ConsoleForm.printTitle(60, "----- Polynomial Driver -----");

        // execute main menu
        mainMenu();
    }


    private static void mainMenu()
    {
        // Declare
        int userChoice;
        String[] menuOptions;
        Menu mainMenu;

        // Initialize
        menuOptions = new String[]{"Edit Polynomial A", "Edit Polynomial B", "Display Sum", "Exit"};
        mainMenu = new Menu(menuOptions);
        keyboard = new Scanner(System.in);

        // Title
        ConsoleForm.printTitle(60, "--- Main Menu ---");

        // Display polynomials
        printPolynomial(POLY_NAME_A, polyA);
        printPolynomial(POLY_NAME_B, polyB);
        System.out.println();

        // Display menu
        mainMenu.printOptions();
        userChoice = mainMenu.prompt(keyboard);
        executeMainMenuChoice(userChoice);

    }


    private static void executeMainMenuChoice(int choice)
    {
        switch (choice)
        {
            case 1:
                editPolynomial(POLY_NAME_A, polyA);
                break;

            case 2:
                editPolynomial(POLY_NAME_B, polyB);
                break;

            case 3:
                displaySum();
                break;

            case 4:
                keyboard.close();
                System.exit(0);

            default:
                System.out.println("Unknown State");
                break;
        }
    }


    private static void editPolynomial(String polyName, Polynomial poly)
    {
        // Declare local variables
        String[] menuOptions;
        Menu subMenu;
        int userChoice;

        // Initialize local variables
        menuOptions = new String[]{"Add Term", "Remove Term", "Clear Polynomial", "Main menu"};
        subMenu = new Menu(menuOptions);

        // Print header
        ConsoleForm.printTitle(60, "-- Edit Polynomial --");

        // print polynomial
        printPolynomial(polyName, poly);
        System.out.println();

        // print menu
        subMenu.printOptions();

        // prompt for choice
        userChoice = subMenu.prompt(keyboard);

        executeEditPolynomialChoice(userChoice, poly, polyName);
    }


    private static void executeEditPolynomialChoice(int choice, Polynomial poly, String polyName)
    {
        switch (choice)
        {
            case 1:
                addTerm(polyName, poly);
                break;

            case 2:
                removeTerm(polyName, poly);
                break;

            case 3:
                clearPolynomial(polyName, poly);
                break;

            case 4:
                mainMenu();
                break;

            default:
                System.out.println("Unknown State");
                break;
        }
    }


    private static void addTerm(String polyName, Polynomial poly)
    {
        // Local variables
        String input;
        int coefficient, exponent;

        // Initialize variables


        // Print information
        ConsoleForm.printTitle(60, "- Add Term -");
        printPolynomial(polyName, poly);

        System.out.println("Leave input empty to go back.\n");

        // prompt user for Coefficient
        System.out.print("Enter term Coefficient: ");
        input = keyboard.nextLine();

        // check for empty string
        if (input.isEmpty())
        {
            editPolynomial(polyName, poly);
        }
        coefficient = validateInput("Enter a valid Coefficient value  ", input);

        // prompt user for Exponent
        System.out.print("Enter term Exponent: ");
        input = keyboard.nextLine();

        // check for empty string
        if (input.isEmpty())
        {
            editPolynomial(polyName, poly);
        }
        exponent = validateInput("Enter a valid Exponent value ", input);

        // Add term to polyNomial
        poly.addTerm(new Term(coefficient, exponent));

        // Start from top
        addTerm(polyName, poly);
    }


    private static void removeTerm(String polyName, Polynomial poly)
    {
        // local variables
        String input;
        int index;
        Term removedTerm;

        // Print infomration
        ConsoleForm.printTitle(60, "- Remove Term -");
        printFormattedPolynomial(polyName, poly);
        printIndexes(poly);

        System.out.println("Leave Blank to go back or");
        System.out.print("Enter a Term choice to remove: ");
        input = keyboard.nextLine();
        if (input.isEmpty())
        {
            editPolynomial(polyName, poly);
        }
        else if (poly.getNumTerms() == 0)
        {
            System.out.println("No move Terms to remove, going back!!!!");
            editPolynomial(polyName, poly);

        }
        index = validateIndex(input, poly.getNumTerms());
        removedTerm = poly.remove(index - 1);

        if (removedTerm == null)
        {
            System.out.println("Unable to remove Term");
        }
        else
        {
            System.out.println(removedTerm + " was removed.");
        }

        removeTerm(polyName, poly);

    }


    private static void clearPolynomial(String polyName, Polynomial poly)
    {
        String input;
        System.out.print("This will remove all terms from " + polyName + ", are you sure(Y/N): ");
        input = keyboard.nextLine();
        if (input.equalsIgnoreCase("y"))
        {
            poly.clear();
            System.out.println(polyName + " was cleared");
        }
        else
        {
            System.out.println(polyName + " was not cleared");
        }

        editPolynomial(polyName, poly);
    }


    private static void displaySum()
    {
        // local variable
        String temp;
        Polynomial polySum;

        // Title
        ConsoleForm.printTitle(60, "- Display Sum -");

        // calculate
        polySum = new Polynomial(polyA);
        polySum.add(polyB);

        // display results
        printPolynomial(POLY_NAME_A, polyA);
        printPolynomial(POLY_NAME_B, polyB);
        printPolynomial("Poly Sum:     ", polySum);
        System.out.println();

        // Prompt use to continue
        System.out.print("Press any key to continue...");
        temp = keyboard.nextLine();
        mainMenu();
    }


    private static int validateIndex(String str, int maxRange)
    {
        String message = "Must enter a value of (1-" + maxRange + "): ";
        boolean notValid;
        int value;

        notValid = true;
        value = 0;

        while (notValid)
        {
            try
            {
                value = Integer.parseInt(str);
                if (value < 1 || value > maxRange)
                {
                    throw new IndexOutOfBoundsException();
                }
                notValid = false;
            } catch (IndexOutOfBoundsException e)
            {
                System.out.print("Invalid Range, " + message);
                str = keyboard.nextLine();
                System.out.println();
            } catch (NumberFormatException e)
            {
                System.out.print("Invalid Input, " + message);
                str = keyboard.nextLine();
                System.out.println();
            } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

        return value;
    }

    private static void printPolynomial(String title, Polynomial polynomial)
    {
        String temp;

        if (polynomial.getNumTerms() == 0)
        {
            temp = " - Empty - ";
        }
        else
        {
            temp = polynomial.toString();
        }

        System.out.println(String.format("%s%s", title, temp));
    }


 
    private static void printFormattedPolynomial(String title, Polynomial polynomial)
    {
        String temp, str;
        int buffer;

        if (polynomial.getNumTerms() == 0)
        {
            temp = " - Empty - ";
        }
        else
        {
            temp = "";
            for (int i = 0; i < polynomial.getNumTerms(); i++)
            {
                temp += String.format("%" + BUFFER_A + "s", polynomial.getTerm(i));
            }

        }

        System.out.println(String.format("%s%s", title, temp));
    }


    private static void printIndexes(Polynomial poly)
    {
        System.out.println("Term  choice: " + indexPoly(poly));
    }

    private static String indexPoly(Polynomial poly)
    {
        String temp, str;
        int buffer;

        temp = "";

        for (int i = 0; i < poly.getNumTerms(); i++)
        {
            str = String.format("[%d]", i + 1);
            temp += String.format("%" + BUFFER_A + "s", str);
        }

        temp += "\n";
        return temp;
    }


    private static int validateInput(String prompt, String input)
    {
        // local variables
        boolean invalid;
        int value;

        // initialize
        invalid = true;
        value = 0;

        while (invalid)
        {
            try
            {
                value = Integer.parseInt(input);
                invalid = false;
            } catch (Exception e)
            {
                System.out.println("\nInvalid Input, only Integers allowed EX:(-45, 0, 56):");
                System.out.print(prompt);
                input = keyboard.nextLine();
            }
        }
        return value;
    }
}
