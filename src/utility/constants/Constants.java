package utility.constants;
//TODO: Seperate all constants by use-case!!!!!


/**
 * <h1>Constants</h1>
 * <p>
 *     This interfaces stores constants that are needed in the programm
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-10
 */
public interface Constants
{
    //Constants for stopwatch
    long CONVERSION_NANO_TO_MILLI = 1_000_000l;

    //Constants for replacing empty strings & strings with space
    String EMPTY_STRING = "";
    String STRING_WITH_SPACE = " ";
    String REPLACEMENT_OF_EMPTY_AND_SPACE = "0";
    String NEW_LINE = "\n";
    int BOUND_LEFT = 3;
    int BOUND_RIGHT = 12;

    //Constants for CSV-Reader and counting method
    String FILE_PATH = "./src/resources/Baumkataster_Berlin_orginal.csv";
    String DATA_CSV_DELIMITER = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    int LIMIT_FOR_SPLITTING = -1;
    int SEMICOLONS_PER_LINE = 11;
    char STRING_TO_SEARCH = ';';
    char CONDITION_TO_NOT_COUNT = '"';
    int ZERO = 0;
    double ZERO_DOUBLE = 0d;

    //constants for believable data
    int MAX_HEIGHT = 50;
    int MIN_HEIGHT = 0;
    Double CORRECTION_HEIGHT = 10d;
    Double MAX_TRUNK_CIRCUMFERENCE = 800d;
    Double MIN_TRUNK_CIRCUMFERENCE = 0.0;
    Double CORRECTION_FOR_TRUNK_CIRCUMFERENCE = 50d;
    int MAX_TREE_AGE = 1000;
    int MIN_TREE_AGE = 0;
    Double MAX_TREETOP_DIAMETER = 25d;
    Double MIN_TREETOP_DIAMETER = 0.0;
    Double CORRECTION_FOR_TREETOP_DIAMETER = 5d;
    Double CONVERSION_CENTI_TO_METER = 100d;
    String UNBEKANNT = "UNBEKANNT";
    String ZERO_STRING = "0";

    //Strings for grouping trees
    String GROUP_BY_GENUS = "Genus: ";
    String GROUP_BY_SPECIES = "Species: ";
    String NEIGHBOURHOOD = "Neighbourhood: ";
    String SPECIES = ", Number of Species: ";
    String HEIGHT_OF_TALLEST_TREE = "Height of tallest tree: ";
    String NUMBER_OF_TREES = " Number of trees: ";
    String TREE = "Tree: ";
    String ID = "ID: ";
    String TREETOP = "Treetop diameter: ";
    String TRUNK = "Trunk circumference: ";
    String STANDINGTIME = "Standing time: ";
    String AVERAGE_HEIGHT = ", Average height: ";
    String AVERAGE_CIRCUMFERENCE = ", Average circumference: ";



    //Constants for correcting
    int ONE = 1;
    int YEAR_OF_DATASET = 2023;


    //Constants for seperating foliage habit
    String CONIFEROUS = "coniferous";
    String DECIDUOUS = "deciduous";
    int MIN_HEIGHT_CARBON = 6;
    int MIN_CIRCUMFERENCE = 7;
    int MAX_HEIGHT_CARBON = 42;
    int MAX_CIRCUMFERENCE = 60;
    int MAX_INDEX_HEIGHT = 36;
    int MAX_INDEX_CIRCUMFERENCE = 53;
    int NEGATIVE_ONE = -1;
    int HEIGHT_INDEX_CORRECTION = -6;
    int CIRCUMFERENCE_INDEX_CORRECTION = -7;
}