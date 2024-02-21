package view;


import utility.constants.Announcements;
import utility.constants.MenuConstants;
import utility.service.Stopwatch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.InputMismatchException;


/**
 * <h1>MyIO</h1>
 * <p>
 *      This class stores methods for displaying different outputs such as various print methods
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-10
 */
public class MyIO implements MenuConstants, Announcements
{
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss:SSS";
    private static final String PROMPT = "-> ";

    private static boolean timeStamp = false;
    private static boolean verboseMode = true;


    /**
     * @author Hannah Wollenweber
     * This method prints a given text
     * @precondition text is given
     * @postcondition text is printed
     * @param text text that is to be printed
     * */
    public static void print (String text)
    {
        if (verboseMode)
        {
            if (timeStamp)
            {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)) + PROMPT + text);
            } else
            {
                System.out.println(text);
            }
        }
    }


    /**
     * @author Hannah Wollenweber
     * method description
     * @precondition All required parameters are passed.
     * @postcondition text is printed in desired colour
     * @param text String to be printed
     * @param color color of the text
     * */
    public static void print (String text, ConsoleColor color)
    {
        System.out.print(color);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }


    /**
     * @author Hannah Wollenweber
     * This method prints a text with a desired colour on a desired console colour
     * @precondition All required parameters are passed.
     * @postcondition text is printed in colour in adjusted console
     * @param text the String to be formatted
     * @param color desired text color
     * @param backgroundColor desired color of the console
     * */
    public static void print (String text, ConsoleColor color, ConsoleColor backgroundColor)
    {
        System.out.print(color);
        System.out.print(backgroundColor);
        print(text);
        System.out.print(ConsoleColor.RESET);
    }


    /**
     * @author Hannah Wollenweber
     * This method formats a String in the desired way
     * @precondition both parameters are given correctly
     * @postcondition the text is formatted with desired color
     * @param text String to be formatted
     * @param color desired text color
     * @return String that is formatted as desired
     * */
    public static String formatString (String text, ConsoleColor color)
    {
        return color + text + ConsoleColor.RESET;
    }


    /**
     * @author Hannah Wollenweber
     * This method formats the given message with the stopwatch and collection so that the message text is displayed with the
     * required time and the size of the collection.
     * @precondition All required parameters are passed.
     * @postcondition The result message is correctly formatted
     * @param message message that is to be printed
     * @param collection collection of which the size is to be displayed
     * @param stopwatch stopwatch to add elapsed time to message
     * */
    public static void printTimeMessageAndSize (String message, Stopwatch stopwatch, Collection<?> collection)
    {
        MyIO.printLine(formatString(String.format(message, stopwatch.getElapsedTimeSinceMethodCall(), collection.size()), ConsoleColor.GREEN_BRIGHT));
    }


    /**
     * @author Hannah Wollenweber
     * This method formats the given message with the stopwatch and number so that the message text is displayed with the
     * required time and number.
     * @precondition All required parameters are passed.
     * @postcondition The result message is correctly formatted
     * @param message message that is to be printed
     * @param number number which is to be displayed
     * @param stopwatch stopwatch to add elapsed time to message
     * */
    public static void printTimeMessageAndNumber (String message, Stopwatch stopwatch, int number)
    {
        MyIO.printLine(formatString(String.format(message, stopwatch.getElapsedTimeSinceMethodCall(), number), ConsoleColor.GREEN_BRIGHT));
    }


    /**
     * @author Hannah Wollenweber
     * This method prints a given Object as String
     * @precondition An object is passed
     * @postcondition the object passed is printed out
     * @param objectToPrint Object that is supposed to be printed
     * */
    public static void printLine (Object objectToPrint)
    {
        System.out.println(objectToPrint.toString());
    }


    //TODO: ADD COMMENTS!!!
    public static String getUserInput ()
    {
        print(USER_PROMPT, ConsoleColor.CYAN);
        String userInput = String.valueOf(ERROR_NEGATIVE_ONE);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try
        {
            userInput = br.readLine();
            return userInput;
        } catch (InputMismatchException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return userInput;
    }


    //--------------------------------------
    //Setter
    public static void setTimeStamp (boolean timeStamp)
    {
        MyIO.timeStamp = timeStamp;
    }


    public static void setVerboseMode (boolean verboseMode)
    {
        MyIO.verboseMode = verboseMode;
    }
}