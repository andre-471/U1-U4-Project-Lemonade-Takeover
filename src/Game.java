import java.util.ArrayList;

public class Game {
    private double money;
    private ArrayList<Plot> plots;
    private int week;
    private String rank;

    public Game() {
        money = 0.0;
        plots = new ArrayList<Plot>();
        week = 0;
        rank = "local lemonade store";
    }

    public void newPlot(int amount) {
        for (int i = 0; i < amount; i++) {
            plots.add(new Plot());
        }
    }

    public void newWeek() {
        week++;
        randomEvent();
        stats();
        money += MoneyPerWeek();
    }

    public String stats() {
        return "--- Week " + week + " ---\nRank: " + rank + "\nMoney: $" + money + "\nPlots: " + plots;
    }


    private void randomEvent() {
    }

    public void updateRank() {

    }

    public boolean plotHasSpace(int plotNum, String treeType) {
        return plots.get(plotNum - 1).hasSpace(treeType);
    }
    public int totalPlots() {
        return plots.size();
    }
    public int netWorth() {
        int netWorth = 0;
        for (Plot plot : plots) {
            netWorth += plot.plotValue();
        }

        netWorth += money;

        return netWorth;
    }

    public void addTree(int plotNum, String treeType) {
        plots.get(plotNum - 1).addTree(treeType);
    }

    public boolean canAffordTrees(int amount, String treeType) {
        return Tree.costBasedOnType(treeType) * amount > money;
    }

    public boolean canAffordPlot(int amount) {
        return amount * Plot.COST > money; // 100 placeholder value for price of a plot, will liely be adjusted
    }

    public int MoneyPerWeek() {
        int moneyGen = 0;
        for (Plot plot : plots) {
            moneyGen += plot.totalLemonPerWeek();
        }
        return moneyGen;
    }

}
