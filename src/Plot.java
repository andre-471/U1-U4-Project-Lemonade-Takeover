import java.util.ArrayList;

public class Plot {
    private final int PLOTSIZE = 70;
    private ArrayList<Tree> trees;
    private int availableSpace;
    public Plot() {
        trees = new ArrayList<Tree>();
        availableSpace = PLOTSIZE;
    }

    public int totalLemonPerWeek() {
        int lemonPerWeek = 0;

        for (Tree tree : trees) {
            lemonPerWeek += tree.lemonPerWeek();
        }

        return lemonPerWeek;
    }
    public void addTree(char treeSize) {
        Tree newTree = new Tree(treeSize);
        if (availableSpace - newTree.getTreeSize() > 0) {
            trees.add(new Tree(treeSize));
        }
    }

    public void addAndReplaceTree() {

    }

    public void sellTree(int idx) {
        char treeType = trees.get(idx).getTreeType();
    }

    private void decrement
}
