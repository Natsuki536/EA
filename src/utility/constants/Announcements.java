package utility.constants;


/**
 * <h1>Announcement Constants</h1>
 * <p>
 *     This interfaces stores constants that are needed in the programm
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-31
 */
public interface Announcements
{
    //String for error announcements
    String INVALID_INPUT = "Invalid input";
    //String constants for announcements
    String ANNOUNCEMENT_DATA_READ = "The file with the following path is now read in -> ";
    String ANNOUNCEMENT_DATA_READ_SUCCESSFUL = "The file was read in %dms , %d lines were found ...";
    String ANNOUNCEMENT_PARSE_DATA = "The file is now processed and parsed... ->";
    String ANNOUNCEMENT_DATA_PARSE_SUCCESSFUL = "The data was successfully parsed in %dms , validating %d data objects ...";
    String ANNOUNCEMENT_PRINT_DATA = "The file is now output... ->";
    String ANNOUNCEMENT_PRINT_OF_ARRAYLIST_SUCCESSFUL = "The data was successfully output in %dms and %d data objects were processed";
    String ANNOUNCEMENT_DELETE_LINES = "Invalid data records will now be deleted... ->";
    String ANNOUNCEMENT_LINES_DELETED = "The invalid data was deleted in %dms. %d data records were deleted.";
    String ANNOUNCEMENT_ADJUST_DATA = "Data records will now be adjusted... ->";
    String ANNOUNCEMENT_TREES_CORRECTED = "The invalid data was corrected in %dms with %d data records being corrected.";
    String ANNOUNCEMENT_OPEN_MENU = "The menu will now be opened...  -> ";
    int ERROR_NEGATIVE_ONE = -1;
    String ERROR_COUNT = "There have been this number of errors: ";
    String GREETING = "Hello and welcome to my java programm!";
    String ERROR_NO_ARGUMENT_IN_COMMAND_LINE = "No valid arguments have been passed in the command line";
    String ERROR_TOO_MANY_ARGUMENTs_IN_COMMAND_LINE = "Too many arguments have been passed in the command line";
    String ERROR_INVALID_ARGUMENT_IN_COMMAND_LINE = "An invalid argument has been passed in the command line";
    String ERROR_INVALID_USER_INPUT = "The user typed in an invalid input";
}
