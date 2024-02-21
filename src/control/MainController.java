package control;


import utility.constants.Announcements;
import utility.constants.Constants;
import utility.constants.DataIndices;
import view.ConsoleColor;
import view.MyIO;

import java.util.InputMismatchException;

import static control.IOController.*;
import static view.MyIO.print;


/**
 * @author Hannah Wollenweber
 *
 * <h1>MainController</h1>
 * <p>
 *     This class contains the methods needed for confirming the programm should or can be executed.
 * </p>
 *
 * @version 1.0
 * @since 2024-02-21
 */
public class MainController implements Constants, Announcements, DataIndices
{
    /**
     * @author Hannah Wollenweber
     * This method checks if the correct amount of arguments is passed in a valid format
     *
     * @precondition the StringArray of arguments is passed
     * @postcondition the arguments have been validated
     *
     * @param arguments arguments passed in command line
     */
    public static void validateArguments (String[] arguments)
    {
        int length = arguments.length;
        checkIfEnoughArguments(length);
        checkIfTooManyArguments(length);
        testDatatype(arguments);
    }


    /**
     * @author Hannah Wollenweber
     * This method terminates the programm if too few arguments have been passed
     *
     * @precondition the number passed is correct
     * @postcondition if too few arguments are passed the programm is exited
     *
     * @param numberOfArgs number of arguments passed in command line
     */
    private static void checkIfEnoughArguments (int numberOfArgs)
    {
        if (numberOfArgs == ZERO)
        {
            print(ERROR_NO_ARGUMENT_IN_COMMAND_LINE, ConsoleColor.RED);
            System.exit(ZERO);
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method terminates the programm if too many arguments have been passed
     *
     * @precondition the number passed is correct
     * @postcondition if too many arguments are passed the programm is exited
     *
     * @param numberOfArgs number of arguments passed in command line
     */
    private static void checkIfTooManyArguments (int numberOfArgs)
    {
        if (numberOfArgs > MAX_NUMBER_OF_ARGS)
        {
            print(ERROR_TOO_MANY_ARGUMENTs_IN_COMMAND_LINE, ConsoleColor.RED);
            System.exit(ZERO);
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method terminates the programm if the arguments are not of type int
     *
     * @precondition the Array of arguments is passed
     * @postcondition the programm terminated if one or more arguments are in an invalid format
     *
     * @param arguments StringArray of arguments passed in command line
     */
    private static void testDatatype (String[] arguments)
    {
        for (String argument : arguments)
        {
            try
            {
                int number = Integer.parseInt(argument);
            } catch (NumberFormatException e)
            {
                MyIO.print(ERROR_INVALID_ARGUMENT_IN_COMMAND_LINE, ConsoleColor.RED);
                MyIO.print(e.getMessage(), ConsoleColor.RED);
                System.exit(ZERO);
            }
        }
    }


    //TODO COMMENTS


    /**
     * @author Hannah Wollenweber
     * This method checks a user's input and either terminates the programm or calls further methods entering the core programm
     *
     * @precondition the user answers the prompt with an accepted choice
     * @postcondition the programm is continued or terminated based on the user's choice
     *
     * @param arguments StringArray of arguments passed in command line
     * @throws IllegalArgumentException invalid user input
     * @exception InputMismatchException input is in invalid format
     */
    public static void printMenu (String[] arguments)
    {
        try
        {
            switch (MyIO.getUserInput())
            {
                case "n", "N":
                    System.exit(ZERO);
                    break;
                case "y", "Y":
                    IOController.openMenu(correctInvalidData(deleteInvalidTrees(parseDataToTreeObject(readDataFromTreeCSV()), arguments), arguments));
                    break;
                default:
                    throw new IllegalArgumentException (ERROR_INVALID_USER_INPUT);
            }
        } catch (InputMismatchException e)
        {
            print(e.getMessage());
            MyIO.print(ERROR_INVALID_USER_INPUT, ConsoleColor.RED);
            System.exit(ZERO);
        }

    }
}
