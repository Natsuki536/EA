package utility.service;


import model.Tree;

import java.util.ArrayList;

/**
 * <h1>Pair</h1>
 * <p>
 *     This class stores the necessary attributes and getter to be able to return both an Integer and a treeArrayList
 * </p>
 *
 * @author Hannah Wollenweber
 * @version 1.0
 * @since 2024-02-19
 */
public class Pair {
    private int value;
    private ArrayList<Tree> treeArrayList;

    //Constructor------------
    public Pair(int value, ArrayList<Tree> treeArrayList) {
        this.value = value;
        this.treeArrayList = treeArrayList;
    }

    //Getter--------------
    public int getValue() {
        return value;
    }

    public ArrayList<Tree> getTreeArrayList ()
    {
        return treeArrayList;
    }
}
