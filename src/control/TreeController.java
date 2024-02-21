package control;


import model.*;
import utility.MyIO;
import utility.constants.Constants;
import utility.constants.DataIndices;


import java.util.ArrayList;


/**
 * <h1>Tree-Controller</h1>
 * <p>
 *     This class stores methods that are necessary for creating trees including filling empty strings.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-10
 */
public abstract class TreeController implements Constants, DataIndices
{
    /**
     * @author Hannah Wollenweber
     * This method creates an ArrayList of Trees from a given ArrayList with StringArrays
     *
     * @precondition The StringArrays contain 12 data fields -> have indices zero through eleven
     * @postcondition An ArrayList of Trees is returned
     *
     * @param rawDataArrayList ArrayList of StringArrays
     * @return treeArrayListOutput
     */
    public static ArrayList<Tree> createTreeArrayListFromRawData (ArrayList<String[]> rawDataArrayList)
    {
        ArrayList<Tree> treeArrayListOutput = new ArrayList<>();

        //iterate through the ArrayList of StringArrays
        for (String[] dataLine : rawDataArrayList)
        {
            //replace empty Strings or Strings with solely space with 0 to be able to parse Stringarray as tree
            fillEmptyString(dataLine);

            //create Tree from StringArray
            Tree currentTree = createObjectFromStringArray(dataLine);
            //add tree if not null
            if (currentTree != null)
            {
                treeArrayListOutput.add(currentTree);
            }
        }
        return treeArrayListOutput;
    }


    /**
     * @author Hannah Wollenweber
     * This method creates tree objects out of a given string array
     *
     * @precondition The given array is a StringArray with 12 fields
     * @postcondition The returned object is a Tree with its attributes.
     *
     * @param rawData StringArray representing a data line that is used to creat tree
     * @return tree|null
     */
    public static Tree createObjectFromStringArray (String[] rawData)
    {
        try
        {
            //int id, Site site, Botanic botanic, TimeData timeData, Metric metric
            return new Tree(
                    Integer.parseInt(rawData[INDEX_ID]),
                    rawData[INDEX_NAME],
                    rawData[INDEX_TREESPECIES],
                    rawData[INDEX_BOTANICAL_SPECIES],
                    rawData[INDEX_TREE_GENUS_GERMAN],
                    rawData[INDEX_TREE_GENUS],
                    Long.parseLong(rawData[INDEX_YEAR_OF_PLANTING]),
                    Integer.parseInt(rawData[INDEX_TREE_STANDING_TIME]),
                    Double.parseDouble(rawData[INDEX_TREETOP_DIAMETER]),
                    Double.parseDouble(rawData[INDEX_TREE_TRUNK_CIRCUMFERENCE]),
                    Double.parseDouble(rawData[INDEX_HEIGHT]),
                    rawData[INDEX_NEIGHBOURHOOD]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e)
        {
            //print information that might help with debugging
            MyIO.print(rawData[DataIndices.INDEX_ID]);
            MyIO.printLine(e.getMessage());
            return null;
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method replaces empty strings or strings with solely a space with a zero.
     * If the array has fewer fields, an ArrayIndexOutOfBoundsException is thrown
     *
     * @precondition The method receives a string array of size 12
     * @postcondition The method returns the given array, now without empty strings
     *
     * @param dataLine StringArray representing a data line
     */
    public static void fillEmptyString (String[] dataLine)
    {
        try
        {
            //iterate through data fields that might cause issues
            for (int i = BOUND_LEFT; i < BOUND_RIGHT; i++)
            {
                //given the String is empty or only contains of a space, it is being replaced with a predefined replacement String
                if (dataLine[i].equals(EMPTY_STRING) || (dataLine[i].equals(STRING_WITH_SPACE)))
                {
                    dataLine[i] = REPLACEMENT_OF_EMPTY_AND_SPACE;
                }
            }
            //catch possible exception
        } catch (ArrayIndexOutOfBoundsException e)
        {
            //print information that might help with debugging
            MyIO.print(dataLine[DataIndices.INDEX_ID]);
            MyIO.printLine(e.getMessage());
        }
    }
}
