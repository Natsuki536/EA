package control;


import model.Tree;
import view.MyIO;
import utility.TreeCarbonData;
import utility.TreesCategorized;
import utility.constants.Announcements;
import utility.constants.Constants;

import java.util.InputMismatchException;


/**
 * <h1>Carbon Data Controller</h1>
 * <p>
 *     This class stores all the necessary methods to calculate the bound carbon of a tree.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-20-02
 */
public class CarbonDataController implements TreesCategorized, Constants, Announcements, TreeCarbonData
{
    /**
     * @author Hannah Wollenweber
     * This method return the bound carbon of a tree
     *
     * @precondition all coniferous and deciduous tree genera are implemented in according lists in the TreeCategorized interface,
     * a valid tree is passed
     * @postcondition The bound carbon of a tree is calculated based on their foliage habit
     *
     * @param tree tree whose bound carbon is to be calculated
     * @return double value of bound carbon
     */
    public static int returnBoundCarbon (Tree tree)
    {
        String foliageHabit = checkFoliageHabit(tree);
        int boundCarbon = ZERO;
        try
        {
            //use acording lookuptable
            switch (foliageHabit)
            {
                case (CONIFEROUS):
                    //use spruce to not over-calculate
                    boundCarbon = calculateBoundCarbon(tree, spruceCarbonByHeightAndDiameter);
                    break;
                case (DECIDUOUS):
                    //use oak to not over-calculate
                    boundCarbon = calculateBoundCarbon(tree, oakCarbonByHeightAndDiameter);
                    break;
            }
        } catch (InputMismatchException e)
        {
            MyIO.print(INVALID_INPUT);
        }
        return boundCarbon;
    }


    /**
     * @author Hananh Wollenweber
     * This method checks what foliage habit a given tree has
     *
     * @precondition all genera of the dataset are seperated in according lists representing a trees foliage habit
     * @postcondition the trees foliage habit is returned
     *
     * @param tree tree to check
     * @return String with tree's foliage habit
     */
    private static String checkFoliageHabit (Tree tree)
    {
        //check whether given tree is coniferous (inside the acording list)
        boolean isConiferous = CONIFEROUS_GENERA.contains(tree.getGenus());
        if (isConiferous)
        {
            return CONIFEROUS;
        } else
        {
            return DECIDUOUS;
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates the bound carbon of a given tree based on their foliage habit
     *
     * @precondition all genera of the dataset are seperated in according lists representing a trees foliage habit
     * @postcondition the calculated bound carbon is returned
     *
     * @param tree passed tree
     * @param lookuptable lookup table that is in accordance with tree's foliage habit
     * @return the bound carbon as int
     * @exception ArrayIndexOutOfBoundsException exception thrown
     */

    private static int calculateBoundCarbon (Tree tree, int[][] lookuptable)
    {
        //get height and apply index correction since table starts at 6 meters with index zero
        int height = ((int) Math.round(tree.getHeight())) + HEIGHT_INDEX_CORRECTION;
        //calculate trunk diameter by dividing the circumference by pi and apply index correction since start at 7cm
        int trunkDiameter = ((int) Math.round(tree.getTrunkCircumference()/Math.PI)) + DIAMETER_INDEX_CORRECTION;
        //declare size of second dimension Array
        int sizeSecondDimension;

        //if height or trunk diameter
        if (height < ZERO || trunkDiameter < ZERO)
        {
            return ZERO;
        }
        //calculate the size of 2nd dimension Array
        sizeSecondDimension = calculateSecondDimensionSize(lookuptable, height);

        //if the tree's height or the tree's trunk diameter exceed the table the max indices have to be used
        if (height >= MAX_INDEX_HEIGHT || trunkDiameter >= MAX_INDEX_DIAMETER)
        {
            try
            {
                //return max value
                return lookuptable[MAX_INDEX_HEIGHT][MAX_INDEX_DIAMETER];
            } catch (ArrayIndexOutOfBoundsException e)
            {
                MyIO.print(e.getMessage());
                return ERROR_NEGATIVE_ONE;
            }
        } else
        {
            try
            {
                //check that diameter doesn't exceed lookuptable
                if (trunkDiameter < sizeSecondDimension)
                {
                    //return value at indices
                    return lookuptable[height][trunkDiameter];
                } else
                {
                    //return maximum value possible for height if diameter does exceed
                    return lookuptable[height][sizeSecondDimension];
                }
            } catch (ArrayIndexOutOfBoundsException e)
            {
                MyIO.print(e.getMessage()); //TODO: check exception handling
                return ERROR_NEGATIVE_ONE;
            }
        }
    }


    /**
     * @author Hannah Wollenweber
     * This method calculates the size of the second dimension of the lookuptable for a given first dimension
     *
     * @precondition the height is within the bounds of the first dimension of lookup table
     * @postcondition size of second dimension is returned
     *
     * @param lookuptable lookup table used
     * @param height height of the tree, used to get to second dimension
     * @return size of second dimension as int
     *
     * @exception ArrayIndexOutOfBoundsException exception that might be thrown when array is being accessed
     */
    private static int calculateSecondDimensionSize (int[][] lookuptable, int height)
    {
        //check that height does not exceed max possible height for lookup table
        if (height < MAX_INDEX_HEIGHT)
        {
            try
            {
                //return size of the 2nd dimension Array
                return lookuptable[height].length + NEGATIVE_ONE;
            } catch (ArrayIndexOutOfBoundsException e)
            {
                MyIO.print(e.getMessage());
                return ERROR_NEGATIVE_ONE;
            }
        } else
        {
            try
            {
                //return max size if height does exceed max height
                return lookuptable[MAX_INDEX_HEIGHT].length + NEGATIVE_ONE;
            } catch (ArrayIndexOutOfBoundsException e)
            {
                MyIO.print(e.getMessage());
                return ERROR_NEGATIVE_ONE;
            }
        }
    }
}
