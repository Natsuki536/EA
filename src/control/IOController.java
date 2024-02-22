package control;


import model.Tree;
import view.ConsoleColor;
import view.MyIO;
import utility.constants.Announcements;
import utility.constants.Constants;
import utility.service.CSVParser;
import utility.service.MenuParser;
import utility.adt.Pair;
import utility.service.Stopwatch;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * <h1>Input-Output-Controller</h1>
 * <p>
 *      This class stores methods that are necessary for displaying various out- and inputs.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-10
 */
public class IOController implements Constants, Announcements
{
    /**
     * @author Hannah Wollenweber
     * This method displays the results of the method that reads the csv file
     * @precondition the called methods are all working correctly
     * @postcondition the data contained in csv file is read and that fact is displayed in console with elapsed time needed and an announcement
     * @return ArrayList<Sting [ ]>
     * @exception FileNotFoundException e file might not be found
     * @throws RuntimeException e
     *
     * */
    public static ArrayList<String[]> readDataFromTreeCSV ()
    {
        Stopwatch stopwatch = new Stopwatch();
        try
        {
            CSVParser csvParser = new CSVParser(Constants.FILE_PATH,
                    Constants.DATA_CSV_DELIMITER);
            MyIO.printLine(MyIO.formatString(
                    ANNOUNCEMENT_DATA_READ + csvParser.getDocumentPath(),
                    ConsoleColor.CYAN));
            ArrayList<String[]> rawDataOutput = csvParser.readData();
            MyIO.printTimeMessageAndSize(
                    ANNOUNCEMENT_DATA_READ_SUCCESSFUL, stopwatch,
                    rawDataOutput);
            return rawDataOutput;
        } catch (FileNotFoundException e)
        {
            MyIO.print(e.getMessage(), ConsoleColor.RED_BRIGHT);
            throw new RuntimeException();
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method displays the results of the method createTreeArrayListFromRawData()
     * @precondition called functions work properly
     * @postcondition the elapsed time of called functions is displayed with announcement
     * @param rawData ArrayList containing StringArrays that represent a line of a csv file
     * @return ArrayList
     * */
    public static ArrayList<Tree> parseDataToTreeObject (ArrayList<String[]> rawData)
    {
        Stopwatch stopwatch = new Stopwatch();
        MyIO.printLine(MyIO.formatString(ANNOUNCEMENT_PARSE_DATA, ConsoleColor.CYAN));
        ArrayList<Tree> outputArrayList = TreeController.createTreeArrayListFromRawData(rawData);
        MyIO.printTimeMessageAndSize(ANNOUNCEMENT_DATA_PARSE_SUCCESSFUL, stopwatch, outputArrayList);
        return outputArrayList;
    }


    /**
     * @author Hannah Wollenweber
     * @precondition the called functions work properly
     * @postcondition The ArrayList is printed out
     * @param arrayListOfArrays ArrayList of StringArrays
     * */
    public static void printRawDataArrayList (ArrayList<String[]> arrayListOfArrays)
    {
        Stopwatch stopwatch = new Stopwatch();
        MyIO.printLine(MyIO.formatString(ANNOUNCEMENT_PRINT_DATA, ConsoleColor.CYAN));
        arrayListOfArrays.forEach((array) -> MyIO.print(Arrays.toString(array)));
        MyIO.printTimeMessageAndSize(ANNOUNCEMENT_PRINT_OF_ARRAYLIST_SUCCESSFUL, stopwatch, arrayListOfArrays);
    }


    /**
     * @author Hannah Wollenweber
     * This method displays the results of the method deleteInvalidTrees()
     * @precondition the called functions work properly
     * @postcondition the elapsed time of called functions is displayed along with an announcement message
     * @param treeArrayList ArrayList of trees
     * @return ArrayList<Trees> return adjusted ArrayList
     * */
    public static ArrayList<Tree> deleteInvalidTrees (ArrayList<Tree> treeArrayList, String[] arguments)
    {
        Stopwatch stopwatch = new Stopwatch();
        MyIO.printLine(MyIO.formatString(ANNOUNCEMENT_DELETE_LINES, ConsoleColor.CYAN));
        ArrayList<Integer> indicesToDelete = DeletionAndCorrectionController.createListOfIndicesToDelete(treeArrayList, arguments);
        ArrayList<Tree> afterDeletion = DeletionAndCorrectionController.removeInvalidLines(treeArrayList, indicesToDelete);
        MyIO.printTimeMessageAndSize(ANNOUNCEMENT_LINES_DELETED, stopwatch, indicesToDelete);
        return afterDeletion;
    }


    /**
     * @author Hannah Wollenweber
     * This method displays the result of the method correctTreeValues()
     * @precondition the called functions work properly
     * @postcondition the elapsed time of called functions is displayed along with an announcement message
     * @param treeArrayList ArrayList of trees
     * @return ArrayList<Trees> return adjusted ArrayList
     * */
    public static ArrayList<Tree> correctInvalidData (ArrayList<Tree> treeArrayList, String[] arguments)
    {
        Stopwatch stopwatch = new Stopwatch();
        MyIO.printLine(MyIO.formatString(ANNOUNCEMENT_ADJUST_DATA, ConsoleColor.CYAN));
        Pair pairOfCountAndTreeArrayList = DeletionAndCorrectionController.correctTreeValues(treeArrayList, arguments);
        int countOfCorrectedTrees = pairOfCountAndTreeArrayList.getValue();
        ArrayList<Tree> afterCorrection = pairOfCountAndTreeArrayList.getTreeArrayList();
        MyIO.printTimeMessageAndNumber(ANNOUNCEMENT_TREES_CORRECTED, stopwatch, countOfCorrectedTrees);
        return afterCorrection;
    }


    /**
     * @author Hannah Wollenweber
     * This method displays a menu that gives the user a number of questions to choose from. The user can choose by typing the according number or exit the menu
     * with 'exit'. If the user does choose a question, the answer will then be displayed and the user can choose another question or exit.
     * @precondition none
     * @postcondition A menu with a query is displayed that the user can use to get answers to various question.
     * */
    public static void openMenu (ArrayList<Tree> treeArrayList)
    {
        MyIO.printLine(MyIO.formatString(ANNOUNCEMENT_OPEN_MENU, ConsoleColor.CYAN));
        MenuParser.createMenu(treeArrayList);
    }
}
