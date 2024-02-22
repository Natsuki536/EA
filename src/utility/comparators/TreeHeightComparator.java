package utility.comparators;


import model.Tree;

import java.util.Comparator;

//TODO: Comments
public class TreeHeightComparator implements Comparator<Tree>
{
    @Override
    public int compare (Tree tree, Tree anotherTree)
    {
        return Double.compare(tree.getHeight(), anotherTree.getHeight());
    }
}
