/**
 * This class represents a tree object
 *
 * @author Munim Ali and Andrew Zhang
 */
public class Tree {
    /** The size of all large trees */
    private static final int TREE_SIZE_L = 14;
    /** The size of all medium trees */
    private static final int TREE_SIZE_M = 10;
    /** The size of all small trees */
    private static final int TREE_SIZE_S = 7;
    /** The cost of all large trees */
    private static final int TREE_COST_L = 1000;
    /** The cost of all medium trees */
    private static final int TREE_COST_M = 50;
    /** The cost of all small trees */
    private static final int TREE_COST_S = 5;
    /** the size of a tree in String format*/
    private String treeType;
    /** the size of a tree in int format */
    private int treeSize;
    /** How many lemons a tree produces per week*/
    private int treeProduction;
    /** The price of a specific tree object */
    private int treePrice;

    /**
     * Instantiates a Tree object.
     * @param treeType The size of a tree in String format
     */
    public Tree(String treeType) {
        this.treeType = treeType;
        switch (treeType) {
            case "large" -> {
                treeSize = TREE_SIZE_L;
                treeProduction = 45;
                treePrice = TREE_COST_L;
            }
            case "medium" -> {
                treeSize = TREE_SIZE_M;
                treeProduction = 20;
                treePrice = TREE_COST_M;
            }
            case "small" -> {
                treeSize = TREE_SIZE_S;
                treeProduction = 5;
                treePrice = TREE_COST_S;
            }
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        }
    }

    /**
     * Static method that returns a value representing the size of a tree.
     * @param treeType the size of a tree in String format.
     * @return The size of a Tree object in int format.
     */
    public static int sizeBasedOnType(String treeType) {
        return switch (treeType) {
            case "large" -> TREE_SIZE_L;
            case "medium" -> TREE_SIZE_M;
            case "small" -> TREE_SIZE_S;
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        };
    }

    /**
     * A static method that returns the price of a tree the user is trying to buy
     * @param treeType The type of tree that is trying to be bought
     * @return The price of a type of tree
     */
    public static int costBasedOnType(String treeType) {
        return switch (treeType) {
            case "large" -> TREE_COST_L;
            case "medium" -> TREE_COST_M;
            case "small" -> TREE_COST_S;
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        };
    }

    /**
     * Returns the current size of a tree in int format
     *
     * @return The size of a tree in int format
     */
    public int getTreeSize() {
        return treeSize;
    }

    /**
     * Returns the current size of a tree in String format
     * @return The size of a tree in String format
     */
    public String getTreeType() {
        return treeType;
    }

    /**
     * Returns the amount of money a person would get from selling their tree
     * @return The value of the tree
     */
    public int getTreePrice() {
        return treePrice;
    }

    /**
     * Returns the amount of lemons a tree produces in a week
     * @return The weekly amount of lemons made by a tree
     */
    public int lemonadePerWeek() {
        return treeProduction;
    }
}
