package control;


import model.Tree;
import utility.comparators.TreeStandingTimeComparator;
import utility.comparators.TreeHeightComparator;
import utility.comparators.TreeTrunkCircumferenceComparator;
import utility.comparators.TreeCrownDiameterComparator;
import view.ConsoleColor;
import view.MyIO;
import utility.constants.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;

import static control.CarbonDataController.returnBoundCarbon;


/**
 * <h1>Grouping and sorting controller</h1>
 * <p>
 *     This class stores all methods needed for grouping the Tree ArrayList a certain way or for sorting the collection in order
 *     to answer specific questions such as in what neighbourhood the oldest tree stands.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-02-17
 */
public class GroupingAndSortingController implements Constants
{
    /**
     * @author Hannah_Wollenweber
     * This method reorganises a given ArrayList of trees in a Hashmap grouped by a grouping
     * @precondition An ArrayList of trees and a getter is given
     * @postcondition The grouped Hashmap is returned
     * @param treeArrayList ArrayList containing trees
     * @param getter getter method that returns the value of a certain tree attribute
     * @return Map<String, ArrayList < Tree>> grouped Hashmaps with an ArrayList of trees as value
     */
    public static <T> Map<String, ArrayList<Tree>> groupBy (ArrayList<Tree> treeArrayList, Function<Tree, T> getter)
    {
        //Hashmap is initialised
        Map<String, ArrayList<Tree>> groupedMap = new HashMap<>();
        //The arrayList is iterated through with the help of a for-each-loop
        for (Tree tree : treeArrayList)
        {
            //The grouping is specified
            String grouping = getter.apply(tree).toString();

            //the tree is sorted into the Hashmap
            addToMap(tree, groupedMap, grouping);
        }
        return groupedMap;
    }


    /**
     * @author Hannah Wollenweber
     * This method re-organised a given treeArrayList in a Hashmap of Hashmaps so that the trees are first grouped by neighbourhood and then by species.
     * @precondition An arraylist of trees with the attributes species and neighbourhood are given
     * @postcondition The arraylist is re-organised and a Hashmap of the form Map<String, Map<String, ArrayList<Tree>>> is returned.
     * @param treeArrayList ArrayList containing trees
     * @return Map<String, Map < String, ArrayList < Tree>>> neighbourhoodSpeciesMap
     */
    public static Map<String, Map<String, ArrayList<Tree>>> groupTreesByNeighbourhoodAndSpecies (ArrayList<Tree> treeArrayList)
    {
        Map<String, Map<String, ArrayList<Tree>>> neighbourhoodSpeciesMap = new HashMap<>();
        //The given treeArrayList is being iterated through with a for-each-loop
        for (Tree tree : treeArrayList)
        {
            //the two groupings are being specified
            String neighbourhood = tree.getSite().getNeighbourhood();
            String species = tree.getBotanic().getTreeSpecies();

            //The tree is added to the correct ArrayList based on the neighbourhood and species
            addToMap(tree, addToMapComplex(neighbourhoodSpeciesMap, neighbourhood).get(neighbourhood), species);
        }
        return neighbourhoodSpeciesMap;
    }


    /**
     * @author Hannah Wollenweber
     * This method checks, if a certain attribute to group by is already added to the Hashmap, if not, it is added.
     * Then it add the given tree to the according group and returns the Hashmap.
     * @precondition A tree, a Hashmap of the type Map<String, Map<String, ArrayList<Tree>>> and a String to group by is given.
     * @postcondition The adjusted Hashmap is returned.
     * @param map Hashmap the tree is to be sorted in
     * @param grouping argument that will function as key in Hashmap - this is the attribute the tree is grouped by
     * @return Map<String, Map < ArrayList < Tree>>> map
     */
    private static Map<String, Map<String, ArrayList<Tree>>> addToMapComplex (Map<String, Map<String, ArrayList<Tree>>> map, String grouping)
    {
        //Given the grouping String is not in the Hashmap yet, it is being added with a HashMap as value
        if (!map.containsKey(grouping))
        {
            map.put(grouping, new HashMap<>());
        }
        //return grouped map
        return map;
    }


