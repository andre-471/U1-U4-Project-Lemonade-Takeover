import java.util.ArrayList;

public class Plot {
    private final int PLOTSIZE = 70;
    private ArrayList<Tree> trees;
    private int availableSpace;
    private int smallTrees;
    private int mediumTrees;
    private int largeTrees;

    public Plot() {
        trees = new ArrayList<Tree>();
        availableSpace = PLOTSIZE;
    }

    public void addTree(char treeSize) {
        Tree newTree = new Tree(treeSize);
        if (availableSpace - newTree.getTreeSize() > 0) {
            trees.add(new Tree(treeSize));
        }
    }

    public addAndReplaceTree()

    public void sellTree() {

    }
}
