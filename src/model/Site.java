package model;

/**
 * <h1>Site</h1>
 * <p>
 *      This class represents a site where a tree can be located. This site consist of a neighbourhood and a location being for example a
 *      street.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-17
 */
public class Site
{
    String neighbourhood;
    String location;

    //Constructor
    public Site (String location, String neighbourhood)
    {
        {
            this.location = location;
            this.neighbourhood = neighbourhood;
        }
    }

    //Getter
    public String getNeighbourhood ()
    {
        return neighbourhood;
    }


    public String getLocation ()
    {
        return location;
    }
}
