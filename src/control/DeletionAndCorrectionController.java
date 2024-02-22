package control;


import model.Tree;
import utility.constants.DataIndices;
import view.MyIO;
import utility.constants.Constants;
import utility.adt.Pair;

import java.util.ArrayList;
import java.util.function.ToDoubleFunction;


/**
 * <h1>Deletion and Correction Controller</h1>
 * <p>
 *     This class stores all the necessary methods to correct values of the given dataset or to delete invalid ones. This includes
 *     methods that might validate data. Borders for deeming a value invalid are sorted in the 'Constants' interface and should be
 *     adjusted before using this programm. Further information is given for each method in their respective documentation.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-24-01
 */
public class DeletionAndCorrectionController implements Constants, DataIndices
{
    /**
     * @author Hannah Wollenweber
     * This method creates an ArrayList of indices that are to be deleted since the Tree attributes are unrepairable due to too many
     * missing arguments.
     *
     * @precondition An ArrayList of Trees is given.
     * @postcondition Whenever the values are unbelievable or unrepairable due to too many arguments missing,
     * the index of that Tree is added to an ArrayList of Integers.
     *
     * @param treeArrayList ArrayList of trees
     * @return ArrayList<Integer>
     */
    public static ArrayList<Integer> createListOfIndicesToDelete (ArrayList<Tree> treeArrayList, String[] arguments)
    {
        int maxStandingTime = getArgument(arguments, INDEX_MAX_STANDING_TIME);
        int maxHeight = getArgument(arguments, INDEX_MAX_HEIGHT);
        int maxTrunkCircumference = getArgument(arguments, INDEX_MAX_TRUNK_CIRCUMFERENCE);
        int maxTopDiameter = getArgument(arguments, INDEX_MAX_TOP_DIAMETER);

        ArrayList<Integer> indicesToDelete = new ArrayList<>();
        for (Tree tree : treeArrayList)
        {
            double treetopDiameter = tree.getTopDiameter();
            double height = tree.getHeight();
            double trunkCircumference = tree.getTrunkCircumference();
            int standingTime = tree.getTreeStandingTime();
            long yearOfPlanting = tree.getYearOfPlanting();
            String treeGenus = tree.getGenus();

            if (treetopDiameter == ZERO && trunkCircumference == ZERO && height == ZERO && standingTime == ZERO)
            {
                addIndex(indicesToDelete, treeArrayList, tree);
                continue;
            }

            if (height > maxHeight || height < MIN_HEIGHT)
            {
                addIndex(indicesToDelete, treeArrayList, tree);
                continue;
            }

            if (trunkCircumference > maxTrunkCircumference)
            {
                tree.setCircumference(trunkCircumference / CONVERSION_CENTI_TO_METER);
                continue;
            }
            if (trunkCircumference > maxTrunkCircumference || trunkCircumference < MIN_TRUNK_CIRCUMFERENCE)
            {
                addIndex(indicesToDelete, treeArrayList, tree);
                continue;
            }
            if (treetopDiameter > maxTopDiameter || treetopDiameter < MIN_TREETOP_DIAMETER)
            {
                addIndex(indicesToDelete, treeArrayList, tree);
                continue;
            }

            if (standingTime > maxStandingTime || standingTime < MIN_STANDINGTIME)
            {
                addIndex(indicesToDelete, treeArrayList, tree);
                continue;
            }
            if ((((YEAR_OF_DATASET - yearOfPlanting) > maxStandingTime) && (yearOfPlanting != ZERO)) ||
                    (((YEAR_OF_DATASET - tree.getYearOfPlanting()) < MIN_STANDINGTIME) && (yearOfPlanting != ZERO)))
            {
                addIndex(indicesToDelete, treeArrayList, tree);
                continue;
            }
            if (treeGenus.equals(UNBEKANNT) || treeGenus.equals(ZERO_STRING))
            {
                addIndex(indicesToDelete, treeArrayList, tree);
            }
        }
        return indicesToDelete;
    }