    /**
     * @author Hannah Wollenweber
     * This method checks, if a certain attribute to group by is already added to the Hashmap, if not, it is added.
     * Then it add the given tree to the according group and returns the Hashmap.
     * @precondition A tree, a Hashmap of the type Map<String, ArrayList<Tree>> and a String to group by is given.
     * @postcondition The adjusted Hashmap is returned.
     * @param tree tree of Tree type
     * @param map Hashmap the tree is to be sorted in
     * @param grouping argument that will function as key in Hashmap - this is the attribute the tree is grouped by
     * @return Map<String, ArrayList < Tree>>
     */
    private static Map<String, ArrayList<Tree>> addToMap (Tree tree, Map<String, ArrayList<Tree>> map, String grouping)
    {
        //Given the grouping String is not in the Hashmap yet, it is being added with an ArrayList as value
        if (!map.containsKey(grouping))
        {
            map.put(grouping, new ArrayList<>());
        }
        //the given tree is added to the according Key-ArrayList
        map.get(grouping).add(tree);
        return map;
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the neighbourhood with the most species when given a Hashmap containing Hashmaps with ArrayLists of Trees. With the first key being the
     * neighbourhood and the second being the species.
     * @precondition A tree, a Hashmap of the type Map<String, Map<String, ArrayList<Tree>>> and a String to group by is given.
     * @postcondition
     * @param neighbourhoodSpeciesMap Hashmap that represents trees being sorted first by neighbourhood and then by species
     */
    public static void findNeighbourhoodWithMostSpecies (Map<String, Map<String, ArrayList<Tree>>> neighbourhoodSpeciesMap)
    {
        //set initial values for the neighbourhood and the largest number of species
        int largestNumberOfSpecies = ZERO;
        String neighbourhoodWithMostSpecies = NEIGHBOURHOOD;

        //iterate through the Hashmap that is first grouped by neighbourhood and in these neighbourhood grouped by species
        for (Map.Entry<String, Map<String, ArrayList<Tree>>> entry : neighbourhoodSpeciesMap.entrySet())
        {
            //set current values for neighbourhood and number of species in neighbourhood
            String neighbourhood = entry.getKey();
            int numberOfSpecies = entry.getValue().size();

            //replace initial values if current values are greater
            if (numberOfSpecies > largestNumberOfSpecies)
            {
                largestNumberOfSpecies = numberOfSpecies;
                neighbourhoodWithMostSpecies = neighbourhood;
            }
        }
        //print out the results
        MyIO.print(NEIGHBOURHOOD_WITH_MOST_SPECIES + neighbourhoodWithMostSpecies + SPECIES + largestNumberOfSpecies, ConsoleColor.GREEN_BRIGHT);
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the neighbourhood with the highest tree.
     * @precondition A Hashmap of the type Map<String, ArrayList<Tree>> is given.
     * @postcondition The neighbourhood with the highest tree is printed out
     * @param neighbourhoodMap Hashmap that represents trees being sorted by their neighbourhood
     */
    public static void findNeighbourhoodWithTallestTree (Map<String, ArrayList<Tree>> neighbourhoodMap)
    {
        //set initial value for neighbourhood with the highest tree
        String neighbourhoodWithTallestTree = NEIGHBOURHOOD;
        double heightOfTallestTree = ZERO_DOUBLE;

        //iterate through Hashmap and look for highest tree in each neighbourhood
        for (Map.Entry<String, ArrayList<Tree>> entry : neighbourhoodMap.entrySet())
        {
            //set current values
            String neighbourhood = entry.getKey();
            ArrayList<Tree> treesInNeighbourhood = entry.getValue();

            //find the highest tree in given treesInNeighbourhood
            Tree tallestTreeInNeighbourhood = findTallestTree(treesInNeighbourhood);

            //replace initial values if current values are greater
            if (tallestTreeInNeighbourhood.getHeight() > heightOfTallestTree)
            {
                heightOfTallestTree = tallestTreeInNeighbourhood.getHeight();
                neighbourhoodWithTallestTree = neighbourhood;
            }
        }
        //print out the results
        MyIO.print(NEIGHBOURHOOD_WITH_TALLEST_TREE + neighbourhoodWithTallestTree + STRING_WITH_SPACE + HEIGHT_OF_TALLEST_TREE + heightOfTallestTree, ConsoleColor.GREEN_BRIGHT);
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the highest tree for a given the neighbourhood
     * @precondition An ArrayList of trees representing the concerning neighbourhood is given
     * @postcondition The height of the highest tree is returned
     * @param neighbourhood ArrayList that represents all trees in a certain neighbourhood
     * @return double tallestTree
     */
    private static Tree findTallestTree (ArrayList<Tree> neighbourhood)
    {
        //initial value for height of the highest tree is set
        Tree tallestTree = neighbourhood.get(ZERO);
        //the neighbourhood ArrayList is iterated through
        for (int i = ONE; i < neighbourhood.size(); i++)
        {
            Tree tree = neighbourhood.get(i);

            TreeHeightComparator treeHeightComparator = new TreeHeightComparator();
            int compareResult = treeHeightComparator.compare(tree, tallestTree);
            //replace initial value if current value is greater
            if (compareResult == ONE)
            {
                tallestTree = tree;
            }
        }
        //return height of the highest tree
        return tallestTree;
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the group with the most trees
     * @precondition An ArrayList of trees representing the concerning group is given
     * @postcondition The neighbourhood with the most trees is returned
     * @param treesGrouped Hashmap that contains trees grouped by a certain attribute
     */
    public static void findGroupWithMostTrees (Map<String, ArrayList<Tree>> treesGrouped, String grouping)
    {
        //initial value for height of tree number in neighbourhood
        int numberOfTrees = ZERO;
        String group = grouping;
        //the group ArrayList is iterated through
        for (Map.Entry<String, ArrayList<Tree>> entry : treesGrouped.entrySet())
        {
            //get current group
            String currentGroup = entry.getKey();

            //get number of trees in current neighbourhood
            int currentNumberOfTrees = entry.getValue().size();

            //replace initial value if current value is greater
            if (currentNumberOfTrees > numberOfTrees)
            {
                numberOfTrees = currentNumberOfTrees;
                group = currentGroup;
            }
        }
        MyIO.print(String.format(GROUPING_WITH_MOST_TREES, grouping, group, numberOfTrees), ConsoleColor.GREEN_BRIGHT);
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the superlative of a tree, e.g. the oldest tree
     * @precondition
     * @postcondition
     * @param treeArrayList ArrayList containing trees
     * @param getter getter method that returns the value of a certain tree attribute
     * @return superlative tree
     */
    public static Tree findSuperlative (ArrayList<Tree> treeArrayList, ToDoubleFunction<Tree> getter)
    {
        //set initial values for the comparison
        double maximum = ZERO_DOUBLE;
        Tree superlativeTree = null;

        //iterate through the ArrayList
        for (Tree tree : treeArrayList)
        {
            //given the current tree is greater than the initial value, it is set as the object to compare to
            if (getter.applyAsDouble(tree) > maximum)
            {
                maximum = getter.applyAsDouble(tree);
                superlativeTree = tree;
            }
        }
        //the tree with the greatest searched attribute is returned
        return superlativeTree;
    }


    /**
     * @author Hannah Wollenweber
     * This method prints the given superlative tree
     * @precondition A tree is given
     * @postcondition attributes are being printed out
     * @param tree tree of Tree type
     */
    public static void printSuperlativeTree (Tree tree)
    {
        MyIO.print(tree.toString(), ConsoleColor.GREEN_BRIGHT);
    }


    /**
     * @author Hannah Wollenweber
     * This calculates the Average value of a Tree attribute over an ArrayList of trees
     * @precondition A tree ArrayList is given
     * @postcondition An average value is calculated
     * @param treeArrayList ArrayList containing trees
     * @param getter getter method that returns the value of a certain tree attribute
     * @return void
     */
    public static double calculateAverage (ArrayList<Tree> treeArrayList, ToDoubleFunction<Tree> getter)
    {
        //set initial values
        double sum = ZERO_DOUBLE;
        int count = ZERO;

        //iterate through ArrayList
        for (Tree tree : treeArrayList)
        {
            sum += getter.applyAsDouble(tree);
            count++;
        }
        //return average
        return (sum / count);
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the average height of each genus and prints out the genus with the highest average
     * @precondition A Hashmap is given
     * @postcondition The maximum average is printed out
     * @param treesByGenus Hashmap that with genus as key and an ArrayList with all trees of that genus
     */
    public static void findAverageHeightOfGenus (Map<String, ArrayList<Tree>> treesByGenus)
    {
        //set initial values
        double maxAverage = ZERO_DOUBLE;
        String genus = GROUP_BY_GENUS;

        //iterate through Hashmap
        for (Map.Entry<String, ArrayList<Tree>> entry : treesByGenus.entrySet())
        {
            //get values of current key
            double currentAverage = calculateAverage(entry.getValue(), Tree::getHeight);
            String currentGenus = entry.getKey();

            //find maximum
            if (currentAverage > maxAverage)
            {
                maxAverage = currentAverage;
                genus = currentGenus;
            }
        }
        MyIO.print(String.format(AVERAGE_HEIGHT, genus, maxAverage), ConsoleColor.GREEN_BRIGHT);
    }


    /**
     * @author Hannah Wollenweber
     * This method finds the average circumference of each genus and prints out the genus with the highest average
     * @precondition A Hashmap is given
     * @postcondition The maximum average is printed out
     * @param treesByGenus Hashmap that with genus as key and an ArrayList with all trees of that genus
     */
    public static void findAverageCircumferenceOfGenus (Map<String, ArrayList<Tree>> treesByGenus)
    {
        //set initial values
        double maxAverage = ZERO_DOUBLE;
        String genus = GROUP_BY_GENUS;

        //iterate through Hashmap
        for (Map.Entry<String, ArrayList<Tree>> entry : treesByGenus.entrySet())
        {
            //get values of current key
            double currentAverage = calculateAverage(entry.getValue(), Tree::getTrunkCircumference);
            String currentGenus = entry.getKey();
            //find maximum
            if (currentAverage > maxAverage)
            {
                maxAverage = currentAverage;
                genus = currentGenus;
            }
        }
        MyIO.print(String.format(AVERAGE_CIRCUMFERENCE, genus, maxAverage), ConsoleColor.GREEN_BRIGHT);
    }


    /**
     * @author Hannah Wollenweber
     * This method calculated the carbon bound by trees in Berlin
     *
     * @precondition all parameters passes are valid and all called functions work properly
     * @postcondition the sum of carbon bound in Berlin is returned
     *
     * @param treeArrayList ArrayList of trees is Berlin
     * @return the sum of all carbon bound by trees in Berlin
     */
    public static long calculateBoundCarbon (ArrayList<Tree> treeArrayList)
    {
        long sumCarbon = ZERO;
        for (Tree tree : treeArrayList)
        {
            sumCarbon += returnBoundCarbon(tree);
        }
        return sumCarbon;
    }


    public static void findMostCarbonBoundInGrouping (Map<String, ArrayList<Tree>> treesGrouped, String grouping)
    {
        //initial value for height of tree number in neighbourhood
        long mostCarbonBound = ZERO;
        String group = grouping;
        //the group ArrayList is iterated through
        for (Map.Entry<String, ArrayList<Tree>> entry : treesGrouped.entrySet())
        {
            //get current group
            String currentGroup = entry.getKey();

            //get number of trees in current neighbourhood
            long carbonBoundInGroup = calculateBoundCarbon(entry.getValue());

            //replace initial value if current value is greater
            if (carbonBoundInGroup > mostCarbonBound)
            {
                mostCarbonBound = carbonBoundInGroup;
                group = currentGroup;
            }
        }
        MyIO.print(String.format(GROUPING_WITH_MOST_BOUND_CARBON, grouping, group, mostCarbonBound), ConsoleColor.GREEN_BRIGHT);
    }
}