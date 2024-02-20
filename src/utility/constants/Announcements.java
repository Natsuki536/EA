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
//TODO:translate to English
    //String constants for announcements
    String ANNOUNCEMENT_DATA_READ = "Die Datei mit dem folgenden Pfad wird jetzt eingelesen -> ";
    String ANNOUNCEMENT_DATA_READ_SUCCESSFUL = "Die Datei wurde in %dms eingelesen , es wurden %d Zeilen gefunden ...";
    String ANNOUNCEMENT_PARSE_DATA = "Die Datei wird nun verarbeitet und geparst... -> ";
    String ANNOUNCEMENT_DATA_PARSE_SUCCESSFUL = "Die Daten wurden erfolgreich in %dms geparsed , dabei wurden %d Datenobjekte validiert ... ";
    String ANNOUNCEMENT_PRINT_DATA = "Die Datei wird nun ausgegeben... -> ";
    String ANNOUNCEMENT_PRINT_OF_ARRAYLIST_SUCCESSFUL = "Die Daten wurden erfolgreich in %dms ausgegeben, dabei wurden %d Datenobjekte verarbeitet";
    String ANNOUNCEMENT_DELETE_LINES = "Invalide Datensätze werden nun gelöscht... -> ";
    String ANNOUNCEMENT_LINES_DELTED = "Die invaliden Daten wurden in %dms gelöscht. Es wurden %d Datensätze gelöscht.";
    String ANNOUNCEMENT_ADJUST_DATA = "Datensätze werden nun angepasst... -> ";
    String ANNOUNCEMENT_TREES_CORRECTED = "Die invaliden Daten wurden in %dms korrigiert. Es wurden %d Datensätze korrigiert.";
    String ANNOUNCEMENT_OPEN_MENU = "Das Menü wird nun geöffnet... -> ";
    int ERROR_NEGATIVE_ONE = -1;
    String ERROR_COUNT = "There have been this number of errors: ";
}
