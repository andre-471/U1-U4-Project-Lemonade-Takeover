import java.util.List;

public class Tree {
    public static int TREE_SIZE_L = 14;
    public static int TREE_SIZE_M = 10;
    public static int TREE_SIZE_S = 7;
    public static int TREE_COST_L = 1000;
    public static int TREE_COST_M = 50;
    public static int TREE_COST_S = 5;
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
                treePrice = 50;
            }
            case "small" -> {
                treeSize = 7;
                treeProduction = 5;
                treePrice = 5;
            }
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        }
    }

    public static int sizeBasedOnType(String treeType) {
//        switch (treeType) {
//            case "large" -> {
//                treeSize = 14;
//                treeProduction = 45;
//                treePrice = 1000;
//            }
//            case "medium" -> {
//                treeSize = 10;
//                treeProduction = 20;
//                treePrice = 50;
//            }
//            case "small" -> {
//                treeSize = 7;
//                treeProduction = 5;
//                treePrice = 5;
//            }
//            default -> throw new IllegalStateException("Unexpected value: " + treeType);
//        }
        return 0;
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
