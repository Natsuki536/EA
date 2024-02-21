package control;


import view.ConsoleColor;
import view.MyIO;
import utility.constants.Announcements;
import utility.constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * <h1>Main</h1>
 * <p>
 *      This class contains the method calls for running the whole programm.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-10
 */

public class Main implements Constants, Announcements
{
    public static void main (String[] args)
    {
        MyIO.print(GREETING, ConsoleColor.BLUE_BRIGHT);
        MainController.validateArguments(args);
        MainController.printMenu(args);
    }
}
