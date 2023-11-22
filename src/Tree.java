public class Tree {
    private static final int TREE_SIZE_L = 14;
    private static final int TREE_SIZE_M = 10;
    private static final int TREE_SIZE_S = 7;
    private static final int TREE_COST_L = 1000;
    private static final int TREE_COST_M = 50;
    private static final int TREE_COST_S = 5;
    private String treeType;
    private int treeSize;
    private int treeProduction;
    private int treePrice;

    // constructor for the Tree Class
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

    public static int sizeBasedOnType(String treeType) {
        return switch (treeType) {
            case "large" -> TREE_SIZE_L;
            case "medium" -> TREE_SIZE_M;
            case "small" -> TREE_SIZE_S;
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        };
    }

    public static int costBasedOnType(String treeType) {
        return switch (treeType) {
            case "large" -> TREE_COST_L;
            case "medium" -> TREE_COST_M;
            case "small" -> TREE_COST_S;
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        };
    }

    public int getTreeSize() {
        return treeSize;
    }

    public String getTreeType() {
        return treeType;
    }

    public int lemonPerWeek() {
        return treeProduction;
    }
}
