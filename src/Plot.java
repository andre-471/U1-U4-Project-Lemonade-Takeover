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

    public int hasSpace() {

    }

    public void addTree(String treeSize) {
        Tree newTree = new Tree(treeSize);
        if (availableSpace - newTree.getTreeSize() > 0) {
            trees.add(new Tree(treeSize));
        } else {
            makePlotSpace();
            trees.add(new Tree(treeSize));
        }
    }

    public void makePlotSpace() {

    }

    public void sellTree(int idx) {
        String treeType = trees.get(idx).getTreeType();
    }
}
