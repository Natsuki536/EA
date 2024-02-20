package utility.service;


import utility.constants.Constants;


/**
 * <h1>CSV-Parser</h1>
 * <p>
 *     This class stores all methods needed for stopping the time a method needs to run.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.1
 * @since 2024-01-10
 */
public class Stopwatch
{
    private long startTime;


    //methods


    /**
     * @author Hannah Wollenweber
     * This method sets startime of stopwatch
     * @precondition none
     * @postcondition the start time is set to the current nano time
     */
    public Stopwatch ()
    {
        startTime = System.nanoTime();
    }


    /**
     * @author Hannah Wollenweber
     * @precondition none
     * @postcondition the start time is being set to the current time
     */
    public void setNewStartTime ()
    {
        this.setStartTime(System.nanoTime());
    }


    /**
     * @author Hannah Wollenweber
     * This method first gets the nanoTime of the JVM when the method is called and then calculates the by the method required time to run in milliseconds.
     * @precondition none
     * @postcondition The elapsed time is returned in milliseconds.
     * @return elapsedTime
     */
    public long getElapsedTimeSinceMethodCall ()
    {
        long requiredTime = (System.nanoTime() - this.getStartTime()) / Constants.CONVERSION_NANO_TO_MILLI;
        setNewStartTime();
        return requiredTime;
    }

    //-----Getter and Setter---


    /**
     * @author Hannah Wollenweber
     * @precondition none
     * @postcondition start time is returned
     * @return startTime
     */
    public long getStartTime ()
    {
        return startTime;
    }


    /**
     * @author Hannah Wollenweber
     * @precondition a value to change to is given as long
     * @postcondition the attribute is set to the wanted time
     * @param startTime time that is set as starttime
     */
    public void setStartTime (long startTime)
    {
        this.startTime = startTime;
    }
}
