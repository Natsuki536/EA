package model;


/**
 * <h1>Botanic</h1>
 * <p>
 *      This class contains the data concerning the botanic a tree might have. Here it contains the species and genus with both its
 *      German and Latin name.
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-01-17
 */
public class Botanic
{
    private String treeSpecies;
    private String treeSpeciesBotanical;
    private String treeGenus;
    private String treeGenusGerman;


    //Constructor
    public Botanic (String treeSpecies, String treeSpeciesBotanical, String treeGenus, String treeGenusGerman)
    {
        this.treeSpecies = treeSpecies;
        this.treeSpeciesBotanical = treeSpeciesBotanical;
        this.treeGenus = treeGenus;
        this.treeGenusGerman = treeGenusGerman;
    }


    //Getter
    public String getTreeSpecies ()
    {
        return treeSpecies;
    }


    public String getTreeSpeciesBotanical ()
    {
        return treeSpeciesBotanical;
    }


    public String getTreeGenus ()
    {
        return treeGenus;
    }


    public String getTreeGenusGerman ()
    {
        return treeGenusGerman;
    }
}
