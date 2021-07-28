package edu.miracosta.cs113;

import java.util.Scanner;
import java.text.DecimalFormat;

public class ConsoleForm
{
    // Student information
    private final static String NAME = "Dagin Gregory";
    private final static String COURSE = "CS113 - Data Structures & Algorithms";
    private final static String TIME = "M,W 3:30pm - 5:20pm";
    
    // Symbol used for border
    private final static char SYMBOL = '*';

    // line description tag
    private final static String NAME_STR = "NAME:";
    private final static String COURSE_STR = "COURSE:";
    private final static String TIME_STR = "TIME:";
    private final static String HOMEWORK_STR = "HOMEWORK #:";
    private final static String PROJECT_STR = "PROJECT #:";
    private final static String MODIFIED_STR = "LAST MODIFIED:";

    // desired line length and buffer for tags
    private final static int LINE_LENGTH = 60;
    private final static int LINE_BUFFER = 20;
    
    // format for lines , integer and decimals
    private final static String STR_FORMAT_1 = 
            String.format("%c %%-%ds", SYMBOL, LINE_BUFFER); 
    private final static String STR_FORMAT_2 = 
            String.format("%%-%ds%c\n",LINE_LENGTH - LINE_BUFFER - 3, SYMBOL);
    private final static String INT_FORMAT = "%d\n";
    private final static DecimalFormat DEC_FORMAT = new DecimalFormat("###.##");


    public static void printHeader(int homework, String description, String lastModified)
    {
        System.out.println(header(homework, description, lastModified));
    }

    public static String header(int homework, String description, String lastModified)
    {
        String temp = "";
        temp += getHorizontalLine(SYMBOL, LINE_LENGTH) + "\n";
        temp += authorLine();
        temp += courseLine();
        temp += timeLine();
        temp += homeworkLine(homework, description);
        temp += lastModifiedLine(lastModified);
        temp += getHorizontalLine(SYMBOL, LINE_LENGTH);
        return temp;
    }

    // METHOD: Print author information for start of program + project Name
    public static void printHeader(int homework, double project, String lastModified)
    {
        System.out.println(header(homework, project, lastModified));
    }

    // METHOD: return a formated string with desired student and homework
    // information
    public static String header(int homework, double project, String lastModified)
    {
        String temp = "";
        temp += getHorizontalLine(SYMBOL, LINE_LENGTH) + "\n";
        temp += authorLine();
        temp += courseLine();
        temp += timeLine();
        temp += homeworkLine(homework);
        temp += projectLine(project);
        temp += lastModifiedLine(lastModified);
        temp += getHorizontalLine(SYMBOL, LINE_LENGTH);
        return temp;
    }

    // METHOD: Print author information for start of program + project Name
    public static void printHeader(int homework, String homeworkDescription, 
            double project, String projectDescription, String lastModified)
    {
        System.out.println(header(homework, homeworkDescription, project, 
                projectDescription, lastModified));
    }

    // METHOD: return a formated string with desired student and homework
    // information
    public static String header(int homework, String homeworkDescription, 
            double project, String projectDescription, String lastModified)
    {
        String temp = "";
        temp += getHorizontalLine(SYMBOL, LINE_LENGTH) + "\n";
        temp += authorLine();
        temp += courseLine();
        temp += timeLine();
        temp += homeworkLine(homework, homeworkDescription);
        temp += projectLine(project, projectDescription);
        temp += lastModifiedLine(lastModified);
        temp += getHorizontalLine(SYMBOL, LINE_LENGTH);
        return temp;
    }

    // HELPER: return formated author line
    private static String authorLine()
    {
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, NAME_STR, NAME);
    }

    // HELPER: returns formated course line
    private static String courseLine()
    {
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, COURSE_STR, COURSE);
    }

    // HELPER: returns formated time ine
    private static String timeLine()
    {
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, TIME_STR, TIME);
    }

    // HELPER: return formated homework line
    private static String homeworkLine(int homework)
    {
        return String.format(STR_FORMAT_1 + INT_FORMAT, HOMEWORK_STR, homework);
    }

    // HELPER: returns formated homework line with description
    private static String homeworkLine(int homework, String homeworkDescription)
    {
        String temp = homework + " - " + homeworkDescription;
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, HOMEWORK_STR, temp);
    }

    // HELPER: return formated project line
    private static String projectLine(double project)
    {
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, PROJECT_STR, 
                DEC_FORMAT.format(project));
    }

    // HELPER: return formated project line with description
    private static String projectLine(double project, String projectDescription)
    {
        String temp = DEC_FORMAT.format(project) + " - " + projectDescription;
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, PROJECT_STR, temp);
    }

    // HELPER: returns formated last modified line
    private static String lastModifiedLine(String lastModified)
    {
        return String.format(STR_FORMAT_1 + STR_FORMAT_2, 
                MODIFIED_STR, lastModified);
    }


    public static void printTitle(int width, String title)
    {
        title = ConsoleForm.getCentered(width, title);
        System.out.printf("\n%s\n\n", title);
    }


    public static String getCentered(int width, String text)
    {
        String line;
        int beforeTextSpace;
        beforeTextSpace = ((width - text.length()) / 2);

        line = ConsoleForm.getHorizontalLine(' ', beforeTextSpace);
        line += text;
        line += ConsoleForm.getHorizontalLine(' ', beforeTextSpace);
        return line;
    }


    public static String getHorizontalLine(char symbol, int length)
    {
        String line = "";

        for (int i = 0; i < length; i++)
        {
            line += symbol;
        }
        return line;
    }
}
