package control;


import utility.ConsoleColor;
import utility.MyIO;
import utility.constants.Announcements;
import utility.constants.Constants;


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
        IOController.openMenu(IOController.correctInvalidData(IOController.deleteInvalidTrees(IOController.parseDataToTreeObject(IOController.readDataFromTreeCSV()))));
    }
}
