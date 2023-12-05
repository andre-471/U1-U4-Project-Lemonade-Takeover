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
    private static final int SIZE = 70;

    /** A list of trees on the plot */
    private ArrayList<Tree> trees;

    /** The amount of plot space left as trees are added */
    private int availableSpace;

    /** Instantiates a Plot object. */
    public Plot() {
        trees = new ArrayList<Tree>();
        availableSpace = SIZE;
    }

    public static int maxTreesInPlot(String treeType) {
        return SIZE / Tree.sizeBasedOnType(treeType);
    }

    /**
     * Method that returns the total lemonade production
     *
     * @return The total lemonade produced from all trees in a week
     */
    public int totalTreeProduction() {
        int lemonPerWeek = 0;

        for (Tree tree : trees) {
            lemonPerWeek += tree.getTreeProduction();
        }

        return lemonPerWeek;
    }

    /**
     * Method that checks if the plot has space given the amount of a type of tree
     * <p>
     * PRECONDITION: treeType is lowercase
     *
     * @param treeType Type of some kind of tree
     * @param amount Amount of that kind of tree
     * @return If the plot has space to store a tree of that type
     */
    public boolean hasSpace(String treeType, int amount) {
        return availableSpace - Tree.sizeBasedOnType(treeType) * amount >= 0;
    }

    /**
     * Method that adds a tree to the plot while preserving order based on treeSize
     * <P>
     * PRECONDITION: availablespace is positive and will remain positive
     * @param treeType Type of some kind of tree
     * @param amount Amount of that kind of tree
     */
    public void addTree(String treeType, int amount) {
        int treeSize = Tree.sizeBasedOnType(treeType);
        for (int i = 0; i < amount; i++) {
            trees.add(new Tree(treeType));
            availableSpace -= treeSize;
        }
    }

    public String listTrees() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Tree tree : trees) {
            stringBuilder.append(tree.getTreeSize());
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    /**
     * Method that makes plot space for a tree given a tree type
     *
     * @param treeType Type of some kind of tree
     * @param amount Amount of that kind of tree
     */
    public void makePlotSpace(String treeType, int amount) {
        int spaceToMake = Tree.sizeBasedOnType(treeType) * amount;
        int[] treesNeeded = treesNeeded(spaceToMake);

        removeTree(treesNeeded);
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
    public int removeTree(int idx) {
        int treeCost = trees.get(idx).getTreePrice();
        availableSpace += trees.get(idx).getTreeSize();
        trees.remove(idx);
        return treeCost;
    }

    private int removeTree(int[] treesNeeded) {
        int i = 0;
        int treeCost = 0;

        while (i < trees.size()) {
            if (treesNeeded[0] > 0 && trees.get(i).getTreeType().equals("small")) {
                treesNeeded[0]--;
                treeCost += removeTree(i);
            } else if (treesNeeded[1] > 0 && trees.get(i).getTreeType().equals("medium")) {
                treesNeeded[1]--;
                treeCost += removeTree(i);
            } else if (treesNeeded[2] > 0 && trees.get(i).getTreeType().equals("large")) {
                treesNeeded[2]--;
                treeCost += removeTree(i);
            } else {
                i++;
            }
        }

        return treeCost;
    }

    private int[] treesNeeded(int plotSpace) {
        int[] treeCount = countTrees();
        int[] treesNeeded = new int[3];

        int smallTreesNeeded = (int) Math.nextUp((double) plotSpace / Tree.sizeBasedOnType("small"));
        treesNeeded[0] = Math.min(smallTreesNeeded, treeCount[0]);
        plotSpace -= treesNeeded[0] * Tree.sizeBasedOnType("small");

        if (plotSpace <= 0) { return treesNeeded; }

        int mediumTreesNeeded = (int) Math.nextUp((double) plotSpace / Tree.sizeBasedOnType("medium"));
        treesNeeded[1] = Math.min(mediumTreesNeeded, treeCount[1]);
        plotSpace -= treesNeeded[1] * Tree.sizeBasedOnType("medium");

        if (plotSpace <= 0) { return treesNeeded; }

        int largeTreesNeeded = (int) Math.nextUp((double) plotSpace / Tree.sizeBasedOnType("large"));
        treesNeeded[2] = largeTreesNeeded;

        return treesNeeded;
    }

    private int[] countTrees() {
        int[] treeCount = new int[3];

        for (Tree tree : trees) {
            switch (tree.getTreeType()) {
                case "small" -> treeCount[0]++;
                case "medium" -> treeCount[1]++;
                case "large" -> treeCount[2]++;
                default -> throw new IllegalStateException("Unexpected value: " + tree.getTreeType());
            }
        }
        return treeCount;
    }
}
