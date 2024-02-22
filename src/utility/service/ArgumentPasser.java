package utility.service;

//TODO:
public class ArgumentPasser
{
    int maxStandingTime;
    int maxHeight;
    int maxTrunkCircumference;
    int maxTopDiameter;

    //Constructor
    public ArgumentPasser (int maxStandingTime, int maxHeight, int maxTrunkCircumference, int maxTopDiameter)
    {
        this.maxStandingTime = maxStandingTime;
        this.maxHeight = maxHeight;
        this.maxTrunkCircumference = maxTrunkCircumference;
        this.maxTopDiameter = maxTopDiameter;
    }

    //Getter and Setter
    public int getMaxStandingTime ()
    {
        return maxStandingTime;
    }


    public void setMaxStandingTime (int maxStandingTime)
    {
        this.maxStandingTime = maxStandingTime;
    }


    public int getMaxHeight ()
    {
        return maxHeight;
    }


    public void setMaxHeight (int maxHeight)
    {
        this.maxHeight = maxHeight;
    }


    public int getMaxTrunkCircumference ()
    {
        return maxTrunkCircumference;
    }


    public void setMaxTrunkCircumference (int maxTrunkCircumference)
    {
        this.maxTrunkCircumference = maxTrunkCircumference;
    }


    public int getMaxTopDiameter ()
    {
        return maxTopDiameter;
    }


    public void setMaxTopDiameter (int maxTopDiameter)
    {
        this.maxTopDiameter = maxTopDiameter;
    }
}
