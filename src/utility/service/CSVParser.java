package utility.service;


import utility.constants.Constants;

import java.io.*;
import java.util.ArrayList;


/**
 * <h1>CSV-Parser</h1>
 * <p>
 *     This class stores all methods needed for reading a csv file and storing the contents in an ArrayList of StringArrays as it is
 *     a parser.
 * </p>
 * <h2>Parser</h2>
 * <p>
 *     A parser in general "is a software component that takes input data (frequently text) and builds a data structure – often some
 *     kind of parse tree, abstract syntax tree or other hierarchical structure, giving a structural representation of the input
 *     while checking for correct syntax." (see <a href="https://en.wikipedia.org/wiki/Parsing#Parser">Parser</a> for more information)
 * </p>
 * <h2>Comma Seperated File</h2>
 * <p>
 *     "Comma-separated values (CSV) is a text file format that uses commas to separate values, and newlines to separate records.
 *     A CSV file stores tabular data (numbers and text) in plain text, where each line of the file typically represents one data
 *     record. Each record consists of the same number of fields, and these are separated by commas in the CSV file. If the field
 *     delimiter itself may appear within a field, fields can be surrounded with quotation marks."
 *     (see: <a href="https://en.wikipedia.org/wiki/Comma-separated_values">CSV-File</a>)
 * </p>
 * <h3>RFC 4180</h3>
 * <p>
 *     The RFC 4180 or Common Format and MIME Type for Comma-Seperated Values Files defines a variant of CSV.
 *     Lines are closes with '\r\n', fields are seperated by comma (,) and spaces are part of the fields.
 *     The lines or fields can also contain commas or line breaks given they are put in quotes then.
 *     The upmost line is the header and contains descriptions of the field content e.g. "title,author,year"
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.1
 * @since 2024-01-10
 */
public class CSVParser implements Constants
{
    //Attributes
    private final String documentPath;
    private final String separator;


    //Constructor
    public CSVParser (String documentPath, String separator)
    {
        this.documentPath = documentPath;
        this.separator = separator;
    }


    /**
     * @author Hannah Wollenweber
     * This method reads a CSV-File with 12 fields in each line, separated by a semicolon.
     * These lines are put in an ArrayList as Stringarray.
     * @precondition A valid CSV file with 12 fiels per dataset, being one line, is given.
     * @postcondition An Arraylist of StringArrays is returned
     * @throws FileNotFoundException e
     * @return Arraylist rawData
     */
    public ArrayList<String[]> readData () throws FileNotFoundException
    {
        // array list to hold each line in the form of a String array
        ArrayList<String[]> rawData = new ArrayList<>();
        // create reader
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.getDocumentPath())))
        {
            //initialise line, StringBuilder, semicolonCounter, boolean
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            int semicolonCounter = ZERO;
            boolean insideQuotedField = false;

            //skip header
            bufferedReader.readLine();

            //read next line as long as there is one
            while ((line = bufferedReader.readLine()) != null)
            {
                //convert line to char array that can be ittereted through
                char[] charArray = line.toCharArray();
                //add line to StringBuilder
                for (char ch : charArray)
                {
                    //add character to StringBuilder
                    stringBuilder.append(ch);

                    //count semicolons outside of quotes
                    semicolonCounter += count_semicolons(ch);
                }
                //when s´counter gets to 11 semicolons, save the dataset as Stringarray in arraylist
                if (semicolonCounter >= SEMICOLONS_PER_LINE && !insideQuotedField)
                {
                    //split the complete line by semicolons
                    rawData.add(stringBuilder.toString().split(this.getSeparator(), LIMIT_FOR_SPLITTING));
                    stringBuilder.setLength(ZERO); // clear the StringBuilder
                    semicolonCounter = ZERO; //reset semicolon counter
                    insideQuotedField = false; // reset the quoted field flag
                } else
                {
                    // If the line doesn't meet the criteria, append a newline to the StringBuilder
                    //TODO: check if this is needed!
                    //StringBuilder.append(NEW_LINE);
                }
            }

        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        return rawData;
    }


    /**
     * @author Hannah Wollenweber
     * This method counts semicolons in a dataline given char by char that are not in quotes.
     * If a quote is given, the next semicolons are not counted until the quotes are closed again.
     * @precondition A char is given
     * @postcondition The count of semicolons is returned
     * @param ch character given to function
     * @return int current count of semicolons
     * */
    public static int count_semicolons (char ch)
    {
        int semicolonCounter = ZERO;
        boolean insideQuotedField = false;

        if (ch == STRING_TO_SEARCH)
        {
            //check if semicolon is not inside quoted field
            if (!insideQuotedField)
            {
                semicolonCounter++;
            }
        }
        //check if in text field; marked by quotes
        if (ch == CONDITION_TO_NOT_COUNT)
        {
            insideQuotedField = !insideQuotedField;
        }

        return semicolonCounter;
    }


    //-----Getter-----
    public String getDocumentPath ()
    {
        return documentPath;
    }


    public String getSeparator ()
    {
        return separator;
    }
}
