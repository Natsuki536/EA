package model;

/**
 * <h1>Time Data</h1>
 * <p>
 *      This class represents time data a tree might have and consists in this case of a year of planting and a standin time.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-17
 */
public class TimeData
{
    long yearOfPlanting;
    int standingTime;

    //Constructor
    public TimeData (long yearOfPlanting, int standingTime)
    {
        this.yearOfPlanting = yearOfPlanting;
        this.standingTime = standingTime;
    }

    //Getter
    public long getYearOfPlanting ()
    {
        return yearOfPlanting;
    }


    public int getStandingTime ()
    {
        return standingTime;
    }

    //Setter


    public void setYearOfPlanting (long yearOfPlanting)
    {
        this.yearOfPlanting = yearOfPlanting;
    }


    public void setStandingTime (int standingTime)
    {
        this.standingTime = standingTime;
    }
}
