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
    public int totalLemonadePerWeek() {
        int lemonPerWeek = 0;

        for (Tree tree : trees) {
            lemonPerWeek += tree.lemonadePerWeek();
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
        return availableSpace - Tree.sizeBasedOnType(treeType) >= 0;
    }

    /**
     * Method that adds a tree to the plot while preserving order based on treeSize
     *
     * @param treeType Type of some kind of tree
     * @param amount Amount of that kind of tree
     */
    public void addTree(String treeType, int amount) {
        int treeSize = Tree.sizeBasedOnType(treeType);
        int closestTreeIdx = treeSizeBinarySearch(treeSize);

        for (int i = 0; i < amount; i++) {
            trees.add(closestTreeIdx, new Tree(treeType));
            availableSpace -= Tree.sizeBasedOnType(treeType);
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

        int[] treeCount = countTrees(); // small medium large

//        int smallTreesNeeded = treesToClear("small", spaceToMake);
//        int mediumTreesNeeded = treesToClear("medium", spaceToMake);
//        int largeTreesNeeded = treesToClear("large", spaceToMake);

//        if
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
    private int treesToClear(String treeType, int plotSpace) {
        double trees = (double) plotSpace / Tree.sizeBasedOnType(treeType);

        if (trees - (int) trees == 0.0) { return (int) trees; }

        return (int) trees + 1;
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

    private int treeSizeBinarySearch(int treeSize) { // supports search for nearest number
        if (trees.isEmpty()) { return 0; }

        int high = trees.size() - 1, mid = high / 2, low = 0;
        while (low <= high) {
            int currSize = trees.get(mid).getTreeSize();

            if (treeSize < currSize) {
                high = mid - 1;
            } else if (treeSize > currSize) {
                low = mid + 1;
            } else {
                return mid;
            }
                mid = low + (high - low) / 2;
        }

        return mid;

    }
}
