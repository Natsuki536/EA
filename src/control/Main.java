package control;


import model.Tree;
import utility.MyIO;
import utility.TreeCategorized;
import utility.constants.Constants;

import java.util.ArrayList;


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

public class Main implements Constants, TreeCategorized
{
    public static void main (String[] args)
    {
        //TODO: remove not needed method calls!!!
        IOController.openMenu(IOController.correctInvalidData(IOController.deleteInvalidTrees(IOController.parseDataToTreeObject(IOController.readDataFromTreeCSV()))));
        /*MyIO.print(String.valueOf(DECIDUOUS_GENERA.size()));
        MyIO.print(String.valueOf(CONIFEROUS_GENERA.size()));
        MyIO.print(String.valueOf(TOTAL_GENERA.size()));
        MyIO.print(String.valueOf(GroupingAndSortingController.groupBy(IOController.correctInvalidData(IOController.deleteInvalidTrees
        (IOController.parseDataToTreeObject(IOController.readDataFromTreeCSV()))) , Tree::getGenus).keySet().size()));*/
        //ArrayList<Tree> treeArrayList = IOController.correctInvalidData(IOController.deleteInvalidTrees(IOController.parseDataToTreeObject(IOController.readDataFromTreeCSV())));
    }
}
