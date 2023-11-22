import java.util.ArrayList;

public class Plot {
    public static final int COST = 100;
    private static final int PLOTSIZE = 70;
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

    public boolean hasSpace(String treeType) {
        return availableSpace - Tree.sizeBasedOnType(treeType) >= 0;
    }

    public void addTree(String treeType) {
        Tree newTree = new Tree(treeType);
        if (availableSpace - newTree.getTreeSize() > 0) {
            trees.add(new Tree(treeType));
        } else {
            makePlotSpace(treeType);
            trees.add(new Tree(treeType));
        }
    }

    public void makePlotSpace(String treeType) {

    }

    public int plotValue() {
        return 0;
    }

    public void sellTree(int idx) {
        String treeType = trees.get(idx).getTreeType();
    }
}
