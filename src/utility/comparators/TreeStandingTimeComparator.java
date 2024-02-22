package utility.comparators;


import model.Tree;

import java.util.Comparator;

//TODO: Comments
public class TreeStandingTimeComparator implements Comparator<Tree>
{
    @Override
    public int compare (Tree tree, Tree anotherTree)
    {
        return Integer.compare(tree.getTreeStandingTime(), anotherTree.getTreeStandingTime());
    }
}
