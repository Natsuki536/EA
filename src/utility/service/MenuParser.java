package utility.service;


import control.GroupingAndSortingController;
import utility.ConsoleColor;
import utility.MyIO;
import utility.constants.Announcements;
import utility.constants.Constants;
import utility.constants.MenuConstants;
import model.Tree;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * <h1>Menu-Parser</h1>
 * <p>
 *     This class stores the necessary methods for creating a menu for the programm that enables the user to choose from various question
 *     that they might want answered. The query with instructions is displayed followed by the numbered questions. The user may choose a
 *     question by typing the according number or exit by typing 'exit'. After every choice the user is prompted again until they exit the
 *     programm. In that case the programm 'says' goodbye.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-02-17
 */
public class MenuParser implements MenuConstants, Announcements, Constants
{

    /**
     * @author Hannah Wollenweber
     * This method displays a menu with questions to choose from that are numbered. The user then can choose one question by typing in the according number,
     * which leads to the answer being displayed followed by the query and the choices again. If the user wishes to exit, they can do so by typing 'exit'.
     * @precondition none
     * @postcondition The menu is displayed, the user can make a choice as well as exit the menu.
     * @throws InputMismatchException e
     * */
    public static void createMenu (ArrayList<Tree> treeArrayList)
    {
        //TODO: Check output and make it prettier!!!
        //ArrayList<Tree> treeArrayList = IOController.deleteInvalidTrees(IOController.parseDataToTreeObject(IOController.readDataFromTreeCSV()));

        //declare and instantiate a scanner for the user input
        Scanner scanner = new Scanner(System.in);

        //boolean for making sure the menu runs as long as the user doesn't exit
        boolean running = true;

        //print menu with instruction and choices
        printMenu();
        while (running)
        {
            //get user's choice
            String choice = scanner.nextLine();
            try
            {
                //display answers based on user's choice
                switch (choice)
                {
                    case (CHOICE_1):
                        GroupingAndSortingController.findGroupWithMostTrees(GroupingAndSortingController.groupBy(treeArrayList, Tree::getNeighbourhood), NEIGHBOURHOOD);
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_2):
                        GroupingAndSortingController.findNeighbourhoodWithTallestTree(GroupingAndSortingController.groupBy(treeArrayList, Tree::getNeighbourhood));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_3):
                        GroupingAndSortingController.printSuperlativeTree(GroupingAndSortingController.findSuperlative(treeArrayList, Tree::getTrunkCircumference));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_4):
                        GroupingAndSortingController.printSuperlativeTree(GroupingAndSortingController.findSuperlative(treeArrayList, Tree::getTopDiameter));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_5):
                        GroupingAndSortingController.printSuperlativeTree(GroupingAndSortingController.findSuperlative(treeArrayList, Tree::getTreeStandingTime));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_6):
                        MyIO.print(String.valueOf(GroupingAndSortingController.groupBy(treeArrayList, Tree::getSpecies).size()));
                        printMenu();
                        break;
                    case (CHOICE_7):
                        MyIO.print(String.valueOf(GroupingAndSortingController.groupBy(treeArrayList, Tree::getGenus).size()));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_8):
                        GroupingAndSortingController.findGroupWithMostTrees(GroupingAndSortingController.groupBy(treeArrayList, Tree::getGenus), GROUP_BY_GENUS);
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_9):
                        GroupingAndSortingController.findNeighbourhoodWithMostSpecies(GroupingAndSortingController.groupTreesByNeighbourhoodAndSpecies(treeArrayList));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_10):
                        GroupingAndSortingController.findAverageHeightOfGenus(GroupingAndSortingController.groupBy(treeArrayList, Tree::getGenus));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_11):
                        GroupingAndSortingController.findAverageCircumferenceOfGenus(GroupingAndSortingController.groupBy(treeArrayList, Tree::getGenus));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_12): //TODO: Implement functions for 12-14!!
                        MyIO.print(BERLIN_BOUND_CARBON + String.valueOf(GroupingAndSortingController.calculateBoundCarbon(treeArrayList)));
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_13):
                        GroupingAndSortingController.findMostCarbonBoundInGrouping(GroupingAndSortingController.groupBy(treeArrayList, Tree::getNeighbourhood), NEIGHBOURHOOD);
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (CHOICE_14):
                        GroupingAndSortingController.findMostCarbonBoundInGrouping(GroupingAndSortingController.groupBy(treeArrayList, Tree::getGenus), GROUP_BY_GENUS);
                        MyIO.print(NEW_LINE);
                        printMenu();
                        break;
                    case (EXIT):
                        MyIO.print(BYE);
                        running = false;
                        break;
                }
            } catch (InputMismatchException e)
            {
                MyIO.print(INVALID_INPUT);
            }
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method displays a menu with questions to choose from that are numbered.
     * @precondition none
     * @postcondition The menu is displayed
     * */
    private static void printMenu ()
    {
        MyIO.printLine(MyIO.formatString(QUERY, ConsoleColor.CYAN));
        MyIO.print(CHOICES);
    }
}
