import java.util.List;

public class Tree {
    private char treeType;
    private int treeSize;
    // constructor for the Tree Class
    public Tree(char treeType) {
        this.treeType = treeType;
        switch (treeType) {
            case 'l' -> {
                treeSize = 14;
            }
            case 'm' -> {
                treeSize = 10;
            }
            case 's' -> {
                treeSize = 1;
            }
        }
    }

    public int getTreeSize() {
        return treeSize;
    }
}
