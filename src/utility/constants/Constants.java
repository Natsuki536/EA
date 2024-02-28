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
    long CONVERSION_NANO_TO_MILLI = 1_000_000L;

    //Constants for replacing empty strings & strings with space
    String EMPTY_STRING = "";
    String STRING_WITH_SPACE = " ";
    String REPLACEMENT_OF_EMPTY_AND_SPACE = "0";
    String NEW_LINE = "\n";
    int BOUND_LEFT = 3;
    int BOUND_RIGHT = 12;

    //Constants for CSV-Reader and counting method
    String FILE_PATH = "./src/resources/Baumkataster_Berlin_test_correction.csv";
    String DATA_CSV_DELIMITER = ";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
    int LIMIT_FOR_SPLITTING = -1;
    int SEMICOLONS_PER_LINE = 11;
    char STRING_TO_SEARCH = ';';
    char CONDITION_TO_NOT_COUNT = '"';
    int ZERO = 0;
    double ZERO_DOUBLE = 0d;

    //TODO: Put MAX in args!
    //constants for believable data
    int MAX_HEIGHT = 44;
    int MIN_HEIGHT = 0;
    Double CORRECTION_HEIGHT = 10d;
    Double MAX_TRUNK_CIRCUMFERENCE = 800d;
    Double MIN_TRUNK_CIRCUMFERENCE = 0.0;
    Double CORRECTION_FOR_TRUNK_CIRCUMFERENCE = 50d;
    int MAX_STANDINGTIME = 1000;
    int MIN_STANDINGTIME = 0;
    Double MAX_TREETOP_DIAMETER = 25d;
    Double MIN_TREETOP_DIAMETER = 0.0;
    Double CORRECTION_FOR_TREETOP_DIAMETER = 5d;
    Double CONVERSION_CENTI_TO_METER = 100D;
    String UNBEKANNT = "UNBEKANNT";
    String ZERO_STRING = "0";

    //Strings for grouping trees
    String GROUP_BY_GENUS = "genus";
    String GROUP_BY_SPECIES = "species";
    String NEIGHBOURHOOD = "neighbourhood";
    String SPECIES = ", with the following number of species: ";
    String HEIGHT_OF_TALLEST_TREE = ", with the height of tallest tree being: ";
    String NUMBER_OF_TREES = ", with the following number of trees: ";
    String LARGEST_CIRCUMFERENCE = "The following tree has the largest trunk circumference:";
    String LARGEST_CROWN = "The tree following tree has the largest crown diameter:";
    String OLDEST_TREE = "The following tree is the oldest:";
    String TREE = "Tree: ";
    String ID = "ID: ";
    String TREETOP = "Treetop diameter: ";
    String TRUNK = "Trunk circumference: ";
    String STANDINGTIME = "Standing time: ";
    String AVERAGE_HEIGHT = "The genus %s has the greatest average height, with the average being %f";
    String AVERAGE_CIRCUMFERENCE = "The genus %s has the greatest average circumference, with the average being %f";
    String BERLIN_BOUND_CARBON = "Berlin has bound the following amount of carbon: ";
    String NUMBER_GENERA = "There is the following number of genera: ";
    String NUMBER_SPECIES = "There is the following number of species: ";
    String NEIGHBOURHOOD_WITH_MOST_SPECIES = " The neighbourhood with the most species is: ";
    String NEIGHBOURHOOD_WITH_TALLEST_TREE = " The neighbourhood with the tallest tree is: ";
    String GROUPING_WITH_MOST_TREES = "The %s with the most trees is: %s, with the following number of trees: %d.";
    String GROUPING_WITH_MOST_BOUND_CARBON = "The %s with the most bound carbon is: %s, with the following amount of carbon bound [in t]: %d.";


    //Constants for correcting
    int ONE = 1;
    int TWO = 2;
    int YEAR_OF_DATASET = 2023;
    long ZERO_LONG = 0L;


    //Constants for separating foliage habit
    String CONIFEROUS = "coniferous";
    String DECIDUOUS = "deciduous";
    int MIN_HEIGHT_CARBON = 6;
    int MIN_DIAMETER = 7;
    int MAX_HEIGHT_CARBON = 42;
    int MAX_DIAMETER = 60;
    int MAX_INDEX_HEIGHT = 36;
    int MAX_INDEX_DIAMETER = 53;
    int NEGATIVE_ONE = -1;
    int HEIGHT_INDEX_CORRECTION = -6;
    int DIAMETER_INDEX_CORRECTION = -7;

    //Constants for Main Controller
    int NUMBER_OF_ARGS = 4;
}
