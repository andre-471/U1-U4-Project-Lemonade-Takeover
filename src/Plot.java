import java.util.ArrayList;

/**
 * This class represents a plot of lemonade trees, with methods to manage those trees
 *
 * @author Andrew Zhang
 */
public class Plot {
    /** The cost of a plot */
    public static final int COST = 100;

    /** The size of a plot */
    private static final int PLOTSIZE = 70;

    /** A list of trees on the plot */
    private ArrayList<Tree> trees;

    /** The amount of plot space left as trees are added */
    private int availableSpace;

    /** Instantiates a Plot object. */
    public Plot() {
        trees = new ArrayList<Tree>();
        availableSpace = PLOTSIZE;
    }

    /**
     * Method that returns the total lemonade production
     *
     * @return The total lemonade produced from all trees in a week
     */
    public int totalLemonadePerWeek() {
        int lemonPerWeek = 0;

        for (Tree tree : trees) {
            lemonPerWeek += tree.lemonadePerWeek();
        }

        return lemonPerWeek;
    }

    /**
     * Method that checks if the plot has space given the type of a tree
     * <p>
     * PRECONDITION: treeType is lowercase
     *
     * @param treeType Type of some kind of tree
     * @return If the plot has space to store a tree of that type
     */
    public boolean hasSpace(String treeType) {
        return availableSpace - Tree.sizeBasedOnType(treeType) >= 0;
    }

    /**
     * Method that adds a tree to the plot
     *
     * @param treeType Type of some kind of tree
     */
    public void addTree(String treeType) {
        Tree newTree = new Tree(treeType);
        if (hasSpace(treeType)) {
            trees.add(new Tree(treeType));
        } else {
            makePlotSpace(treeType);
            trees.add(new Tree(treeType));
        }
    }

    /**
     * Method that makes plot space for a tree given a tree type
     *
     * @param treeType Type of some kind of tree
     */
    public void makePlotSpace(String treeType) {
        // hard to implement wow
    }

    /**
     * Method that returns plot's value
     *
     * @return The sum of the cost of all trees
     */
    public int plotValue() {
        int plotValue = 0;
        for (Tree tree : trees) {
            plotValue += tree.getTreePrice();
        }

        return plotValue;
    }

    /**
     * Sells a tree in the trees list given the index
     *
     * @param idx Index of the tree object to sell
     */
    public void sellTree(int idx) {
        String treeType = trees.get(idx).getTreeType();
    }
}
