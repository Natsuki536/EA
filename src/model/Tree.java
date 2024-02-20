package model;

/**
 * <h1>Tree</h1>
 * <p>
 *      This class is used for representing a tree with its attributes being an id the tree has, a site (neighbourhood and location)
 *      where the tree is located, botanic (tree genus and species in German and Latin), time data (being year of planting and standing time)
 *      and metric data (being height, trunk circumference, treetop diameter).
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-10
 */
public class Tree
{
    private int id;
    private Site site;
    private Botanic botanic;
    private TimeData timeData;
    private Metric metric;


    //Constructors-----------------------------------
    public Tree (int id, String name, String treeSpecies, String botanicalSpecies, String treeGenusGerman, String treeGenus, long yearOfPlanting, int treeAge, double treetopDiameter, double trunkDiameter, double treeHeight, String neighbourhood)
    {
        this.id = id;
        this.site = new Site(name, neighbourhood);
        this.botanic = new Botanic(treeSpecies, botanicalSpecies, treeGenus, treeGenusGerman);
        this.timeData = new TimeData(yearOfPlanting, treeAge);
        this.metric = new Metric(treetopDiameter, trunkDiameter, treeHeight);
    }


    public Tree (int id, Site site, Botanic botanic, TimeData timeData, Metric metric)
    {
        this.id = id;
        this.site = site;
        this.botanic = botanic;
        this.timeData = timeData;
        this.metric = metric;
    }


    public Tree (int id, Site site, String treeSpecies, String botanicalSpecies, String treeGenusGerman, String treeGenus, long yearOfPlanting, int treeAge, double treetopDiameter, double trunkCircumference, double treeHeight)
    {
        this.id = id;
        this.site = site;
        this.botanic = new Botanic(treeSpecies, botanicalSpecies, treeGenus, treeGenusGerman);
        this.timeData = new TimeData(yearOfPlanting, treeAge);
        this.metric = new Metric(treetopDiameter, trunkCircumference, treeHeight);
    }


    //Getter--------------------------------------
    public String getId ()
    {
        return String.valueOf(id);
    }


    public Site getAddress ()
    {
        return site;
    }


    public Botanic getBotanic ()
    {
        return botanic;
    }


    private TimeData getTimeData ()
    {
        return timeData;
    }


    public int getTreeStandingTime ()
    {
        return getTimeData().getStandingTime();
    }


    public long getYearOfPlanting ()
    {
        return getTimeData().getYearOfPlanting();
    }


    private Metric getMetric ()
    {
        return metric;
    }


    public double getHeight ()
    {
        return getMetric().getHeight();
    }


    public double getTopDiameter ()
    {
        return getMetric().getTreetopDiameter();
    }


    public double getTrunkCircumference ()
    {
        return getMetric().getTrunkCircumference();
    }


    public String getNeighbourhood ()
    {
        return getAddress().getNeighbourhood();
    }

    public String getGenus ()
    {
        return getBotanic().getTreeGenus();
    }

    public String getSpecies ()
    {
        return getBotanic().getTreeSpecies();
    }

    public String getSpeciesBotanical ()
    {
        return getBotanic().getTreeSpeciesBotanical();
    }

    //Setter---------------------------------------
    public void setAge (TimeData timeData)
    {
        this.timeData = timeData;
    }


    public void setMetric (Metric metric)
    {
        this.metric = metric;
    }


    public void setTreeStandingTime (int treeStandingTime)
    {
        timeData.setStandingTime(treeStandingTime);
    }


    public void setHeight (double height)
    {
        metric.setHeight(height);
    }

    public void setCircumference (double circumference)
    {
        metric.setTrunkCircumference(circumference);
    }

    public void setTopDiameter (double topDiameter)
    {
        metric.setTreetopDiameter(topDiameter);
    }
}
