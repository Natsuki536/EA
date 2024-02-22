package utility.comparators;


import model.Tree;

import java.util.Comparator;

//TODO: Comments
public class TreeTrunkCircumferenceComparator implements Comparator<Tree>
{
    @Override
    public int compare (Tree tree, Tree anotherTree)
    {
        return Double.compare(tree.getTrunkCircumference(), anotherTree.getTrunkCircumference());
    }
}
