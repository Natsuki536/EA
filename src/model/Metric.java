package model;

/**
 * <h1>Metric</h1>
 * <p>
 *      This class contain metric data a tree might have, in this case it consists of height, treetop diameter and a trunk circumference
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-17
 */
public class Metric
{
    private double treetopDiameter; //in meters
    private double trunkCircumference;//in centimeter
    private double height; //in meter

    //Constructor
    public Metric (double treetopDiameter, double trunkCircumference, double height)
    {
        this.treetopDiameter = treetopDiameter;
        this.trunkCircumference = trunkCircumference;
        this.height = height;
    }

    //Getter
    public double getTreetopDiameter ()
    {
        return treetopDiameter;
    }


    public double getHeight ()
    {
        return height;
    }


    public double getTrunkCircumference ()
    {
        return trunkCircumference;
    }

    //setter
    public void setTreetopDiameter (double treetopDiameter)
    {
        this.treetopDiameter = treetopDiameter;
    }


    public void setTrunkCircumference (double trunkCircumference)
    {
        this.trunkCircumference = trunkCircumference;
    }


    public void setHeight (double height)
    {
        this.height = height;
    }
}