    /**
     * @author Hannah Wollebweber
     * This method adds the indix of a given object of a given ArrayList to another arraylist.
     * This enables you to delete the object later for example.
     *
     * @precondition The first given ArrayList is an ArrayList<Integer>, the second is an ArrayList<Tree> and the object is a Tree
     * @postcondition The updated list with indices is returned
     *
     * @param treeArrayList ArrayList of trees
     * @param tree          current tree
     * @param indices       ArrayList of Integers that represent indices
     * */
    public static void addIndex (ArrayList<Integer> indices, ArrayList<Tree> treeArrayList, Tree tree)
    {
        indices.add(treeArrayList.indexOf(tree));
    }


    /**
     * @author Hannah Wollenweber
     * This method removes Trees with data that cannot be corrected due to missing information
     *
     * @precondition All parameters are passed correctly
     * @postcondition returns ArrayList after deletion of trees
     *
     * @param treeArrayList ArrayList of trees
     * @param indicesToRemove ArrayList containing Integers that represent indices which are supposed to be removed
     * @return treeArrayList
     */
    public static ArrayList<Tree> removeInvalidLines (ArrayList<Tree> treeArrayList, ArrayList<Integer> indicesToRemove)
    {
        int indexCorrection = ZERO;
        for (int index : indicesToRemove)
        {
            try
            {
                treeArrayList.remove(index - indexCorrection);
            } catch (ArrayIndexOutOfBoundsException e)
            {
                MyIO.printLine(e.getMessage());
            } finally
            {
                indexCorrection++;
            }
        }
        return treeArrayList;
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates an average factor for an attribute growth per year over all trees that have a value for that attribute.
     *
     * @precondition an ArrayList of trees is passed
     * @postcondition the factor has been calculated over all trees that have values for the treetop and standing time
     *
     * @param TreeArrayList ArrayList containing trees
     * @param getter getter for the attribute
     * @return double
     */
    private static double calculateAttributeGrowthPerYear (ArrayList<Tree> TreeArrayList, ToDoubleFunction<Tree> getter)
    {
        //initialise values for a factor sum and tree counter; declare averageFactor
        int treeCounter = ZERO;
        double sum = ZERO_DOUBLE;
        double averageFactor;
        for (Tree tree : TreeArrayList)
        {
            //check if factor can be calculated for current tree
            if (getter.applyAsDouble(tree) != ZERO && tree.getTreeStandingTime() != ZERO)
            {
                sum += getter.applyAsDouble(tree) / tree.getTreeStandingTime();
                treeCounter++;
            }
        }
        //calculate and return average factor
        averageFactor = sum / treeCounter;
        return averageFactor;
    }


    /**
     * @author Hannah Wollenweber
     * This method corrects the height or trunk circumference of a given tree.
     *
     * @precondition all passed parameters are valid
     * @postcondition the method has corrected the value that was zero
     *
     * @param tree tree given that is supposed to be corrected
     * @param heightFactor pre-calculated factor based on standing time
     * @param circumferenceFactor pre-calculated factor based on standing time
     */
    private static void correctHeightAndCircumference (Tree tree, double heightFactor, double circumferenceFactor, double height, double trunkCircumference)
    {
        if (trunkCircumference == ZERO)
        {
            //correct factor if zero
            tree.setCircumference(tree.getTreeStandingTime() * circumferenceFactor);
        }
        if (height == ZERO)
        {
            //correct factor if zero
            tree.setHeight(tree.getTreeStandingTime() * heightFactor);
        }
    }


    /**
     * @param treeArrayList ArrayList containing trees
     * @param arguments
     *
     * @return double
     *
     * @author Hannah Wollenweber
     *         This method correct the misisng data from 0 to a plausible value calculated by average factors over the trees (since this is ana
     *         aproximation the genus and species are being ignored). The only values corrected are height and circumference since these are important
     *         for following tasks. The other values are being diregared due to their unimportance.
     * @precondition an ArrayList of trees is passed that has the correct attributes specified in the model
     * @postcondition the tree's attributes have been corrected if empty
     */
    public static Pair correctTreeValues (ArrayList<Tree> treeArrayList, String[] arguments)
    {
        //calculate all factors as they are needed in following method calls
        double heightFactor = calculateAttributeGrowthPerYear(treeArrayList, Tree::getHeight);
        double trunkCircumferencePerYearFactor = calculateAttributeGrowthPerYear(treeArrayList, Tree::getTrunkCircumference);
        double treeTopPerYearFactor = calculateAttributeGrowthPerYear(treeArrayList, Tree::getTopDiameter);

        int correctionCount = ZERO;

        //iterate through the ArrayList of trees
        for (Tree tree : treeArrayList)
        {
            //get number of missing fields
            int numberOfFieldsToCorrect = countMissingArguments(tree);

            //get values of height and trunk circumference as they determine whether the tree has to be corrected
            double currentTrunkCircumference = tree.getTrunkCircumference();
            double currentHeight = tree.getHeight();

            //only if height or trunk circumference are zero the tree has to be corrected
            if (currentTrunkCircumference == ZERO || currentHeight == ZERO)
            {
                //count all trees that are have been corrected
                correctionCount++;
                correctAttributes(numberOfFieldsToCorrect, tree, heightFactor, trunkCircumferencePerYearFactor, treeTopPerYearFactor, currentHeight, currentTrunkCircumference);
            }
        }
        return new Pair(correctionCount, treeArrayList);
    }


    /**
     * @author Hannah Wollenweber
     * This method counts the number of fields that are zero and have to be corrected.
     *
     * @precondition A valid Tree is given.
     * @postcondition A count of fields that are empty is returned
     *
     * @param tree tree of type Tree
     * @return double
     */
    private static int countMissingArguments (Tree tree)
    {
        int counterOfMissingData = ZERO;
        if (tree.getTreeStandingTime() == ZERO)
        {
            counterOfMissingData++;
        }
        if (tree.getTopDiameter() == ZERO_DOUBLE)
        {
            counterOfMissingData++;
        }
        if (tree.getHeight() == ZERO_DOUBLE)
        {
            counterOfMissingData++;
        }
        if (tree.getTopDiameter() == ZERO_DOUBLE)
        {
            counterOfMissingData++;
        }
        return counterOfMissingData;
    }


    /**
     * @author Hannah Wollenweber
     * This method corrects a tree's attributes given the ones important for further calculations are empty
     *
     * @precondition all parameters are passed correctly and previous calculations are valid
     * @postcondition the tree's attributes have been corrected
     *
     * @param numberOfFieldsToCorrect number of fields that are empty
     * @param tree current tree that might be corrected
     * @param heightFactor previously calculated factor of the height divided by standingTime
     * @param trunkCircumferencePerYearFactor previously calculated factor of the trunk circumference divided by standingTime
     * @param treeTopPerYearFactor previously calculated factor of the treetop diameter divided by standingTime
     * @param currentHeight height of the concerning tree before correction
     * @param currentTrunkCircumference trunk circumference of tree before correction
     */
    private static void correctAttributes (int numberOfFieldsToCorrect, Tree tree, double heightFactor, double trunkCircumferencePerYearFactor, double treeTopPerYearFactor, double currentHeight, double currentTrunkCircumference)
    {

        //if only one field is zero, the algorithm to correct it is much simpler
        if (numberOfFieldsToCorrect == ONE || (currentHeight == ZERO && currentTrunkCircumference == ZERO))
        {
            correctHeightAndCircumference(tree, heightFactor, trunkCircumferencePerYearFactor, currentHeight, currentTrunkCircumference);
        } else
        {
            //correct all fields
            correctTime(tree, trunkCircumferencePerYearFactor, heightFactor, treeTopPerYearFactor);
            correctRemainingAttributes(tree, tree.getTreeStandingTime(), heightFactor, treeTopPerYearFactor, trunkCircumferencePerYearFactor);
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method corrects the standing time given it is zero
     *
     * @precondition the necessary parameters are passed and the factors that have been calculated are valid
     * @postcondition the standing time has been calculated and validated given a year of planting is available
     *
     * @param tree current tree
     * @param trunkCircumferencePerYearFactor calculated factor for correction: trunk circumference/standing time
     * @param heightFactor calculated factor for correction: trunk circumference/standing time
     * @param treeTopPerYearFactor calculated factor for correction: treetop diameter/standing time
     */
    private static void correctTime (Tree tree, double trunkCircumferencePerYearFactor, double heightFactor, double treeTopPerYearFactor)
    {
        int standingTime = tree.getTreeStandingTime();

        if (tree.getYearOfPlanting() != ZERO && standingTime == ZERO)
        {
            tree.setTreeStandingTime(validateStandingTime(standingTime, tree.getYearOfPlanting()));
        } else if (standingTime == ZERO)
        {
            tree.setTreeStandingTime(calculateStandingTime(tree, trunkCircumferencePerYearFactor, heightFactor, treeTopPerYearFactor));
        }

    }


    /**
     * @author Hannah Wollenweber
     *This method checks if the standing time is equal to the dataset's year minus the year of planting
     *
     * @precondition all parameters are passed correctly
     * @postcondition the standing time is validated with the help of the year of planting
     *
     * @param standingTime timespan of tree standing at its site
     * @param yearOfPlanting year when the tree was planted
     * @return int
     */
    private static int validateStandingTime (int standingTime, long yearOfPlanting)
    {
        int calculatedStandingTime = (int) calculateStandingTimeWithYearOfPlanting(yearOfPlanting);
        //if standing time is the same as the calculated time, nothing has to be changed
        if (standingTime == calculatedStandingTime)
        {
            return standingTime;
        } else
        {
            //otherwise the calculated time has to be returned
            return calculatedStandingTime;
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates a standing time based on the year of planting and the year of the given dataset
     *
     * @precondition The correct year of the data set is put in the interface 'Constants' and the datatype of the dates is long
     * both dates have to be entered as year only and in accordance to the gregorian calendar.
     * @postcondition A standing time is calculated [in a]
     *
     * @param yearOfPlanting year the tree was planted
     * @return long
     */
    private static long calculateStandingTimeWithYearOfPlanting (long yearOfPlanting)
    {
        return (YEAR_OF_DATASET - yearOfPlanting);
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates a standing time for a passed tree
     *
     * @precondition all parameters are passed correctly
     * @postcondition returns calculated standing time
     *
     * @param tree current tree
     * @param circumferenceFactor calculated factor for correction: trunk circumference/standing time
     * @param heightFactor calculated factor for correction: height/standing time
     * @param diameterFactor calculated factor for correction: top diameter/standing time
     * @return int standing time
     * */
    private static int calculateStandingTime (Tree tree, double circumferenceFactor, double heightFactor, double diameterFactor)
    {
        //get initial value for standing time
        int standingTime = tree.getTreeStandingTime();
        while (standingTime == ZERO)
        {
            if (tree.getHeight() != ZERO)
            {
                //calculate a standing time using the height factor
                standingTime = calculateStandingTimeWithFactor(tree, heightFactor, Tree::getHeight);

                //escape if a standingTime has been calculated to check if standingTime still is zero
                continue;
            }
            if (tree.getTrunkCircumference() != ZERO)
            {
                //calculate a standing time using the trunk circumference factor
                standingTime = calculateStandingTimeWithFactor(tree, circumferenceFactor, Tree::getTrunkCircumference);

                //escape if a standingTime has been calculated to check if standingTime still is zero
                continue;
            }
            if (tree.getTopDiameter() != ZERO)
            {
                //calculate a standing time using the treetop diameter factor
                standingTime = calculateStandingTimeWithFactor(tree, diameterFactor, Tree::getTopDiameter);

                //no escape needed since last call of while loop
            }
        }
        //return a standing time that now is not zero anymore
        return standingTime;
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates a standing time for a passed tree based on pre-calculated average factors
     *
     * @precondition a tree and a getter are passed
     * @postcondition a standing time is calculated and returned
     *
     * @param tree current tree
     * @param factor pre-calculated factor
     * @param getter appropriate getter
     * @return int - the standing time
     */
    private static int calculateStandingTimeWithFactor (Tree tree, double factor, ToDoubleFunction<Tree> getter)
    {
        return ((int) Math.ceil(getter.applyAsDouble(tree) / factor));
    }


    /**
     * @author Hannah Wollenweber
     * This method corrects the remaining attributes based on the standing time.
     *
     * @precondition the standing time is valid and not zero, the factors have been calculated correctly
     * @postcondition the height, treetopDiameter and trunkCircumference have been correctly if needed
     *
     * @param tree current tree
     * @param standingTime the tree's standing time
     * @param heightFactor previously calculated factor height/year
     * @param treeTopFactor previously calculated factor treeTopDiameter/year
     * @param trunkFactor previously calculated factor trunkCircumference/year
     */
    //TODO add args
    private static void correctRemainingAttributes (Tree tree, int standingTime, double heightFactor, double treeTopFactor, double trunkFactor)
    {
        //correct height if value is zero
        if (tree.getHeight() == ZERO_DOUBLE)
        {
            //calculate a value with standing time and a pre-calculated factor
            double newValue = calculateNewValue(heightFactor, standingTime, MAX_HEIGHT, CORRECTION_HEIGHT);

            tree.setHeight(newValue);
        }
        //correct trunk circumference if value is zero
        if (tree.getTrunkCircumference() == ZERO_DOUBLE)
        {
            //calculate a value with standing time and a pre-calculated factor
            double newValue = calculateNewValue(trunkFactor, standingTime, MAX_TRUNK_CIRCUMFERENCE, CORRECTION_FOR_TRUNK_CIRCUMFERENCE);

            tree.setCircumference(newValue);
        }
        //correct treetop diameter if value is zero
        if (tree.getTopDiameter() == ZERO_DOUBLE)
        {
            //calculate a value with standing time and a pre-calculated factor
            double newValue = calculateNewValue(treeTopFactor, standingTime, MAX_TREETOP_DIAMETER, CORRECTION_FOR_TREETOP_DIAMETER);

            tree.setTopDiameter(newValue);
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates a new value for an attribute with the help of a pre-calculated factor
     *
     * @precondition the passed parameters are all valid
     * @postcondition a plausible new value is calculated
     *
     * @param factor factor for correcting the attribute
     * @param standingTime the trees standing time
     * @param maxValue max value of the attribute
     * @param correctionValue correction value if corrected value exceeds max value
     * @return new value as double
     */
    private static double calculateNewValue (double factor, int standingTime, double maxValue, double correctionValue)
    {
        //calculate a value with standing time and a pre-calculated factor
        double newValue = factor + standingTime;

        //set the value to a validated value
        newValue = validateNewValue(newValue, maxValue, correctionValue);

        return newValue;
    }


    /**
     * @author Hannah Wollenweber
     * This method checks if the new with a factor calculated value is greater than the max value and if so sets it to the maximum
     * value minus a correctio which ensure it can't become a superlative tree such as biggest tree.
     *
     * @precondition all parameters are entered correctly
     * @postcondition a valid new value is returned
     *
     * @param newValue with correcting factor calculated new value
     * @param maxValue pre-defined max value for attribute
     * @param correctionValue pre-defined correction value
     * @return double validated new value
     */
    private static double validateNewValue (double newValue, double maxValue, double correctionValue)
    {
        if (newValue > maxValue)
        {
            return (maxValue - correctionValue);
        } else
        {
            return newValue;
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method fetches the desired argument out of the args STringArray
     *
     * @precondition arguments have been passed
     * @postcondition the correct argument is returned
     *
     * @param arguments arguments passed in command line
     * @param index index of desired argument
     * @return int value of argument
     */
    private static int getArgument (String[] arguments, byte index)
    {
        return Integer.parseInt(arguments[index]);
    }
}