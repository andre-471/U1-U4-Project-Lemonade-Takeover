import java.util.List;

public class Tree {
    private String treeType;
    private int treeSize;
    private int treeProduction;
    // constructor for the Tree Class
    public Tree(String treeType) {
        this.treeType = treeType;
        switch (treeType) {
            case "large" -> {
                treeSize = 14;
                treeProduction = 45;
            }
            case "medium" -> {
                treeSize = 10;
                treeProduction = 20;
            }
            case "small" -> {
                treeSize = 7;
                treeProduction = 5;
            }
            default -> throw new IllegalStateException("Unexpected value: " + treeType);
        }
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
